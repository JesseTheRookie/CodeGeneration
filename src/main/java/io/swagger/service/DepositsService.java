package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.model.Deposit;
import io.swagger.model.Account;
import io.swagger.repository.DepositsRepository;
import io.swagger.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class DepositsService {

    private DepositsRepository depositsRepository;
    private AccountRepository accountRepository;

    public DepositsService(DepositsRepository depositsRepository, AccountRepository accountRepository) throws Exception {
        this.accountRepository = accountRepository;
        this.depositsRepository = depositsRepository;
        createTestDeposits();
    }

    public void createDeposit(Deposit newDeposit) throws Exception{
        addToAccount(newDeposit.getTo(), newDeposit.getAmount());
        depositsRepository.save(newDeposit);
    }

    public Iterable<Deposit> getAllDeposits() {
        return depositsRepository.findAll();
    }

    public List<Deposit> getDepositByIban(String iban) {
        return depositsRepository.getDepositsByIban(iban);
    }

    public void addToAccount (String iban, Double amount) throws Exception{
        Account account = accountRepository.findById(iban).orElse(null);
        if (account == null){
            throw new ApiException(406, "no account found that corresponds with the IBAN: "+ iban);
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void createTestDeposits() throws Exception{
        this.createDeposit(new Deposit("NL01INHO0000000004", 50.00));
        this.createDeposit(new Deposit("NL01INHO0000000003", 12.00));
        this.createDeposit(new Deposit("NL01INHO0000000002", 404.00));
    }
}