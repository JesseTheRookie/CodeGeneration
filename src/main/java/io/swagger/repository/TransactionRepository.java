package io.swagger.repository;

import io.swagger.model.Transaction;
import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query("select t from Transaction t where t.fromIban = ?1")
    List<Transaction> getTransactionByIban(String iban);
}
