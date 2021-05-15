package com.github.server.services;

import com.github.server.entity.Tournament;
import com.github.server.entity.User;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;
import com.github.server.utils.HibernateUtils;

import java.util.Collection;

public class TournamentService implements ITournamentService {

    final private IRepository<Tournament> repository = new Repository<>();

    @Override
    public Collection<Tournament> findAll() {
        return this.repository.findAll(HibernateUtils.getSession(), Tournament.class);
    }

    @Override
    public Tournament findById(Long id) {
        return this.repository.findBy(HibernateUtils.getSession(), Tournament.class, "id", id);
    }

    @Override
    public Tournament findByName(String name) {
        return this.repository.findBy(HibernateUtils.getSession(), Tournament.class, "name", name);
    }
//
//    @Override
//    public Collection<Tournament> findByStatus(String status) {
//        return this.repository.findAllBy(HibernateUtils.getSession(), Tournament.class, "status", status);
//    }

    @Override
    public void insert(Tournament tournament) {
        this.repository.save(HibernateUtils.getSession(), Tournament.class, tournament);
    }

    @Override
    public void update(Tournament tournament) {
        this.repository.update(HibernateUtils.getSession(), Tournament.class, tournament);
    }
}
