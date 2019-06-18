package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query("select t from Transaction t where t.fromIban = ?1")
    List<Transaction> getTransactionsByFromIban(String iban);
    @Query("select t from Transaction t where t.to = ?1")
    List<Transaction> getTransactionsByToIban(String iban);
    @Query("select t from Transaction t where t.id = ?1")
    //Aanpassen naar Transaction
    List<Transaction> getTransactionById(Integer id);
    @Query("select t from Transaction t where t.performedBy = ?1")
    List<Transaction> getTransactionsByPerformedBy(Integer userId);
}
