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
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private SecurityController securityController;
    private UserRepository userRepository;
    private final Double maxAmount = 100.0;
    private final Integer dayLimit = 2;
    private final Double amountLimit = 10.00;
    private Integer test = 0;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, SecurityController securityController, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.securityController = securityController;
        this.userRepository = userRepository;
    }

    public void addToAccount(String iban, Double amount) {
        Account account = accountRepository.findById(iban).orElse(null);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public boolean reductFromAccount(String iban, Double amount) {
        Account account = accountRepository.findById(iban).orElse(null);
        if ((account.getBalance() > amount) && ((account.getBalance() - amount) > amountLimit)) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            return true;
        } else {
            throw new IllegalArgumentException("Balance can't be below zero");
        }
    }

    public Integer getNumberOfTransactionToday(String iban) {

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

            if (sameDay) {
                test++;
            }
        }
        return test;
    }

    public Boolean checkForInvalidTransactions(String ibanFrom, String ibanTo) {
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        if (accountFrom.getUser().getId() == accountTo.getUser().getId()) {
            return true;
        }
        if (accountTo.getAccounttype().equals(Account.AccounttypeEnum.CURRENT) && accountFrom.getAccounttype().equals(Account.AccounttypeEnum.CURRENT)) {
            return true;
        }
        return false;
    }

    public void createTransaction(Transaction newTransaction) throws ApiException {
        if (checkForInvalidTransactions(newTransaction.getFromIban(), newTransaction.getTo())) {
            //Only executed if the transaction count is lower than or equal to 10
            if (getNumberOfTransactionToday(newTransaction.getFromIban()) <= dayLimit) {
                //Only executed if the amount of transaction is lower than 100.00
                if (maxAmount > newTransaction.getAmount()) {
                    transactionRepository.save(newTransaction);
                    //Only add amount to account if a reduction from the sender account is possible
                    if (reductFromAccount(newTransaction.getFromIban(), newTransaction.getAmount())) {
                        addToAccount(newTransaction.getTo(), newTransaction.getAmount());
                    } else {
                        throw new ApiException(412, "reduction from account is invalid (amount is to high)");
                    }
                } else {
                    throw new ApiException(406, "Can't transfer more than " + maxAmount);
                }
            } else {
                throw new ApiException(406, "Can't create more transactions than day limit" + dayLimit);
            }
        } else {
            throw new ApiException(406, "Can't create a transaction to another savings than your own nor from a savings acount to another user's current account");
        }
    }

    public Iterable<Transaction> getAllTransactions() throws ApiException {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return transactionRepository.findAll();
        }throw  new ApiException(403, "You are not authorized for this request");

    }

    public Iterable<Transaction> getTransactionByIban(String iban) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (userRepository.getUserByName(
                securityController.currentUserName()).getId() == account.getUserId()
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return (List<Transaction>) transactionRepository.getTransactionByIban(iban);
            }
       else throw new ApiException(403, "You are not authorized for this request");
    }
}
