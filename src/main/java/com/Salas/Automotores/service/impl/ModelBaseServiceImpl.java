package com.Salas.Automotores.service.impl;

import java.util.List;
import java.util.Optional;

import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.model.ModelBase;
import com.Salas.Automotores.repository.BaseRepository;
import com.Salas.Automotores.service.ModelBaseService;

public abstract class ModelBaseServiceImpl<E extends ModelBase,ID > implements ModelBaseService<E,ID> {

    protected BaseRepository<E,ID> baseRepository;

    public ModelBaseServiceImpl(BaseRepository<E,ID> baseRepository) {

        this.baseRepository = baseRepository;
    }

    @Override
    public E create(E entity) {
        E e =baseRepository.save(entity);
        return e;
    }

    @Override
    public List<E> getAll() throws ListNotFoundException{
        List<E> list = baseRepository.findAll();
        return Optional.ofNullable(list).orElseThrow(() -> new ListNotFoundException("No record to list"));
    }

    @Override
    public E getById(ID id) throws RecordNotExistException {
        Optional<E> e = baseRepository.findById(id);
        return e.orElseThrow(() -> new RecordNotExistException("The record was not found "+id));
    }

    @Override
    public E update(E entity) {
        E e= baseRepository.save(entity);
        return e;
    }

    @Override
    public void deleteById(ID id) throws RecordNotExistException {
        Optional<E> e = baseRepository.findById(id);
        e.orElseThrow(() -> new RecordNotExistException("Record was not found"));
        baseRepository.delete(e.get());
    }

}
