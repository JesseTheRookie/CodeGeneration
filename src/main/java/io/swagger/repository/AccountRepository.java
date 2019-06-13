package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface AccountRepository extends CrudRepository<Account, String> {

    @Query("select a from Account a, User u where a.user.id = u.id and a.user.id =?1")
    public List<Account> getAccountsById(Integer id);

    @Query("select a from Account a where a.typeValue =?1")
    public List<Account> getAccountsByType(String type);
}
