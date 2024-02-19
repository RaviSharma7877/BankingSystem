package com.RSstudies.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.RSstudies.models.Users;

public interface UsersService {
public Users addUser(Users customer);
	
	public Users login(String username);
	public Users updateUserDetails(int userId, String accountStatus);
	public Users getUserByUserId(int id);
	public List<Users> getAllUsers();
	public String deleteUser(int userId);
}
