package com.Salas.Automotores.controllers.dtos;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.Salas.Automotores.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
	
	private Long id;
	private Integer Kilometers;
	private Integer year;
	private String model;
	private String gasType; 
	private String color;
	private Integer price;
	private String status;
	private BrandDto brandId;
	private OwnerDto ownerId;
	private List<PhotoDto> photos;
	
	@Autowired
	private static ModelMapper mapper = new ModelMapper();
	
	public static VehicleDto mapToDto(Vehicle v) {
		VehicleDto vehicleDto = mapper.map(v, VehicleDto.class);
		return vehicleDto;
	}
	
	public static List<VehicleDto> mapToDtoList(List<Vehicle> vehiclesList) {
		List<VehicleDto> vehicles = new ArrayList<>();
		for (Vehicle v : vehiclesList) {
			vehicles.add(mapToDto(v));
		}
		return vehicles;
	}
}
