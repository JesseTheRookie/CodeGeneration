package io.swagger.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


public interface IAuthenticationFacade {
    Authentication getAuthentication();
}

