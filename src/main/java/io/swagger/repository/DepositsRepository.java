package io.swagger.repository;

import io.swagger.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositsRepository extends CrudRepository<Deposit, String> {
    @Query("select d from Deposit d, Account a where d.to = a.iban and d.to =?1")
    public List<Deposit> getDepositsByIban(String iban);
}