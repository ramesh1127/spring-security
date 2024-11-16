package com.sra.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sra.springsecurity.model.User;
import com.sra.springsecurity.model.repo.UserRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	
		@Autowired
		private UserRepo userRepo;
		
		@PostMapping("/create")
		public ResponseEntity<User> postMethodName(@RequestBody User user) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(user));
		}
		

}
