package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String> {

    @Query("select a from Account a, User u where a.user.id = u.id and a.user.id =?1")
    public List<Account> getAccountsById(Integer id);
}
