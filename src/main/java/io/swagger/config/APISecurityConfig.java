package io.swagger.config;

import io.swagger.filter.ApiKeyAuthFilter;
import io.swagger.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(2)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    private ApiKeyRepository keyRepository;

    public APISecurityConfig(ApiKeyRepository keyRepository){
        this.keyRepository = keyRepository;
    }

    @Value("X-AUTHTOKEN")
    private String headerName;

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();

            if(!keyRepository.findById(principal).isPresent()){
                throw new BadCredentialsException("API Key was not found");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });


        httpSecurity
                .antMatcher("/users/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter).authorizeRequests()
                .anyRequest().authenticated();
    }
}
