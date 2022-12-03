package com.Salas.Automotores.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.controllers.requests.OwnerRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Owner;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.OwnerRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;

@Service
public class OwnerService extends ModelBaseServiceImpl<Owner, Long>{

	@Autowired
	private OwnerRepository ownerRepository;
	
	public OwnerService(BaseRepository<Owner, Long> baseRepository) {
		super(baseRepository);
	}
	
	public Owner createOwner(OwnerRequest ownerRequest) throws BadRequestException {
		if (isBadRequest(ownerRequest)) throw new BadRequestException("firstname, lastname, phone and email are required..");
		Optional<Owner> checkOwner = ownerRepository.findByEmail(ownerRequest.getEmail());
		if (checkOwner.isPresent()) throw new BadRequestException("Registered email");
		Owner newOwner = new Owner();
		newOwner.setEmail(ownerRequest.getEmail());
		newOwner.setPhone(ownerRequest.getPhone());
		newOwner.setFirstName(ownerRequest.getFirstName());
		newOwner.setLastName(ownerRequest.getLastName());
		newOwner.setDni(ownerRequest.getDni());
		return ownerRepository.save(newOwner);
	}
	
	private boolean isBadRequest(OwnerRequest request) {
		return request.getEmail() == null || request.getEmail().isEmpty() ||
				request.getPhone() == null || request.getPhone().isEmpty() ||
				request.getFirstName() == null || request.getFirstName().isEmpty() ||
				request.getLastName() == null || request.getLastName().isEmpty() ||
				request.getDni() == null || request.getDni().isEmpty();
	}
	
	public void deleteOwner(Long id) throws RecordNotExistException {
		try {
			this.deleteById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Owner don´t exist");
		}
	}
	
	public List<Owner> getAll() throws ListNotFoundException {
		List<Owner> list = ownerRepository.findAllByOrderByLastName();
		return Optional.ofNullable(list).orElseThrow(()-> new ListNotFoundException("No brands to list"));
	}
	
	public Owner patchOwner(Long id, OwnerRequest request) throws RecordNotExistException {
		Owner owner;
		try {
			owner = this.getById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Owner has not been found...");
		}
		
		if (request.getFirstName() != null && !request.getFirstName().isEmpty())
			owner.setFirstName(request.getFirstName());
		if (request.getLastName() != null && !request.getLastName().isEmpty())
			owner.setLastName(request.getLastName());
		if (request.getEmail() != null && !request.getEmail().isEmpty())
			owner.setEmail(request.getEmail());
		if (request.getPhone() != null && !request.getPhone().isEmpty())
			owner.setPhone(request.getPhone());
		if (request.getDni() == null && !request.getDni().isEmpty())
			owner.setDni(request.getDni());
		Owner update = this.update(owner);
		return update;
	}
}
