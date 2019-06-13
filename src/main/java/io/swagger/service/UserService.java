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
public class UserService {

    private UserRepository userRepository;
    private AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Account> getAccountsByUserId(Integer userId) {
        return accountService.getAccountsByUserId(userId);
    }
}
