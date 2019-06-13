package io.swagger.service;

import io.swagger.model.Withdrawal;
import io.swagger.repository.WithdrawalsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WithdrawalsService {
    private WithdrawalsRepository withdrawalsRepository;

    public WithdrawalsService(WithdrawalsRepository withdrawalsRepository) {
        this.withdrawalsRepository = withdrawalsRepository;

        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", new BigDecimal("50")));
        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", new BigDecimal("4")));
        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", new BigDecimal("6")));
        this.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", new BigDecimal("89")));
    }

    public Withdrawal createNewWithdrawal(Withdrawal newWithdrawal){
        Withdrawal foo = withdrawalsRepository.save(newWithdrawal);
        return foo;
    }

    public Iterable<Withdrawal> getAllWithdrawals(){
        return withdrawalsRepository.findAll();
    }

    public List<Withdrawal> getWithdrawalsByIban(String iban){
        return withdrawalsRepository.getWithdrawalsByIban(iban);
    }
}
