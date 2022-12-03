package com.Salas.Automotores.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String dni;
	
}
	
	
