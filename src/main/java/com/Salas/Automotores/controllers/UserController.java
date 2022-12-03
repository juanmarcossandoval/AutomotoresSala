package com.Salas.Automotores.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.controllers.dtos.UserDto;
import com.Salas.Automotores.controllers.requests.UserRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.User;
import com.Salas.Automotores.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws RecordNotExistException {
		userService.deleteUser(id);
		return new ResponseEntity<>(new ResponseDto(200, "Has been successfully deleted."), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> listUsers() throws ListNotFoundException {
		List<User> usersList = userService.getAll();
		List<UserDto> userDtosList = UserDto.mapToDtoList(usersList);
		return new ResponseEntity<>(userDtosList, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws RecordNotExistException, BadRequestException {
		User newUser = userService.createAdminUser(userRequest);
		return new ResponseEntity<>(new ResponseDto(200, "Created user: "+ newUser.getEmail()),HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> patchUpdateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) throws RecordNotExistException {
		User user = userService.patchUpdate(id, userRequest);
		UserDto	userDto = UserDto.mapToDto(user);		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
