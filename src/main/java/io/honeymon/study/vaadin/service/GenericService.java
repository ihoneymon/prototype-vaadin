package io.honeymon.study.vaadin.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by jake on 26/04/2017.
 */
public interface GenericService<E, ID extends Serializable, R extends JpaRepository<E, ID>> {
    E save(E e);

    E findById(ID id);

    void delete(E e);

    void deleteById(ID id);
}
