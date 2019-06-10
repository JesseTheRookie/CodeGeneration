package io.swagger.service;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SwaggerService {

    private UserRepository userRepository;

    public SwaggerService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

}
