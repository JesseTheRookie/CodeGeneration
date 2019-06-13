package io.swagger.config;

import io.swagger.model.Account;
import io.swagger.model.ApiKey;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.ApiKeyRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;


@Component
public class MyApplicationRunner implements ApplicationRunner {

    private UserRepository userRepository;
    private ApiKeyRepository keyRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public MyApplicationRunner(UserRepository userRepository, AccountRepository accountRepository, ApiKeyRepository keyRepository, TransactionRepository transactionRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.keyRepository = keyRepository;
        this.transactionRepository = transactionRepository;
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
                                        new BigDecimal(line.split(",")[3]),
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

        //Transactions
        /*
        Files.lines(Paths.get("src/main/resources/transactions.csv"))
                .forEach(
                        line -> transactionRepository.save(
                                new Transaction(
                                        Integer.parseInt(line.split(",")[0]),
                                        line.split(",")[1],
                                        line.split(",")[2],
                                        new BigDecimal(line.split( ",")[3]),
                                        Transaction.parseStringToTimeStamp(line.split(",")[4]),
                                        Integer.parseInt(line.split(",")[5]))
                        ));

        transactionRepository.findAll()
                .forEach(System.out::println);

         */
    }
}

