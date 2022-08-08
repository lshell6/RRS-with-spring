package com.rewards.backend;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rewards.backend.service.EmployeeDetailsService;
import com.rewards.backend.service.ManagerDetailsService;

@Configuration
public class ApiSecurityConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	@Autowired
	private ManagerDetailsService managerDetailsService;
	
	protected void configure(HttpHeaderSecurityFilter http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/employee/login").authenticated()
			.antMatchers(HttpMethod.GET, "/manager/login").authenticated()
			.antMatchers(HttpMethod.GET, "/employee/username").authenticated()
			.antMatchers(HttpMethod.GET, "/employee/profile").authenticated()
			.antMatchers(HttpMethod.GET, "/manager/username").authenticated()
			.antMatchers(HttpMethod.GET, "/manager/profile").authenticated()
			.anyRequest().permitAll()
			//.antMatchers(HttpMethod.GET, "/customers").authenticated()
			//.antMatchers("/products").authenticated()
			//.antMatchers("/products/category/{cid}").hasAnyAuthority("ADMIN")
			.and().httpBasic()
			.and().csrf().disable();
	}
 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* 
		 *  auth.inMemoryAuthentication()
		
				.withUser("harry")
				.password(getPasswordEncoder().encode("potter123"))
				.roles("ADMIN")
				.and()
				.withUser("ronald")
				.password(getPasswordEncoder().encode("weasley123"))
				.roles("EXEC");
				*/
		auth.authenticationProvider(getCustomProvider());
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	private DaoAuthenticationProvider getCustomProvider(){
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(employeeDetailsService);
		//or manager
		return dao;
	}
}
