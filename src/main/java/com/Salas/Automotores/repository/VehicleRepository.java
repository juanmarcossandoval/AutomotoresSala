package com.Salas.Automotores.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Salas.Automotores.model.Vehicle;

@Repository
public interface VehicleRepository extends BaseRepository<Vehicle, Long> {

	Optional<Vehicle> findVehicleById(Long id);
	
	@Query("SELECT v FROM Vehicle v "
			+ "WHERE (:minKilometers is null or v.kilometers >= minKilometers) "
			+ "AND (:maxKilometers is null or v.kilometers <= :maxKilometers) "
			+ "AND (:yearFrom is null or v.year >= :yearFrom) "
			+ "AND (:yearTo is null or v.year <= :yearTo) "
			+ "AND (:model is null or v.model = :model) "
			+ "AND (:gasType is null or v.gasType = :gasType) "
			+ "AND (:color is null or v.color = :color) "
			+ "AND (:priceFrom is null or v.price >= :priceFrom) "
			+ "AND (:priceTo is null or v.price <= :priceTo) "
			+ "AND (:status is null or v.status = :status) "
			+ "AND (:brandId is null or v.brandId.id = :brandId ) "
			+ "ORDER BY v.price ASC")
	List<Vehicle> filteredVehicles(@Param("minKilometers")Integer minKilometers,
			@Param("maxKilometers")Integer maxKilometers,
			@Param("yearFrom")Integer yearFrom,
			@Param("yearTo")Integer yearTo,
			@Param("model")String model,
			@Param("gasType")String gasType, 
			@Param("color")String color,
			@Param("priceFrom")Integer priceFrom,
			@Param("priceTo")Integer priceTo,
			@Param("status")String status,
			@Param("brandId")Long brandId);
}
