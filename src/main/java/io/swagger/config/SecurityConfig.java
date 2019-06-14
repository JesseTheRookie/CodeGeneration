package io.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("bank")
                .password("{noop}bank123")
                .roles("USER_EMPLOYEE")
                .and()
                .withUser("stefan")
                .password("{noop}admin123")
                .roles("USER_EMPLOYEE")
                .and()
                .withUser("bert")
                .password("{noop}user123")
                .roles("USER")
                .and()
                .withUser("ernie")
                .password("{noop}user123")
                .roles("EMPLOYEE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()

                .antMatchers("/accounts","/transactions","/deposits","/withdrawals").hasAnyRole("USER_EMPLOYEE","EMPLOYEE")
                .antMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();

                /*
                .antMatchers("/").permitAll()// Everybody can see root
                .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN") // Only Admin can POST
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("ADMIN") // Only Admin can POST
                .antMatchers(HttpMethod.GET,"/users/**").permitAll() // All users can GET
                .antMatchers(HttpMethod.GET,"/accounts/**").permitAll() // All users can GET
                .antMatchers(HttpMethod.GET,"/transactions/**").permitAll() // All users can GET
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll(); // The login page can be seen by everybody*/
    }
}
