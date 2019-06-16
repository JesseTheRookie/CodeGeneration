package io.swagger.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Handles views
 */
@Controller
public class ViewController{

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
     * Get users
     */
    @RequestMapping(value = {"/get-users"})
    public String getAllUsers() {
        return "/get-users.html";
    }


    /**
     * Get accounts
     */
    @RequestMapping(value = {"/get-accounts"})
    public String getAllAccounts() {
        return "/get-accounts.html";
    }


    /**
     * Login
     */
    @RequestMapping(value = {"/login"})
    public String login() {
        //ToDo check if user is logged in
        return "/login.html";
    }
}
