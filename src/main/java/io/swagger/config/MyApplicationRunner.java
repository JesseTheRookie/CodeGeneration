package io.swagger.config;

import io.swagger.model.*;
import io.swagger.repository.*;
import io.swagger.service.DepositsService;
import io.swagger.service.TransactionService;
import io.swagger.service.WithdrawalsService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private UserRepository userRepository;
    private ApiKeyRepository keyRepository;
    private AccountRepository accountRepository;
    private WithdrawalsRepository withdrawalsRepository;
    private WithdrawalsService withdrawalsService;

    public MyApplicationRunner(UserRepository userRepository, AccountRepository accountRepository, ApiKeyRepository keyRepository, WithdrawalsService withdrawalsService, WithdrawalsRepository withdrawalsRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.keyRepository = keyRepository;
        this.withdrawalsRepository = withdrawalsRepository;
        this.withdrawalsService = withdrawalsService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //Users and accounts

        Files.lines(Paths.get("src/main/resources/users.csv"))
                .forEach(
                        line -> userRepository.save(
                                new User(line.split(",")[0],
                                        line.split(",")[1],
                                        User.RoleEnum.fromValue(line.split(",")[2])
                                )
                        )
                );

        userRepository.findAll()
                .forEach(System.out::println);

        Files.lines(Paths.get("src/main/resources/accounts.csv"))
                .forEach(
                        line -> accountRepository.save(
                                new Account((line.split(",")[0]),
                                        userRepository.findById(Integer.parseInt(line.split(",")[1])),
                                        line.split(",")[2],
                                        Double.parseDouble(line.split(",")[3]),
                                        Account.AccounttypeEnum.fromValue(line.split(",")[4]),
                                        Account.StatusEnum.fromValue(line.split(",")[5])
                                )
                        )
                );

        accountRepository.findAll()
                .forEach(System.out::println);


        for (String s : Arrays.asList(new String[]{"e800eff2-846c-4334-9168-bcc14bdf0a9c","ebace734-4b2c-4791-aaac-e076cb3c221e","0449e978-5e4e-40bb-8af2-c29478bc3a98"})){
            keyRepository.save(new ApiKey(s));
        }

        keyRepository.findAll()
                .forEach(System.out::println);

        withdrawalsService.createNewWithdrawal(new Withdrawal("NL01INHO0000000004", 50.00));
        withdrawalsService.createNewWithdrawal(new Withdrawal("NL01INHO0000000003", 12.00));
        withdrawalsService.createNewWithdrawal(new Withdrawal("NL01INHO0000000002", 404.00));

        withdrawalsRepository.findAll()
                .forEach(System.out::println);

        accountRepository.findAll()
                .forEach(System.out::println);

    }
}

