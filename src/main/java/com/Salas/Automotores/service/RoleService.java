package com.Salas.Automotores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.repository.RoleRepository;
import com.Salas.Automotores.service.impl.ModelBaseServiceImpl;
import com.Salas.Automotores.model.Role;

@Service
public class RoleService extends ModelBaseServiceImpl<Role, Long> {

	@Autowired
	private RoleRepository roleRepository;

	public RoleService(BaseRepository<Role, Long> baseRepository) {
		super(baseRepository);
	}

	public Role findByName(String name) {
		Role role = roleRepository.findByName(name).orElse(null);
		return role;
	}
}
