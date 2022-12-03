package com.Salas.Automotores.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.Salas.Automotores.model.Brand;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Long>{
	
	Optional<Brand> findByName(String name);
	
	List<Brand> findAllByOrderByName();
	
}
