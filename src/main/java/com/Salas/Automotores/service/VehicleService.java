package com.Salas.Automotores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.controllers.requests.VehicleFilterRequest;
import com.Salas.Automotores.controllers.requests.VehicleRequest;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.Brand;
import com.Salas.Automotores.model.Owner;
import com.Salas.Automotores.model.Vehicle;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.BrandRepository;
import com.Salas.Automotores.repository.OwnerRepository;
import com.Salas.Automotores.repository.VehicleRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;

@Service
public class VehicleService extends ModelBaseServiceImpl<Vehicle, Long> {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;

	public VehicleService(BaseRepository<Vehicle, Long> baseRepository) {
		super(baseRepository);
	} 	
	
	public Vehicle createVehicle(VehicleRequest vehicleRequest) throws BadRequestException, RecordNotExistException {
		if (isBadRequest(vehicleRequest)) throw new BadRequestException("Kilometers, year, model, gasType, color, price, status, brandId and ownerId are required..");
		Optional<Brand> checkBrand = brandRepository.findById(vehicleRequest.getBrandId());
		Optional<Owner> checkOwner = ownerRepository.findById(vehicleRequest.getOwnerId());
		if (checkBrand.isEmpty() || checkOwner.isEmpty()) throw new RecordNotExistException("Brand or Owner don´t exist.");
		Vehicle newVehicle = new Vehicle();
		newVehicle.setKilometers(vehicleRequest.getKilometers());
		newVehicle.setYear(vehicleRequest.getYear());
		newVehicle.setModel(vehicleRequest.getModel());
		newVehicle.setGasType(vehicleRequest.getGasType());
		newVehicle.setColor(vehicleRequest.getColor());
		newVehicle.setPrice(vehicleRequest.getPrice());
		newVehicle.setStatus(vehicleRequest.getStatus());
		newVehicle.setBrandId(checkBrand.get());
		newVehicle.setOwnerId(checkOwner.get());
		newVehicle = vehicleRepository.save(newVehicle);
		return newVehicle;
		
	}
	
	private boolean isBadRequest(VehicleRequest request) {
		return request.getKilometers() == null || request.getYear() == null || request.getPrice() == null ||
				request.getModel() == null || request.getModel().isEmpty() ||
				request.getGasType() == null || request.getGasType().isEmpty() ||
				request.getColor() == null || request.getColor().isEmpty() ||
				request.getStatus() == null || request.getStatus().isEmpty() ||
				request.getBrandId() == null || request.getOwnerId() == null;
	}
	
	public void deleteVehicle(Long id) throws RecordNotExistException {
		try {
			this.deleteById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Vehicle don´t exist");
		}
	}
	
	public List<Vehicle> getFilteredVehicles(VehicleFilterRequest request) throws ListNotFoundException {
		List<Vehicle> list = vehicleRepository.filteredVehicles(
				request.getMinKilometers(),
				request.getMaxKilometers(),
				request.getYearFrom(),
				request.getYearTo(),
				request.getModel(),
				request.getGasType(), 
				request.getColor(),
				request.getPriceFrom(),
				request.getPriceTo(),
				request.getStatus(),
				request.getBrandId());
		return Optional.ofNullable(list).orElseThrow(()-> new ListNotFoundException("No owners to list"));
	}
	
	public Vehicle patchVehicle(Long id, VehicleRequest request) throws RecordNotExistException {
		Vehicle vehicle;
		try {
			vehicle = this.getById(id);
		} catch (RecordNotExistException e) {
			throw new RecordNotExistException("Vehicle has not been found...");
		}
		
		if (request.getBrandId() != null) {
			Optional<Brand> checkBrand = brandRepository.findById(request.getBrandId());
			if (checkBrand.isEmpty()) throw new RecordNotExistException("Brand has not been found...");
			vehicle.setBrandId(checkBrand.get());
		}
		
		if (request.getOwnerId() != null) {
			Optional<Owner> checkOwner  = ownerRepository.findById(request.getOwnerId());
			if (checkOwner.isEmpty()) throw new RecordNotExistException("Owner has not been found...");
			vehicle.setOwnerId(checkOwner.get());
		}
		
		if (request.getKilometers() != null) 
			vehicle.setKilometers(request.getKilometers());
		if (request.getYear() != null) 
			vehicle.setYear(request.getYear());
		if (request.getPrice() != null)
			vehicle.setPrice(request.getPrice());
		if (request.getModel() != null && !request.getModel().isEmpty())
			vehicle.setModel(request.getModel());
		if (request.getGasType() != null && !request.getGasType().isEmpty())
			vehicle.setGasType(request.getGasType());
		if (request.getColor() != null && !request.getColor().isEmpty())
			vehicle.setColor(request.getColor());
		if (request.getStatus() != null || !request.getStatus().isEmpty())
			vehicle.setStatus(request.getStatus());
				
		Vehicle update = this.update(vehicle);
		return update;
	}
}
