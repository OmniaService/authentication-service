package com.omnia.authenticationservice.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Collection;

@NoRepositoryBean
interface BaseRepository<T, K extends Serializable> extends Repository<T, K> {

    T save (T entity);

    Collection<T> findAll();

}
