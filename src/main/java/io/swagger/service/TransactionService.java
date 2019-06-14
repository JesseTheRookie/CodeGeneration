package io.swagger.service;

import io.swagger.api.AccountsApiController;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountRepository;
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
    private final Double maxAmount = 100.0;
    private final Integer dayLimit = 2;
    private Integer test = 0;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addToAccount (String iban, Double amount){
        Account account = accountRepository.findById(iban).orElse(null);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public boolean reductFromAccount (String iban, Double amount){
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getBalance() > amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            return true;
        } else{
            throw new IllegalArgumentException("Balance can't be below zero");
        }
    }

    public Integer getNumberOfTransactionToday(String iban){

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

             if(sameDay){
                 test++;
             }
        }
        return test;
    }

    public Boolean checkIfSavingsToSameUser(String iban){
        Iterable<Account> acounts = accountRepository.findAll();
        
        return false;
    }
    public void createTransaction (Transaction newTransaction) {
        //Only executed if the transaction count is lower than or equal to 10
        if (getNumberOfTransactionToday(newTransaction.getFromIban()) <= dayLimit) {
            //Only executed if the amount of transaction is lower than 100.00
            if (maxAmount > newTransaction.getAmount()) {
                transactionRepository.save(newTransaction);
                //Only add amount to account if a reduction from the sender account is possible
                if (reductFromAccount(newTransaction.getFromIban(), newTransaction.getAmount())) {
                    addToAccount(newTransaction.getTo(), newTransaction.getAmount());
                } else{
                    //exception =>
                }
            } else{
                //exception => amount > maxAmount
            }
        } else{
            //throw new IllegalArgumentException("You have reached your maximum transaction quota for today");
        }
    }
    public Iterable<Transaction> getAllTransactions(){return transactionRepository.findAll();}
    public Iterable<Transaction> getTransactionByIban(String iban){return transactionRepository.getTransactionByIban(iban);}
}
