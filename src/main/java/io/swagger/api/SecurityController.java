package io.swagger.api;

import io.swagger.config.IAuthenticationFacade;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {


   @Autowired private IAuthenticationFacade authenticationFacade;
   @Autowired private UserRepository userRepository;

    public String currentUserName(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    @ResponseBody
    public Object currentUser(){
        String principal = ((User)authenticationFacade.getAuthentication().getPrincipal()).getUsername();
        Object user = userRepository.getUserByName(principal);
        return user;

//        userName = ((Principal)authentication.getPrincipal()).getName();
//
//    }else {
//        userName = ((User)authentication.getPrincipal()).getUsername();
//    }
    }
}
