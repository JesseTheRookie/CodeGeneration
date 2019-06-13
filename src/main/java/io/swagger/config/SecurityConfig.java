package io.swagger.config;
/*
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
                .withUser("user")
                .password("{noop}password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()// Everybody can see root
                .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN") // Only Admin can POST
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("ADMIN") // Only Admin can POST
                .antMatchers(HttpMethod.GET,"/users/**").permitAll() // All users can GET
                .antMatchers(HttpMethod.GET,"/accounts/**").permitAll() // All users can GET
                .antMatchers(HttpMethod.GET,"/transactions/**").permitAll() // All users can GET
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll(); // The login page can be seen by everybody
    }
}
*/