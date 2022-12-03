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
import com.Salas.Automotores.controllers.dtos.OwnerDto;
import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.controllers.requests.OwnerRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Owner;
import com.Salas.Automotores.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> listOwners() throws ListNotFoundException {
		List<Owner> ownersList = ownerService.getAll();
		List<OwnerDto> ownersDtosList = OwnerDto.mapToDtoList(ownersList);
		return new ResponseEntity<>(ownersDtosList, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> createOwner(@RequestBody OwnerRequest ownerRequest) throws BadRequestException {
		Owner newOwner = ownerService.createOwner(ownerRequest);
		return new ResponseEntity<>(new ResponseDto(200, "Created owner: "+ newOwner.getId()),HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchUpdateOwner(@PathVariable("id") Long id, @RequestBody OwnerRequest ownerRequest) throws RecordNotExistException {
		Owner owner = ownerService.patchOwner(id, ownerRequest);
		OwnerDto ownerDto = OwnerDto.mapToDto(owner);		
		return new ResponseEntity<>(ownerDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) throws RecordNotExistException {
		ownerService.deleteOwner(id);
		return new ResponseEntity<>(new ResponseDto(200, "Has been successfully deleted."), HttpStatus.OK);
	}
}
