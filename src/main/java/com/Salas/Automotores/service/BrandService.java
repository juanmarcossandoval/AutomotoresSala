package com.Salas.Automotores.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.controllers.requests.BrandRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Brand;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.BrandRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;

@Service
public class BrandService extends ModelBaseServiceImpl<Brand, Long> {

	@Autowired
	private BrandRepository brandRepository;
	
	public BrandService(BaseRepository<Brand, Long> baseRepository) {
		super(baseRepository);
	}
	
	public Brand createBrand(BrandRequest brandRequest) throws BadRequestException {
		if (brandRequest.getName() == null || brandRequest.getName().isEmpty()) throw new BadRequestException("Brand name don't be empty");
		Optional<Brand> checkBrand = brandRepository.findByName(brandRequest.getName());
		if (checkBrand.isPresent()) throw new BadRequestException("Brand already exist");
		Brand newBrand = new Brand();
		newBrand.setName(brandRequest.getName());
		return brandRepository.save(newBrand);
	}
	
	public List<Brand> getAll() throws ListNotFoundException {
		List<Brand> list = brandRepository.findAllByOrderByName();
		return Optional.ofNullable(list).orElseThrow(()-> new ListNotFoundException("No brands to list"));
	}
	
	public void deleteBrand(Long id) throws RecordNotExistException {
		try {
			this.deleteById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Brand don´t exist");
		}
	}
}
