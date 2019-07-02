package io.swagger.config;

//import io.swagger.service.MyUserDetailsService;

import io.swagger.security.CustomAuthenticationProvider;
import io.swagger.service.IUserDetailsService;
import io.swagger.service.UserDetailsServiceImpl;
import io.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }

//    @Autowired
//    public IUserDetailsService userDetailsService;

//    private MyUserDetailsService userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService());//.passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider);
//        auth.inMemoryAuthentication()
//                .withUser("bank")
//                .password("{noop}bank123")
//                .roles("USER_EMPLOYEE")
//                .and()
//                .withUser("stefan")
//                .password("{noop}admin123")
//                .roles("USER_EMPLOYEE")
//                .and()
//                .withUser("jasper")
//                .password("{noop}appelmoes123")
//                .roles("USER_EMPLOYEE")
//                .and()
//                .withUser("gabie")
//                .password("{noop}frikandel123")
//                .roles("USER_EMPLOYEE")
//                .and()
//                .withUser("jesse")
//                .password("{noop}niks123")
//                .roles("USER_EMPLOYEE")
//                .and()
//                .withUser("bert")
//                .password("{noop}user123")
//                .roles("USER")
//                .and()
//                .withUser("ernie")
//                .password("{noop}user123")
//                .roles("EMPLOYEE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/accounts").hasAnyRole("USER_EMPLOYEE", "EMPLOYEE")
                .antMatchers("/transactions").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**", "/css/**", "/img/**", "/js/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").hasAnyRole("USER_EMPLOYEE", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
        http.csrf().disable();

//       http.headers().contentTypeOptions().disable();
//       http.headers().httpStrictTransportSecurity().disable();//.includeSubDomains(true).maxAgeInSeconds(31536000);//.includeSubdomains(true).maxAgeSeconds(31536000);
    }

}
