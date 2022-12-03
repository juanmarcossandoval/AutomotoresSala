package com.Salas.Automotores.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Salas.Automotores.controllers.dtos.BrandDto;
import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.controllers.requests.BrandRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Brand;
import com.Salas.Automotores.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@PostMapping("/new")
	public ResponseEntity<?> createBrand(@RequestBody BrandRequest brandRequest) throws BadRequestException {
		Brand newBrand = brandService.createBrand(brandRequest);
		return new ResponseEntity<>(new ResponseDto(200, "Created brand: " + newBrand.getId()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id) throws RecordNotExistException {
		brandService.deleteBrand(id);
		return new ResponseEntity<>(new ResponseDto(200, "Has been successfully deleted."), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> listBrands() throws ListNotFoundException {
		List<Brand> brandsList = brandService.getAll();
		List<BrandDto> brandsDtoList = BrandDto.mapToDtoList(brandsList);
		return new ResponseEntity<>(brandsDtoList, HttpStatus.OK);
	}

}
