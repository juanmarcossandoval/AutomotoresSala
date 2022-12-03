package com.Salas.Automotores.controllers.dtos;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.Salas.Automotores.model.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String dni;
	
	@Autowired
	private static ModelMapper mapper = new ModelMapper();
	
	public static OwnerDto mapToDto(Owner o) {
		OwnerDto ownerDto = mapper.map(o, OwnerDto.class);
		return ownerDto;
	}
	
	public static List<OwnerDto> mapToDtoList(List<Owner> ownerList) {
		List<OwnerDto> owners = new ArrayList<>();
		for (Owner o : ownerList) {
			owners.add(mapToDto(o));
		}
		return owners;
	}

}
