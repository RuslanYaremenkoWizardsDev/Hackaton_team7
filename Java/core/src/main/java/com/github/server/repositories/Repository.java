package com.github.server.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

public class Repository<T> implements IRepository<T> {

    @Override
    public Collection<T> findAll(Session session, Class<T> clz) {
        try (session) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(clz);
            criteria.from(clz);
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findBy(Session session, Class<T> clz, String field, Object value) {
        try (session) {
            Criteria criteria = session.createCriteria(clz);
            return (T) criteria.add(Restrictions.eq(field, value)).uniqueResult();
        }
    }

    @Override
    public void save(Session session, Class<T> clz, T entity) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.save(entity);
            tx1.commit();
        }
    }

    @Override
    public void update(Session session, Class<T> clz, T entity) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.update(entity);
            tx1.commit();
        }
    }

    @Override
    public void delete(Session session, Class<T> clz, T entity) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.delete(entity);
            tx1.commit();
        }
    }
}
