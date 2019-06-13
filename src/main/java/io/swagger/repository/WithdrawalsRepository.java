package io.swagger.repository;

import io.swagger.model.Withdrawal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalsRepository extends CrudRepository<Withdrawal, String> {
    @Query("select w from Withdrawal w, Account a where w.senderIban = a.iban and w.senderIban =?1")
    public List<Withdrawal> getWithdrawalsByIban(String iban);
}