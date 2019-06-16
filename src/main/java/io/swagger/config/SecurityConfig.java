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
                .withUser("jasper")
                .password("{noop}appelmoes123")
                .roles("USER_EMPLOYEE")
                .and()
                .withUser("gabie")
                .password("{noop}frikandel123")
                .roles("USER_EMPLOYEE")
                .and()
                .withUser("jesse")
                .password("{noop}niks123")
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

        http.authorizeRequests()
                .antMatchers("/accounts", "/transactions", "/deposits", "/withdrawals").hasAnyRole("USER_EMPLOYEE", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/users/**","/css/**", "/img/**", "/js/**").permitAll()
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
