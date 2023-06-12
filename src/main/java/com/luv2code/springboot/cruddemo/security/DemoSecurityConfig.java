package com.luv2code.springboot.cruddemo.security;

import java.net.http.HttpRequest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	
	
	//add support for jdbc.. NO hard code value
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		return new JdbcUserDetailsManager(dataSource);
		
	}
	
	
	
	@Bean
	public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests(configurer -> 
		configurer
		.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
		);
		
		
		//use http basic authentication
		
		http.httpBasic();
		
		//disable cross site request forgery(CSRF)
		
		//in general, not required for stateless REST APIs that use POST, PUT, DELETE, AND/OR PATCH 
		
		http.csrf().disable();
		
		return http.build();
		
	}
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER")
				.build();
		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john,mary,susan);
		
	}
	*/

}
