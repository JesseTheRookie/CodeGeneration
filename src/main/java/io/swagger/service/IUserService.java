package io.swagger.service;

import io.swagger.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User findByUsername(String username);
}
