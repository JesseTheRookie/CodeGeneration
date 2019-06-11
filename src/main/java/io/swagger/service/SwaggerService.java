package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SwaggerService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public SwaggerService(UserRepository userRepository, AccountRepository accountRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void createUser(User newUser){
        userRepository.save(newUser);
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void toggleAccountStatus(String iban){
        Account a = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
        if (a.getStatus() == Account.StatusEnum.ACTIVE){
           a.setStatus(Account.StatusEnum.FROZEN);
        }else a.setStatus(Account.StatusEnum.ACTIVE);
    }
    // ToDo status toggle

}
