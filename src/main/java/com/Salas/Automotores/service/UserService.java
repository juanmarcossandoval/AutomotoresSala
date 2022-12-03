package com.Salas.Automotores.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.controllers.requests.UserRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Role;
import com.Salas.Automotores.model.User;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.UserRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;

@Service
public class UserService extends ModelBaseServiceImpl<User, Long> {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleService roleService;

    public UserService(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }

    public Optional<User> findByEmail(String email) {
    	return userRepository.findUserByEmail(email);
    }

    public User getLoggedUser() throws RecordNotExistException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(() -> new RecordNotExistException("User has not been found..."));
        return user;
    }
    
    public List<User> getAll() throws ListNotFoundException {
    	List<User> list = userRepository.findAllByOrderByCreateAt();
    	return Optional.ofNullable(list).orElseThrow(() -> new ListNotFoundException("No users to list"));
    }
    
    public User patchUpdate(Long id, UserRequest userRequest) throws RecordNotExistException {

        User user;
		try {
			user = this.getById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("User has not been found...");
		}

        if (userRequest.getFirstName() != null && !userRequest.getFirstName().isEmpty())
            user.setFirstName(userRequest.getFirstName());
        if (userRequest.getLastName() != null && !userRequest.getLastName().isEmpty())
            user.setLastName(userRequest.getLastName());
        if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) 
            user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User update = this.update(user);
		return update;
    }

    public void deleteUser(Long Id) throws RecordNotExistException {
        try {
			this.deleteById(Id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("User has not been found...");
		}
    }

    public User createAdminUser(UserRequest userRequest) throws BadRequestException {
    	if (isBadRequest(userRequest)) 
    		throw new BadRequestException("firstname, lastname, password and email are required..");
    	Optional<User> checkUser = userRepository.findUserByEmail(userRequest.getEmail());
    	if (checkUser.isPresent()) throw new BadRequestException("Registered email");
    	User newUser = new User();
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setPassword(userRequest.getPassword());
		newUser.setEmail(userRequest.getEmail());
        Role role = new Role();
        role = roleService.findByName("ADMIN");
        newUser.setRoleId(role);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }
    
    private boolean isBadRequest(UserRequest request) {
    	return request.getFirstName() == null || request.getFirstName().isEmpty() ||
    			request.getLastName() == null || request.getLastName().isEmpty() ||
    			request.getEmail() == null || request.getEmail().isEmpty() ||
    			request.getPassword() == null || request.getPassword().isEmpty();
    }

}
