package io.swagger.config;

import io.swagger.model.*;
import io.swagger.repository.*;
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
                                        line.split(",")[2]
                                )
                        )
                );

        userRepository.findAll()
                .forEach(System.out::println);

        Files.lines(Paths.get("src/main/resources/accounts.csv"))
                .forEach(
                        line -> accountRepository.save(
                                new Account(
                                        line.split(",")[0],
                                        userRepository.findById(Integer.parseInt(line.split(",")[1])),
                                        line.split(",")[2],
                                        Double.parseDouble(line.split(",")[3]),
                                        line.split(",")[4],
                                        line.split(",")[5]
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

        transactionRepository.save(new Transaction("NL01INHO0000000004", "NL01INHO0000000002", 50.0, "TRANSACTION", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000002", "NL01INHO0000000004", 150.0, "TRANSACTION", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000004", "NL01INHO0000000002", 50.0, "TRANSACTION", 102 ));

        transactionRepository.findAll()
                .forEach(System.out::println);

        accountRepository.findAll()
                .forEach(System.out::println);

        transactionRepository.save(new Transaction("NL01INHO0000000003", 12.00, "DEPOSIT", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000002", 404.00, "DEPOSIT", 101 ));

        transactionRepository.save(new Transaction("NL01INHO0000000004", 50.0, "WITHDRAWAL", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000004", 4.0, "WITHDRAWAL", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000004", 6.0, "WITHDRAWAL", 101 ));
        transactionRepository.save(new Transaction("NL01INHO0000000004", 8.0, "WITHDRAWAL", 101 ));

        transactionRepository.findAll()
                .forEach(System.out::println);

    }
}

