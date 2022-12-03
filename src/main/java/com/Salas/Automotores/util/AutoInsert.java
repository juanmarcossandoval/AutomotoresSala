package com.Salas.Automotores.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Salas.Automotores.model.Role;
import com.Salas.Automotores.model.User;
import com.Salas.Automotores.service.RoleService;
import com.Salas.Automotores.service.UserService;

@Component
public class AutoInsert implements CommandLineRunner {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Role role;
	private User user;

	@Value("${admin.firstname}")
	private String firstname;
	
	@Value("${admin.lastname}")
	private String lastname;
	
	@Value("${admin.email}")
	private String email;
	
	@Value("${admin.password}")
	private String password;

	@Override
	public void run(String... args) throws Exception {
		
		role = new Role();
		
		role = roleService.findByName("ADMIN");
		if (role == null) {
			role = new Role();
			role.setName("ADMIN");
			role.setDescription("admin user");
			roleService.create(role);

		}
		
		role = roleService.findByName("USER");
		if (role == null) {
			role = new Role();
			role.setName("USER");
			role.setDescription("default user");
			roleService.create(role);

		}

		user = new User();
		user = userService.findByEmail(email).orElse(null);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setPassword(passwordEncoder.encode(password));
			role = roleService.findByName("ADMIN");
			user.setRoleId(role);
			userService.create(user);
		}
		
	}
}
