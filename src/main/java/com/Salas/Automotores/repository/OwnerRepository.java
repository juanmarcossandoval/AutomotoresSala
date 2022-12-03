package com.Salas.Automotores.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.Salas.Automotores.model.Owner;

@Repository
public interface OwnerRepository extends BaseRepository<Owner, Long>{
	
	Optional<Owner> findByEmail(String email);
	
	List<Owner> findAllByOrderByLastName();
	
}
