package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.model.Deposit;
import io.swagger.model.Account;
import io.swagger.repository.DepositsRepository;
import io.swagger.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DepositsService {

    private DepositsRepository depositsRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;

    public DepositsService(DepositsRepository depositsRepository, AccountRepository accountRepository, AccountService accountService){
        this.accountRepository = accountRepository;
        this.depositsRepository = depositsRepository;
        this.accountService =  accountService;
    }

    public void createDeposit(Deposit newDeposit) throws ApiException{
        addToAccount(newDeposit.getTo(), newDeposit.getAmount());
        depositsRepository.save(newDeposit);
    }

    public Iterable<Deposit> getAllDeposits() {
        return depositsRepository.findAll();
    }

    public List<Deposit> getDepositByIban(String iban) {
        return depositsRepository.getDepositsByIban(iban);
    }

    public void addToAccount (String iban, Double amount) throws ApiException{
        Account account = accountRepository.findById(iban).orElse(null);
        if (accountService.accountIsNotNull(iban)){
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        }
    }
}