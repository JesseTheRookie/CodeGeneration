//package io.swagger.service;
//
//import io.swagger.config.SecurityConfig;
//import io.swagger.model.User;
//import io.swagger.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements IUserDetailsService {
//
//    @Autowired
//    private SecurityConfig securityConfig;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findUserByUsername(username);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return user;
//    }
//}
//
