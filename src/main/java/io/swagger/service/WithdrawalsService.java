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

    public WithdrawalsService(WithdrawalsRepository withdrawalsRepository, AccountRepository accountRepository) throws Exception{
        this.accountRepository = accountRepository;
        this.withdrawalsRepository = withdrawalsRepository;

        //createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 50.00));
       // createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 4.00));
      //  createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 6.00));
      //  createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 89.00));
    }

    public Withdrawal createNewWithdrawal(Withdrawal newWithdrawal) throws Exception{
        reductFromAccount(newWithdrawal.getSenderIban(), newWithdrawal.getAmount());
        return withdrawalsRepository.save(newWithdrawal);
    }

    public void reductFromAccount (String iban, Double amount) throws Exception{
        Account account = accountRepository.findById(iban).orElse(null);
        if (account == null){
            throw new ApiException(406, "no account found that corresponds with the IBAN: "+ iban);
        }
        if (account.getBalance() > amount){
            throw new ApiException(406, "Balance can't be below zero");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

    }

    public Iterable<Withdrawal> getAllWithdrawals(){
        return withdrawalsRepository.findAll();
    }

    public List<Withdrawal> getWithdrawalsByIban(String iban){
        return withdrawalsRepository.getWithdrawalsByIban(iban);
    }
}
