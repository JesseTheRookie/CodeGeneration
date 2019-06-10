package io.swagger.config;

import io.swagger.model.ApiKey;
import io.swagger.model.User;
import io.swagger.repository.ApiKeyRepository;
import io.swagger.repository.UserRepository;
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

    public MyApplicationRunner(UserRepository userRepository, ApiKeyRepository keyRepository){

        this.userRepository = userRepository;
        this.keyRepository = keyRepository;
    }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Files.lines(Paths.get("src/main/resources/users.csv"))
                .forEach(
                        line -> userRepository.save(
                                new User(Long.parseLong(line.split(",")[0]),
                                        line.split(",")[1],
                                        line.split(",")[2],
                                        User.RoleEnum.fromValue(line.split(",")[3])
                                )
                        )
                );

        userRepository.findAll()
                .forEach(System.out::println);

        for (String s : Arrays.asList(new String[]{"e800eff2-846c-4334-9168-bcc14bdf0a9c","ebace734-4b2c-4791-aaac-e076cb3c221e","0449e978-5e4e-40bb-8af2-c29478bc3a98"})){
            keyRepository.save(new ApiKey(s));
        }

        keyRepository.findAll()
                .forEach(System.out::println);

    }
}
