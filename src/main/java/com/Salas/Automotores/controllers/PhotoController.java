package com.Salas.Automotores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.controllers.requests.PhotoRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Photo;
import com.Salas.Automotores.service.PhotoService;

@RestController
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@PostMapping("/new")
	public ResponseEntity<?> createPhoto(@RequestBody PhotoRequest photoRequest) throws RecordNotExistException, BadRequestException {
		Photo newPhoto = photoService.createPhoto(photoRequest);		
		return new ResponseEntity<>(new ResponseDto(200,"Created photo: " + newPhoto.getId()),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePhoto(@PathVariable("id") Long id) throws RecordNotExistException {
	    photoService.deletePhoto(id);
		return new ResponseEntity<>(new ResponseDto(200, "Has been successfully deleted."), HttpStatus.OK);
	}
}
