package com.Salas.Automotores.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Salas.Automotores.model.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

	Optional<Role> findByName(String name);

}
