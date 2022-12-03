package com.Salas.Automotores.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFilterRequest {
	
	private Integer minKilometers;
	private Integer maxKilometers;
	private Integer yearFrom;
	private Integer yearTo;
	private String model;
	private String gasType; 
	private String color;
	private Integer priceFrom;
	private Integer priceTo;
	private String status;
	private Long brandId;
	
}
