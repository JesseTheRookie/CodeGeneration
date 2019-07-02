package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Iterable<User> getAllUsers() throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return userRepository.findAll();
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public User getUserById(Integer userId) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName())
                .getId() == userId
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public List<Account> getAccountsByUserId(Integer userId) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName())
                .getId() == userId
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountService.getAccountsByUserId(userId);
        } else throw new ApiException(403, "You are not authorized for this request");
    }
}
