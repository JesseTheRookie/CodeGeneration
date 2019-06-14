package io.swagger.service;

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

    public WithdrawalsService(WithdrawalsRepository withdrawalsRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.withdrawalsRepository = withdrawalsRepository;

//        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 50.00));
//        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 4.00));
//        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 6.00));
//        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 89.00));
    }

    public Withdrawal createNewWithdrawal(Withdrawal newWithdrawal){
        reductFromAccount(newWithdrawal.getSenderIban(), newWithdrawal.getAmount());
        return withdrawalsRepository.save(newWithdrawal);
    }

    public void reductFromAccount (String iban, Double amount){
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getBalance() > amount){
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } else{
            throw new IllegalArgumentException("Balance can't be below zero");
        }

    }

    public Iterable<Withdrawal> getAllWithdrawals(){
        return withdrawalsRepository.findAll();
    }

    public List<Withdrawal> getWithdrawalsByIban(String iban){
        return withdrawalsRepository.getWithdrawalsByIban(iban);
    }
}
