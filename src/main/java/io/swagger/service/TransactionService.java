package io.swagger.service;

import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private final Double maxAmount = 100.0;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
        createTestTransactions();
    }
    public void createTransaction (Transaction newTransaction) {
        if(maxAmount > newTransaction.getAmount()) {
            transactionRepository.save(newTransaction);
        }
    }
    public Iterable<Transaction> getAllTransactions(){return transactionRepository.findAll();}
    public Iterable<Transaction> getTransactionByIban(String iban){return transactionRepository.getTransactionByIban(iban);}

    public void createTestTransactions(){
        this.createTransaction(new Transaction("NL01INHO0000000004", "NL01INHO0000000003", 50.0, 1));
        this.createTransaction(new Transaction("NL01INHO0000000003", "NL01INHO0000000004", 100.1, 2));
    }
}
