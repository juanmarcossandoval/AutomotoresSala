package com.Salas.Automotores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.Salas.Automotores.model.ModelBase;

@NoRepositoryBean
public interface BaseRepository <E extends ModelBase,ID> extends JpaRepository<E,ID>{
}
