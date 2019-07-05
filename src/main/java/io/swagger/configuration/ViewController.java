package io.swagger.configuration;

import io.swagger.api.ApiException;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        if (securityController.currentUser().getRole().equals(User.RoleEnum.USER_EMPLOYEE) || securityController.currentUser().getRole().equals(User.RoleEnum.EMPLOYEE)){
            return "redirect:/all-users";
        }
        return "/user-dashboard.html";
    }

    /**
     * Employee tools
     */
    @RequestMapping(value = {"/employeeTools"})
    public String employeeTools() {
        if (securityController.currentUser().getRole().equals(User.RoleEnum.USER_EMPLOYEE) || securityController.currentUser().getRole().equals(User.RoleEnum.EMPLOYEE)){
            return "redirect:/all-users";
        }
        return "redirect:/dashboard";

    }


    /**
     * Create accounts
     */
    @RequestMapping(value = {"/create-accounts"})
    public String createAccount() {
        if (securityController.currentUser().getRole().equals(User.RoleEnum.USER)){
            return "redirect:/dashboard";
        }
        return "/create-account.html";
    }

    /**
     * Create transaction
     */
    @RequestMapping(value = {"/create-transaction"})
    public String createTransaction() {
        return "/create-transaction.html";
    }

    /**
     * Create transaction
     */
    @RequestMapping(value = {"/get-transactions"})
    public String getTransaction() {
        return "/get-transactions.html";
    }

    /**
     * Create users
     */
    @RequestMapping(value = {"/create-users"})
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
        if (securityController.currentUser().getRole().equals(User.RoleEnum.USER)){
            return "/user-dashboard.html";
        }
        return "/employee-search-users.html";
    }

    /**
     * Get accounts in employee interface
     */
    @RequestMapping(value = {"/employee-get-accounts"})
    public String employeeGetAllAccounts() {
        if (securityController.currentUser().getRole().equals(User.RoleEnum.USER)){
            return "redirect:/dashboard";
        }
        return "/employee-user-accounts.html";
    }
}
