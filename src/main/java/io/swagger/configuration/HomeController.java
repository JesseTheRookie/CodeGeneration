package io.swagger.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {


    //@ResponseBody
    @RequestMapping(value = "/login")
    public String index() {
        //System.out.println("swagger-ui.html");
        //return "redirect:swagger-ui.html";
        String string =  "index.html";
        return string;
    }
}
