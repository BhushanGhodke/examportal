package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	private jwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private jwtAuthenticationFilter  jwtAuthenticationFilter;

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean(); 
	}
	
	@Override
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	

	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.cors()
		.disable()
		.authorizeRequests()
		.antMatchers("/generate-token","/user/","user/test")
		.permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class );
		
	
	}
	
	
	
	
	/*
		@Bean
		protected AuthenticationManager authenticationManagerBean() {
			
			return authenticationManagerBean();
			
		}
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider	 authProvider= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		
		return authProvider;
		
		}
		
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
			http.authorizeHttpRequests()
			.requestMatchers("/generate-token","/user/")
			.permitAll()
			.requestMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			 http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class );
			
			return http.build();
		}*/
	
	
}
