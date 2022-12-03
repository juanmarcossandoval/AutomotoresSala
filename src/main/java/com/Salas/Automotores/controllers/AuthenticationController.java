package com.Salas.Automotores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Salas.Automotores.service.MyUserDetailsService;
import com.Salas.Automotores.service.UserService;
import com.Salas.Automotores.util.JwtUtil;
import com.Salas.Automotores.controllers.dtos.AuthenticationResponse;
import com.Salas.Automotores.controllers.dtos.UserDto;
import com.Salas.Automotores.controllers.requests.AuthenticationRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManagerBean;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authReq) throws RecordNotExistException, BadRequestException {		
		if (authReq.getUsername() == null || authReq.getPassword() == null )
			throw new BadRequestException("Password and Username are required");		
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authReq.getUsername());		
		if (userDetails == null) 
			throw new BadRequestException("User has not been found...");
		try {
			authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadRequestException("Incorrect password");
		}
		AuthenticationResponse auth = new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails));
		return new ResponseEntity<>(auth, HttpStatus.OK);		
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> authenticate() throws RecordNotExistException {
		User user = userService.getLoggedUser();
		UserDto userDto = UserDto.mapToDto(user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
