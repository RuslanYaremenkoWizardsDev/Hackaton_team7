package com.github.server.repository;

import com.github.server.repositories.IRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;

public class TestRepository<T> implements IRepository<T> {

    private final Collection<T> dataBase = new ArrayList<>();

    @Override
    public Collection<T> findAll(Session session) {
        return dataBase;
    }

    @Override
    public T findBy(String field, Object value, Session session) {
        return null;
    }

    @Override
    public Collection<T> findAllBy(String field, Object value, Session session) {
        return null;
    }

    @Override
    public void save(T entity, Session session) {

    }

    @Override
    public void update(T entity, Session session) {

    }

    @Override
    public void delete(T entity, Session session) {

    }
}
