package io.swagger.service;

import io.swagger.api.AccountsApiController;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountRepository;
import io.swagger.service.AccountService;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private final Double maxAmount = 100.0;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addToAccount (String iban, Double amount){
        Account account = accountRepository.findById(iban).orElse(null);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void reductFromAccount (String iban, Double amount){
        Account account = accountRepository.findById(iban).orElse(null);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void createTransaction (Transaction newTransaction) {
        if(maxAmount > newTransaction.getAmount()) {
            transactionRepository.save(newTransaction);
            addToAccount(newTransaction.getTo(), newTransaction.getAmount());
            reductFromAccount(newTransaction.getFromIban(), newTransaction.getAmount());
        }
    }
    public Iterable<Transaction> getAllTransactions(){return transactionRepository.findAll();}
    public Iterable<Transaction> getTransactionByIban(String iban){return transactionRepository.getTransactionByIban(iban);}
}
