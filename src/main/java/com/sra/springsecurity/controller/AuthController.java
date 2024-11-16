package com.sra.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sra.springsecurity.config.CustomUserDetailsService;
import com.sra.springsecurity.model.util.JwtUtil;
import com.sra.springsecurity.pojo.UserRequest;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> postMethodName(@RequestBody UserRequest userRequest) {
		
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(userRequest.getUsername());
		String token=jwtUtil.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
}

