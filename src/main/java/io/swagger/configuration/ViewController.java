package io.swagger.configuration;

import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.api.SecurityController;
/**
 * Handles views
 */



@Controller
public class ViewController{

    @Autowired private SecurityController securityController;
    /**
     * redirects to landing / welcome / home page
     */
    @RequestMapping(value = {"", "/"})
    public String index() {
        return "redirect:/dashboard"; // change the url after the  '/' to change the index page
    }


    /**
     * Dashboard
     */
    @RequestMapping(value = {"/dashboard"})
    public String dashboard() {
        if (((User)securityController.currentUser()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return "redirect:/all-users";
        }
        return "/user-dashboard.html";
    }


    /**
     * Create accounts
     */
    @RequestMapping(value = {"/create-account"})
    public String createAccount() {
        return "/create-account.html";
    }


    /**
     * Create users
     */
    @RequestMapping(value = {"/create-user"})
    public String createUser() {
        return "/create-user.html";
    }


    /**
     * Login
     */
    @RequestMapping(value = {"/login"})
    public String login() {
        if (securityController.currentUser() == null){
            return "/login.html";
        }
        return "redirect:/";
    }

    /**
     * displays all users
     */
    @RequestMapping(value = {"/all-users"})
    public String allUsers() {
        return "/employee-search-users.html";
    }
}
