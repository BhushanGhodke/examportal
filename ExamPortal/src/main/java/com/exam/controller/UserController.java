package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/test")
	public String test() {
		return "Welcome to backend api of Examportal";
	}

	@PostMapping("/create")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		
		//encoding password with bcryptpasswordencoder
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Set<UserRole>roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole  userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}
	
	//get User by using username
	@GetMapping("/{username}")
	public User getUser(@PathVariable ("username")
	String username) {
		 return userService.getUser(username);
	}
	
	
	//delete user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId ) {
		userService.deleteUser(userId);
	}
	
	//update

	
	
	/*  @ExceptionHandler(UserFoundException.class)
	  public ResponseEntity<?>exceptionHandler(UserFoundException e )	{
		  return ResponseEntity.ok(new UserFoundException("user found "));
	  }*/
	
}
