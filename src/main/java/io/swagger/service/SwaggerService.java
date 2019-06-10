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

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

}
