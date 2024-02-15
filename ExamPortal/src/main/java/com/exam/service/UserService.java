package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	//Creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by using username
	public User getUser(String username);
	
	//delete by user id
	public void deleteUser(Long userId);
}
