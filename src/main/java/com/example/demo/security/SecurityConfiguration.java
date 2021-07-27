package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Create User - root/root
    @Autowired
    public String[] configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        String[] roles = { "USER", "ADMIN" };
        auth.inMemoryAuthentication().withUser("root").password("{noop}root").roles(roles[0], roles[1]);
        return roles;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/**")
                .access("hasRole('USER')");
        super.configure(http);
    }
}