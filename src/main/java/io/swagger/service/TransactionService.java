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
    private DepositsService depositsService;
    private AccountService accountService;
    private WithdrawalsService withdrawalsService;
    private UserRepository userRepository;

    private final Double MAX_AMOUNT = 500.0;
    private final Integer DAILY_TRANSACTIONS_LIMIT = 2;

    //properties
    private Integer transactionsDoneThisDay = 0;


    //constructor
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, SecurityController securityController, UserRepository userRepository, DepositsService depositsService, WithdrawalsService withdrawalsService, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.withdrawalsService = withdrawalsService;
        this.depositsService = depositsService;
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
        if (userRepository.getUserByName(
                securityController.currentUserName()).getId() == account.getUserId()
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return transactionRepository.getTransactionsByFromIban(iban);
        }
        else throw new ApiException(403, "You are not authorized for this request");
    }


    //get all received transactions
    public Iterable<Transaction> getTransactionsByToIban(String iban) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (userRepository.getUserByName(
                securityController.currentUserName()).getId() == account.getUserId()
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return transactionRepository.getTransactionsByToIban(iban);
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public Iterable<Transaction> getTransactionById(Integer id) {
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

        if (areAccountsSameUser(transaction.getFromIban(), transaction.getToIban())) { // binnen dezelfde user mogen transacties plaats vinden.
            return;
            //            throw new ApiException(403, "You are not authorized for this request");
        }
        if (areAccountsBothOfTypeCurrent(transaction.getFromIban(), transaction.getToIban())) { // Niet dezelfde user, dan alleen als beide current zijn
            return;

        }
        throw new ApiException(406, "Can't create a transaction to another savings account than your own nor from a savings acount to another user's current account or vice versa");
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
        if (!accountService.accountIsNotNull(withdrawal.getFromIban())) {
            throw new ApiException(406, "Something has gone wrong: ");
        }
        if(!balanceIsHigherThanAmount(withdrawal.getFromIban(), withdrawal.getAmount())){
            throw new ApiException(406, "Balance is to low: ");
        }
    }


    //creators
    private void createTransaction(Transaction newTransaction) throws ApiException {
        String fromAccountIban = newTransaction.getFromIban();
        String toAccountIban = newTransaction.getToIban();
        double amount = newTransaction.getAmount();

        if (!theAccountIsCustomer(fromAccountIban)) {
            throw new ApiException(403, "You are not authorized for this request");
        }
       // if (theTransactionIsValid(fromAccountIban, toAccountIban)) {
            if (theTransactionDoesNotExceedTheDailyLimit(fromAccountIban)) {
                if (theAmountFromAccountDoesNotExceedTheMaxAmount(amount)) {
                    if (withdrawalsService.withdrawIsValid(fromAccountIban, amount)) {
                        withdrawalsService.reductFromAccount(fromAccountIban, amount);
                        depositsService.addToAccount(toAccountIban, amount);
                        transactionRepository.save(newTransaction);
                    }
                }
         //   }
        } else if (withdrawalsService.withdrawIsValid(fromAccountIban, amount)) {
            withdrawalsService.reductFromAccount(fromAccountIban, amount);
            depositsService.addToAccount(toAccountIban, amount);
            transactionRepository.save(newTransaction);
        }
    }


    //create

    private void createDeposit(Transaction newDeposit) throws ApiException {
        Account account = accountRepository.findById(newDeposit.getToIban()).orElse(null);
        if (accountService.accountIsNotNull(newDeposit.getToIban())) {
            account.setBalance(account.getBalance() + newDeposit.getAmount());
            accountRepository.save(account);
        }
        transactionRepository.save(newDeposit);
    }

    private void createWithdrawal(Transaction newWithdrawal) throws ApiException {
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

    private Boolean theTransactionDoesNotExceedTheDailyLimit(String iban) throws ApiException {
        if (getNumberOfTransactionToday(iban) < DAILY_TRANSACTIONS_LIMIT) {
            return true;
        } else {
            throw new ApiException(406, "Can't create more transactions than day limit" + DAILY_TRANSACTIONS_LIMIT);
        }
    }

    private Boolean theAmountFromAccountDoesNotExceedTheMaxAmount(Double amount) throws ApiException {
        if (amount < MAX_AMOUNT) {
            return true;
        } else {
            throw new ApiException(406, "Can't transfer more than " + MAX_AMOUNT);
        }
    }

    private Boolean areAccountsSameUser(String ibanFrom, String ibanTo) {
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        int UserIdFrom = accountFrom.getUserId();
        int UserIdTo = accountTo.getUserId();

        if (UserIdFrom == UserIdTo) {
            return true;
        }
        return false;
    }

    private Boolean areAccountsBothOfTypeCurrent(String ibanFrom, String ibanTo) {
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        Account.AccounttypeEnum accounttypeFrom = accountFrom.getAccounttype();
        Account.AccounttypeEnum accounttypeTo = accountTo.getAccounttype();

        if (accounttypeFrom == accounttypeTo) {
            return true;
        }
        return false;
    }

    private Boolean balanceIsHigherThanAmount(String iban, Double amount) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getBalance() >= amount) {
            return true;
        } else {
            throw new ApiException(406, "Balance can't be below zero on: " + iban);
        }
    }


    public boolean isEmployee() throws ApiException {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return true;
        }
        return false;
    }

    private Boolean theAccountIsCustomer(String iban) {
        Account account = accountRepository.findById(iban).orElse(null);
        User user = account.getUser();

        if (user.getRole() == User.RoleEnum.USER) {
            return true;
        }
        return false;
    }

/////////////// all filter functions


}
