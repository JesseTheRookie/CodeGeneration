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

    public DepositsService(DepositsRepository depositsRepository, AccountRepository accountRepository) throws ApiException {
        this.accountRepository = accountRepository;
        this.depositsRepository = depositsRepository;
        //createTestDeposits();
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
        if (account == null){
            throw new ApiException(406, "no account found that corresponds with the IBAN: "+ iban);
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void createTestDeposits() throws Exception{

    }
}