package io.swagger.service;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import io.swagger.api.AccountsApiController;
import io.swagger.api.ApiException;
import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import io.swagger.service.AccountService;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;
//import sun.jvm.hotspot.code.Location;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
public class TransactionService {


    //fields
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private SecurityController securityController;
    private AccountService accountService;
    private UserRepository userRepository;

    private final Double MAX_AMOUNT = 500.0;
    private final Integer DAILY_TRANSACTIONS_LIMIT = 2;

    //properties
    private Integer transactionsDoneThisDay = 0;


    //constructor
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, SecurityController securityController, UserRepository userRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.securityController = securityController;
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    //getter
    public Integer getTransactionsDoneThisDay() {
        return transactionsDoneThisDay;
    }

    public Iterable<Transaction> getAllTransactions() throws ApiException {
        if (isEmployee()) {
            return transactionRepository.findAll();
        }
        throw new ApiException(403, "You are not authorized for this request");
    }

    //get all send transactions
    public Iterable<Transaction> getTransactionsByFromIban(String iban) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (isEmployee() || securityController.currentUserId() == account.getUserId()) {
            return transactionRepository.getTransactionsByFromIban(iban);
        } else throw new ApiException(403, "You are not authorized for this request");
    }


    //get all received transactions
    public Iterable<Transaction> getTransactionsByToIban(String iban) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (isEmployee() || securityController.currentUserId() == account.getUserId()) {
            return transactionRepository.getTransactionsByToIban(iban);
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.getTransactionById(id);
    }

    public Iterable<Transaction> getTransactionsByPerformedBy(Integer userId) {
        return transactionRepository.getTransactionsByPerformedBy(userId);
    }

    public Iterable<Transaction> getTransactionsByType(Transaction.TransactionType type) {
        return transactionRepository.getTransactionsByType(type);
    }


    //methods

    public void initiateTransaction(Transaction newTransaction) throws ApiException {
        switch (newTransaction.getType()) {
            case TRANSACTION:
                validateTransaction(newTransaction);
                createTransaction(newTransaction);
                break;

            case DEPOSIT:
                validateDeposit(newTransaction);
                createDeposit(newTransaction);
                break;

            case WITHDRAWAL:
                validateWithdrawal(newTransaction);
                createWithdrawal(newTransaction);
                break;
        }
    }

    //validators

    private void validateTransaction(Transaction transaction) throws ApiException {
        checkIfAccountIsNull(transaction.getToIban());
        checkIfAccountIsNull(transaction.getFromIban());

        if (!isEmployee()) { //if user is employee no validation needed

            if (!accountsAreOwnedBySameUser(transaction.getFromIban(), transaction.getToIban())) { // binnen dezelfde user mogen transacties plaats vinden.

                if (!accountIsOwnedByLoggedInUser(transaction.getFromIban())) {
                    throw new ApiException(403, "You are not authorized for this request");
                }

                if (!areAccountsBothOfTypeCurrent(transaction.getFromIban(), transaction.getToIban())) { // Niet dezelfde user, dan alleen als beide current zijn
                    throw new ApiException(403, "You are not authorized for this request");
                }

                if (transactionExceedTheDailyLimit(transaction.getFromIban())) {
                    throw new ApiException(406, "Can't create more transactions than day limit" + DAILY_TRANSACTIONS_LIMIT);
                }

                if (amountFromAccountExceedTheMaxAmount(transaction.getAmount())) {
                    throw new ApiException(406, "Can't transfer more than " + MAX_AMOUNT);
                }

            }
        }

        //voer transactie uit
    }


    private void validateDeposit(Transaction deposit) throws ApiException {
        if (!isEmployee()) {
            throw new ApiException(403, "You are not authorized for this request");
        }
    }

    private void validateWithdrawal(Transaction withdrawal) throws ApiException {
        if (!isEmployee()) {
            throw new ApiException(403, "You are not authorized for this request");
        }
        if (accountService.accountIsNull(withdrawal.getFromIban())) {
            throw new ApiException(406, "Something has gone wrong: ");
        }
        if (!balanceIsHigherThanAmount(withdrawal.getFromIban(), withdrawal.getAmount())) {
            throw new ApiException(406, "Balance is to low: ");
        }
    }


    //creators
    private void createTransaction(Transaction newTransaction) throws ApiException {
        Account fromAccount = accountRepository.findById(newTransaction.getFromIban()).orElse(null);
        Account toAccount = accountRepository.findById(newTransaction.getToIban()).orElse(null);

        //update from account
        fromAccount.setBalance(fromAccount.getBalance() - newTransaction.getAmount());
        accountRepository.save(fromAccount);
        // }

        //update to account
        toAccount.setBalance(toAccount.getBalance() + newTransaction.getAmount());
        accountRepository.save(toAccount);

        transactionRepository.save(newTransaction);
    }

    private void createDeposit(Transaction newDeposit) throws ApiException {
        Account account = accountRepository.findById(newDeposit.getToIban()).orElse(null);
        if (accountService.accountIsNull(newDeposit.getToIban())) {
            throw new ApiException(406, "no account found that corresponds with the IBAN: " + newDeposit.getToIban());
        }

        account.setBalance(account.getBalance() + newDeposit.getAmount());

        accountRepository.save(account);
        transactionRepository.save(newDeposit);
    }

    private void createWithdrawal(Transaction newWithdrawal) {
        Account account = accountRepository.findById(newWithdrawal.getFromIban()).orElse(null);

        // if (withdrawIsValid(newWithdrawal.getFromIban(), newWithdrawal.getAmount())) {
        account.setBalance(account.getBalance() - newWithdrawal.getAmount());
        accountRepository.save(account);
        // }
        transactionRepository.save(newWithdrawal);
    }

    private Integer getNumberOfTransactionToday(String iban) {

        Timestamp timestamp = new Timestamp(new Date().getTime());
        Iterable<Transaction> transactions = transactionRepository.findAll();
        for (Transaction t : transactions) {

            //currenttime
            long stamp1 = timestamp.getTime();
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(stamp1);

            //time of transaction
            long stamp2 = t.getTimeStamp().getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(stamp2);

            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

            if (sameDay && iban == t.getFromIban()) {
                transactionsDoneThisDay++;
            }
        }
        return transactionsDoneThisDay;
    }

    private Boolean transactionExceedTheDailyLimit(String iban) {
        return getNumberOfTransactionToday(iban) > DAILY_TRANSACTIONS_LIMIT;
    }

    private Boolean amountFromAccountExceedTheMaxAmount(Double amount) {
        return amount > MAX_AMOUNT;
    }

    private Boolean accountsAreOwnedBySameUser(String ibanFrom, String ibanTo) {
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        int UserIdFrom = accountFrom.getUserId();
        int UserIdTo = accountTo.getUserId();

        return UserIdFrom == UserIdTo;
    }

    private Boolean areAccountsBothOfTypeCurrent(String ibanFrom, String ibanTo) {
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        Account.AccounttypeEnum accounttypeFrom = accountFrom.getAccounttype();
        Account.AccounttypeEnum accounttypeTo = accountTo.getAccounttype();

        return accounttypeFrom.equals(accounttypeTo);

    }

    private Boolean balanceIsHigherThanAmount(String iban, Double amount) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getBalance() >= amount) {
            return true;
        } else {
            throw new ApiException(406, "Balance can't be below zero on: " + iban);
        }
    }

    private Boolean accountIsOwnedByLoggedInUser(String iban) {
        Account account = accountRepository.findById(iban).orElse(null);
        return account.getUser().getId() == securityController.currentUserId();
    }

    private void checkIfAccountIsNull(String iban) throws ApiException {
        //check if fromIBAN exists
        if (accountService.accountIsNull(iban)) {
            throw new ApiException(406, "no account found that corresponds with the IBAN: " + iban);
        }
    }

    public boolean isEmployee() {
        return (securityController.currentUser().hasAuthority(User.RoleEnum.EMPLOYEE)) || (securityController.currentUser().hasAuthority(User.RoleEnum.USER_EMPLOYEE));
    }

    private Boolean theAccountIsCustomer(String iban) {
        Account account = accountRepository.findById(iban).orElse(null);
        User user = account.getUser();

        return user.hasAuthority(User.RoleEnum.USER);
    }
}
