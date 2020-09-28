package com.example.pkp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
              UserDetails operator = User.withDefaultPasswordEncoder()
                .username("op")
                .password("op1")
                .roles("OPERATOR")
                .build();

        return new InMemoryUserDetailsManager(operator);


    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
        .and()
        .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/courses/byDate").permitAll()
                .antMatchers(HttpMethod.GET, "/courses/fromTo").permitAll()
                .antMatchers(HttpMethod.GET, "/courses").permitAll()
                .antMatchers(HttpMethod.POST, "courses/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET,"/routes/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.POST,"/routes/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.POST,"/cities/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET,"/cities/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.POST,"/drivers/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET,"/drivers/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.POST,"/trains/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET,"/trains/**").hasRole("OPERATOR")



                .antMatchers("/console/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
                http.csrf().disable();
                http.headers().frameOptions().disable();
    }
}

