package com.Salas.Automotores.service;

import java.util.List;

import com.Salas.Automotores.model.ModelBase;

public interface ModelBaseService <E extends ModelBase, ID > {

    public E create(E entity) throws Exception;
    public List<E> getAll() throws Exception;
    public E getById(ID id) throws Exception;
    public E update(E entity) throws Exception;
    public void deleteById(ID id) throws Exception;
}
