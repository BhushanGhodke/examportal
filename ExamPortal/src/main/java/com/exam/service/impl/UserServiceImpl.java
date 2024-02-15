package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exception.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.reposiotry.RoleRepository;
import com.exam.reposiotry.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	  
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//creating user
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	
		User local=this.userRepository.findByUsername(user.getUsername());
		System.out.println(local);
		System.out.println("local data printing");
		
		if(local!=null) {
			//System.out.println("User is already there !!");
			throw  new UserFoundException("User is found in DB table");
		}else {
			//user create
			for(UserRole ur:userRoles ) {
				roleRepository.save(ur.getRole());
			}
			 
			  user.getUserRoles().addAll(userRoles);
			  local=this.userRepository.save(user);
			
		}
		return local;
	}

	//getting user by username
	@Override
	public User getUser(String username) {
		
		
		return this.userRepository.findByUsername(username);
	}

	
	//delete user by userId
	@Override
	public void deleteUser(Long userId) {
	
		 userRepository.deleteById(userId);
		
	}
}
