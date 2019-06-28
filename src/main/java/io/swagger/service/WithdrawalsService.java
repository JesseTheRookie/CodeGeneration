package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.model.Account;
import io.swagger.model.Withdrawal;
import io.swagger.repository.WithdrawalsRepository;
import io.swagger.repository.AccountRepository;

import java.lang.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WithdrawalsService {
    private WithdrawalsRepository withdrawalsRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;

    public WithdrawalsService(WithdrawalsRepository withdrawalsRepository, AccountRepository accountRepository, AccountService accountService){
        this.accountRepository = accountRepository;
        this.withdrawalsRepository = withdrawalsRepository;
        this.accountService = accountService;
    }

    public Withdrawal createNewWithdrawal(Withdrawal newWithdrawal) throws ApiException{
        Account account = accountRepository.findById(newWithdrawal.getSenderIban()).orElse(null);
        if(withdrawIsValid(newWithdrawal.getSenderIban(), newWithdrawal.getAmount())){
            account.setBalance(account.getBalance() - newWithdrawal.getAmount());
            accountRepository.save(account);
        }
        return withdrawalsRepository.save(newWithdrawal);
    }

    public void reductFromAccount (String iban, Double amount) throws ApiException{
        Account account = accountRepository.findById(iban).orElse(null);
        if(withdrawIsValid(iban, amount)){
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        }
    }
    public Boolean withdrawIsValid(String iban, Double amount) throws ApiException{
        if(accountService.accountIsNotNull(iban) && balanceIsHigherThanAmount(iban, amount)){
            return true;
        } else{
            throw new ApiException(406, "Something has gone wrong: ");
        }
    }
    private Boolean balanceIsHigherThanAmount(String iban, Double amount) throws ApiException{
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getBalance() > amount){
            return true;
        } else{
            throw new ApiException(406, "Balance can't be below zero on: "+ iban);
        }
    }
    public Iterable<Withdrawal> getAllWithdrawals(){
        return withdrawalsRepository.findAll();
    }

    public List<Withdrawal> getWithdrawalsByIban(String iban){
        return withdrawalsRepository.getWithdrawalsByIban(iban);
    }
}
