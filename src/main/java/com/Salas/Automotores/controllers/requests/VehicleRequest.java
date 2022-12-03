package com.Salas.Automotores.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
	
	private Integer kilometers;
	private Integer year;
	private String model;
	private String gasType; 
	private String color;
	private Integer price;
	private String status;
	private Long brandId;
	private Long ownerId;
	
}
