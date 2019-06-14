package io.swagger.service;

import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccountsByType(String type) {
            return accountRepository.getAccountsByType(type);
        /*
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return (List<Account>) accounts.stream()
                .filter(account -> account.getAccounttype().toString().equals(type))
                .findAny()
                .orElse(null);*/
    }

    public Iterable<Account> getAllAccountsByType(){return accountRepository.findAll();}

    public List<Account> getAccountsByUserId(Integer id) {

        return accountRepository.getAccountsById(id);
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
