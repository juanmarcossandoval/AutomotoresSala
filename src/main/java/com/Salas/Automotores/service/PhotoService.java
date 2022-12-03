package com.Salas.Automotores.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.controllers.requests.PhotoRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Photo;
import com.Salas.Automotores.model.Vehicle;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.PhotoRepository;
import com.Salas.Automotores.repository.VehicleRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;

@Service
public class PhotoService extends ModelBaseServiceImpl<Photo, Long>{
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	public PhotoService(BaseRepository<Photo, Long> baseRepository) {
		super(baseRepository);
	}
	
	public Photo createPhoto(PhotoRequest photoRequest) throws RecordNotExistException, BadRequestException {
		if (photoRequest.getContent() == null || photoRequest.getContent().isEmpty()) throw new BadRequestException("Photo content don't be empty");
		if (photoRequest.getVehicleId() == null) throw new BadRequestException("Vehicle id don't be empty");
		Optional<Vehicle> checkVehicle = vehicleRepository.findById(photoRequest.getVehicleId());
		if (checkVehicle.isEmpty()) throw new RecordNotExistException("Vehicle don´t exist");
		Photo newPhoto = new Photo();
		newPhoto.setContent(photoRequest.getContent());
		newPhoto.setVehicleId(checkVehicle.get());
		newPhoto = photoRepository.save(newPhoto);
		return newPhoto;
	}
	
	public void deletePhoto(Long id) throws RecordNotExistException  {
		try {
			this.deleteById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Photo don´t exist");
		}
	}
	
}
