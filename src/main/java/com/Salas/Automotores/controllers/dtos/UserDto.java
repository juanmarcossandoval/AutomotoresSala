package com.Salas.Automotores.controllers.dtos;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.Salas.Automotores.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	
	private String firstName;

	private String lastName;

	private String email;
	
	private RoleDto roleId;

	@Autowired
	private static ModelMapper mapper = new ModelMapper();

	public static UserDto mapToDto(User u) {
		UserDto userDto = mapper.map(u, UserDto.class);
		return userDto;
	}

	public static List<UserDto> mapToDtoList(List<User> userList) {
		List<UserDto> users = new ArrayList<>();
		for (User u : userList) {
			users.add(mapToDto(u));
		}
		return users;
	}

}
