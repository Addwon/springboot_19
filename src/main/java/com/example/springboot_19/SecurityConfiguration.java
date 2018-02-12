package com.example.springboot_19;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/")
                .access("hasAnyAuthority('USER')or hasAnyAuthority('ADMIN')")
                .antMatchers("/admin").access("hasAnyAuthority('ADMIN')")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
        .withUser("user").password("password").authorities("USER")
        .and()
        .withUser("dave").password("begreat").authorities("ADMIN");
    }
}

