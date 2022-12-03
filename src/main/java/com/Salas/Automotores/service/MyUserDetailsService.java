package com.Salas.Automotores.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Salas.Automotores.model.MyUserDetails;
import com.Salas.Automotores.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	@Transactional(readOnly = true)
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByEmail(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User has not been found...");
		}
		
		return new MyUserDetails(user.get());
	}
}


