package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;
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

    public List<Account> getAllAccountsByType(String type) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.getAccountsByType(type);
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public Iterable<Account> getAllAccountsByType() throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.findAll();
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public List<Account> getAccountsByUserId(Integer id) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName())
                .getId() == id
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            return accountRepository.getAccountsById(id);
        }  else throw new ApiException(403, "You are not authorized for this request");
    }

    public void createAccount(Account body) {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            accountRepository.save(body);
        }
    }

    public Account getAccountByIban(String iban) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
        } else throw new ApiException(403, "You are not authorized for this request");
    }

    public void deleteAccount(String iban) throws ApiException {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {
            Account account = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
            if (account.getBalance()!= 0){
                 throw new ApiException(405, "This account still has money in it, and can't be deleted");
            }else accountRepository.delete(account);
        }
    }

    public void toggleAccountStatus(String iban) {
        if (userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByUsername(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)) {

            Account oldAccount = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new);
            Account updatedAccount = new Account(oldAccount.getUser());
                    updatedAccount.setIban(oldAccount.getIban());
                    updatedAccount.setName(oldAccount.getName());
                    updatedAccount.setBalance(oldAccount.getBalance());
                    updatedAccount.setAccounttype(oldAccount.getAccounttype());
                    if (oldAccount.getStatus()== Account.StatusEnum.ACTIVE){
                        updatedAccount.setStatus(Account.StatusEnum.FROZEN);
                    }else updatedAccount.setStatus(Account.StatusEnum.ACTIVE);
            accountRepository.delete(oldAccount);
            accountRepository.save(updatedAccount);
        }
    }

    public Boolean accountIsNull(String iban){
        Account account = accountRepository.findById(iban).orElse(null);
        return account == null;
    }
}
