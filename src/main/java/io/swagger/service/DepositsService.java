package io.swagger.service;

import io.swagger.model.Deposit;
import io.swagger.repository.DepositsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class DepositsService {

    private DepositsRepository depositsRepository;

    public DepositsService(DepositsRepository depositsRepository) {
        this.depositsRepository = depositsRepository;
        createTestDeposits();
    }

    public void createDeposit(Deposit newDeposit) {
        depositsRepository.save(newDeposit);
    }

    public Iterable<Deposit> getAllDeposits() {
        return depositsRepository.findAll();
    }

    public List<Deposit> getDepositByIban(String iban) {
        return depositsRepository.getDepositsByIban(iban);
    }

    public void createTestDeposits(){
        this.createDeposit(new Deposit("NL01INHO0000000004", new BigDecimal("50")));
        this.createDeposit(new Deposit("NL01INHO0000000003", new BigDecimal("12")));
        this.createDeposit(new Deposit("NL01INHO0000000002", new BigDecimal("404")));
    }
}