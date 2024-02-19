package com.RSstudies.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RSstudies.Repository.UserRepository;
import com.RSstudies.models.Role;
import com.RSstudies.models.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwE;

	@Override
	public Users addUser(Users user) {
		user.setPassword(passwE.encode(user.getPassword()));
		user.setRole(Role.USER);
		return userRepo.save(user);
	}
	
	@Override
	public Users login(String username) {
		return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
	}

	@Override
	public Users updateUserDetails(int userId, String accountStatus) {
		Users user = userRepo.findById(userId).orElseThrow();
		return user;
	}
	@Override
	public Users getUserByUserId(int userId) {
		// TODO Auto-generated method stub
		Users user = userRepo.findById(userId).orElseThrow();
		return user;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public String deleteUser(int userId) {
		Users user = userRepo.findById(userId).orElseThrow();
		userRepo.delete(user);
		return "User deleted successfully";
	}

}
