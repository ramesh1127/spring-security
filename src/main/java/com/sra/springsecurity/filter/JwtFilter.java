package com.sra.springsecurity.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sra.springsecurity.config.CustomUserDetailsService;
import com.sra.springsecurity.model.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("entry 1");
		String header = request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(header!=null && header.startsWith("Bearer ")) {
			token=header.substring(7);
			username=jwtUtil.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        
		}
		
		filterChain.doFilter(request, response);
	}

}
