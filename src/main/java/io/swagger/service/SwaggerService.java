package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class SwaggerService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public SwaggerService(UserRepository userRepository, AccountRepository accountRepository) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByUserId(Integer id) {
       return accountRepository.getAccountsById(id);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    public void createAccount(Account body) {
        accountRepository.save(body);
    }

    public Account getAccountByIban(String iban) {
        return accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteAccount(String iban) {
        accountRepository.delete(accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new));
    }

    public void toggleAccountStatus(String iban, Account body) {

        /*
        accountRepository.deleteById(iban);
        accountRepository.save(body);
        */
        // Bovenstaande werkt ook, echter kan op onderstaande manier enkel de status aangepast worden
        // dit voorkomt aanpassen van bv balance

        Account oldAccount = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
        Account updatedAccount = new Account(
                oldAccount.getIban(),
                oldAccount.getUser(),
                oldAccount.getName(),
                oldAccount.getBalance(),
                oldAccount.getAccounttype(),
                body.getStatus());
        accountRepository.delete(oldAccount);
        accountRepository.save(updatedAccount);
    }
}
