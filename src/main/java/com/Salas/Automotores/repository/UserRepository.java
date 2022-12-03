package com.Salas.Automotores.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.Salas.Automotores.model.User;

@Repository
public interface UserRepository extends BaseRepository<User,Long> {

	Optional<User> findUserByEmail(String email);

	List<User> findAllByOrderByCreateAt();
	
}
