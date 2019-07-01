package io.swagger.service;


import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {
    //@autowired
    private UserRepository userRepository;

//    @override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        UserBuilder builder = null;

//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new MyUserPrincipal(user);


        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRole().toString());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}

