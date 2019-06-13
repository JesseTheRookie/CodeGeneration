package io.swagger.service;

import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
        createTestTransactions();
    }
    public void createTransaction (Transaction newTransaction) {
        transactionRepository.save(newTransaction);
    }
    public Iterable<Transaction> getAllTransactions(){return transactionRepository.findAll();}

    public void createTestTransactions(){
        this.createTransaction(new Transaction("NL01INHO0000000004", "NL01INHO0000000003", new BigDecimal(100), 1));
        this.createTransaction(new Transaction("NL01INHO0000000003", "NL01INHO0000000004", new BigDecimal(100), 2));
    }
}
