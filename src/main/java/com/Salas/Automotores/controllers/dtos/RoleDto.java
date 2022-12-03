package com.Salas.Automotores.controllers.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.Salas.Automotores.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private Long id;
	private String name;
    private String description;

    @Autowired
    private static ModelMapper mapper = new ModelMapper();

    public static RoleDto mapToDto(Role r) {
        RoleDto roleDto = mapper.map(r, RoleDto.class);
        return roleDto;
    }

}
