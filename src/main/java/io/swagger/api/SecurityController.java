package io.swagger.api;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SecurityController {

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    public SecurityController(HttpServletRequest request) {
        this.request = request;
    }


    public String currentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public int currentUserId() {
        return userRepository.getUserByUsername(currentUserName()).getId();
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public User currentUser() {
        String accept = request.getHeader("Accept");
        return userRepository.getUserByUsername(currentUserName()); // get userDetails by username
    }
}
