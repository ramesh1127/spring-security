package com.sra.springsecurity.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sra.springsecurity.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
