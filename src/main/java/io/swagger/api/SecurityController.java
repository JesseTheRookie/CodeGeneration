package io.swagger.api;

import io.swagger.config.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class SecurityController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public String currentUserName(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}
