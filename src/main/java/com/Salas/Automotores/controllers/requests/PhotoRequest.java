package com.Salas.Automotores.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequest {
	
	private String content;
	private Long vehicleId;
	
}
