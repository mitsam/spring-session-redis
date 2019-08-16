package com.lazyint.demo.spring_session_redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.lazyint.demo.spring_session_redis.security.AuthSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
	User.UserBuilder users = User.withDefaultPasswordEncoder();
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	manager.createUser(users.username("amit").password("samuel").roles("USER").build());
	return manager;

    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http
	.sessionManagement().maximumSessions(1)
	.and()
	.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
	.and()
	.csrf().disable()
	.authorizeRequests().anyRequest().authenticated()
	.and()
	.requestCache().requestCache(new NullRequestCache())
	.and()
	.formLogin().permitAll().successHandler(authSuccessHandler)
	.and()
        .logout()
        .permitAll();
    }
    // @formatter:on

}
