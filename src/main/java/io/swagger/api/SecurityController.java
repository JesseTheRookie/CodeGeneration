package io.swagger.api;

import io.swagger.config.IAuthenticationFacade;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {


    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;

    public String currentUserName() {
        Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
        return ((User)principal).getUsername();
    }

    public int currentUserId() {
        Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
        return ((User)principal).getId();
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public Object currentUser() {
        Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
        if (principal.equals("anonymousUser")) { // check if principal is anonymousUser
            return null; // return null
        } else {
            String username = ((User)principal).getUsername(); // get username from principal
            Object user = userRepository.getUserByUsername(username); // get user by username from principal
            return user; // return user
        }
    }
}
