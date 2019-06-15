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
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController{

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {

        String string =  "/index.html";
        return string;
    }
}
