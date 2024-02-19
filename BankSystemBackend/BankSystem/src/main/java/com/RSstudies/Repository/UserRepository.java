package com.RSstudies.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RSstudies.models.Role;
import com.RSstudies.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByEmail(String email);
	
	Users findByRole(Role role);

	Optional<Users> findByUsername(String username);
}
