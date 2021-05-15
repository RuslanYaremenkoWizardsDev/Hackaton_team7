package com.github.server.repositories;

import org.hibernate.Session;

import java.util.Collection;

public interface IRepository<T> {

    Collection<T> findAll(Session session, Class<T> clz);

    T findBy(Session session, Class<T> clz, String field, Object value);

    void save(Session session, Class<T> clz, T entity);

    void update(Session session, Class<T> clz, T entity);

    void delete(Session session, Class<T> clz, T entity);

}
