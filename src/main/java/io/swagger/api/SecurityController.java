package io.swagger.api;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
//import io.swagger.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

//    @Autowired
//    private IUserService userService;

//    @Autowired
//    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    public SecurityController(HttpServletRequest request) {
        this.request = request;
    }

    public String currentUserName() {
//        Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
//        return ((User)principal).getUsername();
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public int currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
        return ((User)principal).getId();
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public UserDetails currentUser() {
        String accept = request.getHeader("Accept");
//       // Object principal = authenticationFacade.getAuthentication().getPrincipal(); // get principal
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//
//        if (username.equals("anonymousUser")) { // check if principal is anonymousUser
//            return null; // return null
//        }
//
//        User user = userRepository.findUserByUsername(username);
//        //else {
 //           String username = ((User)principal).getUsername(); // get username from principal
            User user = userRepository.getUserByUsername(username); // get user by username from principal
//           // return user; // return user
//      // }
        return user;
    }
}
