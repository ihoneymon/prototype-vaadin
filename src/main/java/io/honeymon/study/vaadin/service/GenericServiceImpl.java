package io.honeymon.study.vaadin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by jake on 26/04/2017.
 */
@Slf4j
@Transactional
public class GenericServiceImpl<E, ID extends Serializable, R extends JpaRepository<E, ID>> implements GenericService<E, ID, R> {
    @Autowired
    R repository;

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public E findById(ID id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(E e) {
        repository.delete(e);
    }

    @Override
    public void deleteById(ID id) {
        E e = findById(id);
        delete(e);
    }
}
