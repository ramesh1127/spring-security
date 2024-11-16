package com.sra.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sra.springsecurity.filter.JwtFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@SuppressWarnings("deprecation")
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
			System.out.println("entry 0");   
		 http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers("/api/auth/**", "/register").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
