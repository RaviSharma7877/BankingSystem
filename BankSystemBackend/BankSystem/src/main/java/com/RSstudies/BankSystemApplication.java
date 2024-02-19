package com.RSstudies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.RSstudies.Repository.UserRepository;
import com.RSstudies.models.Role;
import com.RSstudies.models.Users;



@SpringBootApplication
public class BankSystemApplication {

	@Autowired
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}

	public void run(String... args) {
		Users adminAccount = userRepo.findByRole(Role.ADMIN);
		
		if(adminAccount == null) {
			Users usrs = new Users();
			usrs.setEmail("admin@gmail.com");
			usrs.setFirstName("admin");
			usrs.setLastName("admin");
			usrs.setUsername("admin");
			usrs.setRole(Role.ADMIN);
			usrs.setPassword(new BCryptPasswordEncoder().encode("admin"));
			
			userRepo.save(usrs);
		}
	}
}
