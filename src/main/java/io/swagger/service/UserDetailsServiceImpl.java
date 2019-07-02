package io.swagger.service;


import io.swagger.config.IAuthenticationFacade;
import io.swagger.config.SecurityConfig;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      /*  System.out.println(new BCryptPasswordEncoder(11).encode("user123"));
        System.out.println(new BCryptPasswordEncoder(11).encode("user123"));
        System.out.println(new BCryptPasswordEncoder(11).encode("{noop}user123"));
        System.out.println(new BCryptPasswordEncoder(11).encode("{noop}user123"));
        System.out.println(new BCryptPasswordEncoder(11).encode("{noop}user123"));*/
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
       // org.springframework.security.core.userdetails.User fooUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));

        return user;
    }




//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepository.getUserByUsername(username);
//        UserBuilder builder = null;

//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//
//        return new MyUserPrincipal(user);




//
//
//
//        if (user != null) {
//            builder = org.springframework.security.core.userdetails.User.withUsername(username);
//            builder.password(new BCryptPasswordEncoder().encode("{noop}user123"));//"{noop}" +
//            builder.roles(user.getRole().toString());
//        } else {
//            throw new UsernameNotFoundException("User not found.");
//        }
//
//        return builder.build();
//    }
}

