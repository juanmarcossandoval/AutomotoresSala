package com.Salas.Automotores.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Salas.Automotores.controllers.dtos.VehicleDto;
import com.Salas.Automotores.controllers.requests.VehicleFilterRequest;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.model.Vehicle;
import com.Salas.Automotores.service.VehicleService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping
	public ResponseEntity<?> listFilteredVehicles(@RequestBody VehicleFilterRequest vehicleFilteredRequest) throws ListNotFoundException {
		List<Vehicle> vehicleList = vehicleService.getFilteredVehicles(vehicleFilteredRequest);
		List<VehicleDto> vehiclesDtosList = VehicleDto.mapToDtoList(vehicleList);
		return new ResponseEntity<>(vehiclesDtosList, HttpStatus.OK);
	}
	
}
