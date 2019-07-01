package io.swagger.service;

import io.swagger.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyUserDetailsService implements UserDetailsService {
    @autowired
    private UserRepository userRepository;

    @override
    public UserDetails loaduserByUsername(String username){
        User user = userRepository.findByusername(username);
    }
}
