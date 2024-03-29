package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.exam.exception.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

//@CrossOrigin("*")
@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("Starting Code ");
			try {
				User user = new User();
		
				user.setFirstName("Bhushan");
				user.setLastName("Ghodke");
				user.setUsername("BhushanGhodke");
				user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
				user.setEmail("abc@gamil.com");
				user.setProfile("default.png");
				user.setMobile("9762960194");
				Role role1 = new Role();
				role1.setRoleId(44L);
				role1.setRoleName("ADMIN");
		
				Set<UserRole> userRoleSet = new HashSet<>();
				UserRole userRole = new UserRole();
				userRole.setRole(role1);
				userRole.setUser(user);
		
				userRoleSet.add(userRole);
		
				User user1 = this.userService.createUser(user, userRoleSet);
				System.out.println(user1.getUsername());
			} catch (UserFoundException e) {
				e.printStackTrace();
			}
	}
}
