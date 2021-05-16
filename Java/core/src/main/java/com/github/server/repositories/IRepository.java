package com.github.server.repositories;

import org.hibernate.Session;

import java.util.Collection;

public interface IRepository<T> {

    Collection<T> findAll(Class<T> clz, Session session);

    T findBy(Class<T> clz, String field, Object value, Session session);

    Collection<T> findAllBy(Class<T> clz, String field, Object value, Session session);

    void save(Class<T> clz, T entity, Session session);

    void update(Class<T> clz, T entity, Session session);

    void delete(Class<T> clz, T entity, Session session);

}
