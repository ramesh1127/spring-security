package com.sra.springsecurity.config;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sra.springsecurity.model.User;
import com.sra.springsecurity.model.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("entry 2");
		Optional<User> user = userRepo.findByUsername(username);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
					user.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
