package io.swagger.service;

import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private SecurityController securityController;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository ,SecurityController securityController) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.securityController = securityController;
    }

    public List<Account> getAllAccountsByType(String type) {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.getAccountsByType(type);
        }else return null;
    }

    public Iterable<Account> getAllAccountsByType() {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.findAll();
        } else return null;
    }

    public List<Account> getAccountsByUserId(Integer id) {
        if (userRepository.getUserByName(
                securityController.currentUserName())
                .getId() == id
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.getAccountsById(id);
        } else return null;
    }

    public void createAccount(Account body) {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            accountRepository.save(body);
        }
    }

    public Account getAccountByIban(String iban) {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
        }else return null;
    }

    public void deleteAccount(String iban) {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            Account account = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
            if (account.getBalance()!= 0){
                return;
            }else accountRepository.delete(account);
        }
    }

    public void toggleAccountStatus(String iban, Account body) {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {

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
}
