package io.swagger.service;

import io.swagger.api.SecurityController;
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
    private SecurityController securityController;

    public UserService(UserRepository userRepository, AccountService accountService, SecurityController securityController) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.securityController = securityController;
    }

    public Iterable<User> getAllUsers() {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return userRepository.findAll();
        } else return null;
    }

    public void createUser(User newUser) {

        userRepository.save(newUser);
    }

    public User getUserById(Integer userId) {
        if (userRepository.getUserByName(
                securityController.currentUserName())
                .getId() == userId
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        } else return null;
    }

    public List<Account> getAccountsByUserId(Integer userId) {
        if (userRepository.getUserByName(
                securityController.currentUserName())
                .getId() == userId
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return accountService.getAccountsByUserId(userId);
        }else return null;
    }
}
