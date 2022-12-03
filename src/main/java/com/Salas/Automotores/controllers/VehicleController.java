package com.Salas.Automotores.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.controllers.dtos.VehicleDto;
import com.Salas.Automotores.controllers.requests.VehicleRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Vehicle;
import com.Salas.Automotores.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> listVehicles() throws ListNotFoundException {
		List<Vehicle> vehicleList = vehicleService.getAll();
		List<VehicleDto> vehiclesDtosList = VehicleDto.mapToDtoList(vehicleList);
		return new ResponseEntity<>(vehiclesDtosList, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicleRequest) throws BadRequestException, RecordNotExistException {
		Vehicle newVehicle = vehicleService.createVehicle(vehicleRequest);
		return new ResponseEntity<>(new ResponseDto(200, "Created vehicle: "+ newVehicle.getId()),HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchUpdateVehicle(@PathVariable("id") Long id, @RequestBody VehicleRequest vehicleRequest) throws RecordNotExistException {
		Vehicle vehicle = vehicleService.patchVehicle(id, vehicleRequest);
		VehicleDto vehicleDto = VehicleDto.mapToDto(vehicle);		
		return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id) throws RecordNotExistException {
		vehicleService.deleteVehicle(id);
		return new ResponseEntity<>(new ResponseDto(200, "Has been successfully deleted."), HttpStatus.OK);
	}
}
