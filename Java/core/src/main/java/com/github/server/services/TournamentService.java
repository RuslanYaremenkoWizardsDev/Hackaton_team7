package com.github.server.services;

import com.github.server.entity.Tournament;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Collection;

public class TournamentService implements ITournamentService {

    private final IRepository<Tournament> repository;

    public TournamentService(IRepository<Tournament> repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Tournament> findAll() {
        return this.repository.findAll(Tournament.class, HibernateUtils.getSession());
    }

    @Override
    public Tournament findById(Long id) {
        return this.repository.findBy(Tournament.class, "id", id, HibernateUtils.getSession());
    }

    @Override
    public Tournament findByName(String name) {
        return this.repository.findBy(Tournament.class, "name", name, HibernateUtils.getSession());
    }
//
//    @Override
//    public Collection<Tournament> findByStatus(String status) {
//        return this.repository.findAllBy(HibernateUtils.getSession(), Tournament.class, "status", status);
//    }

    @Override
    public void insert(Tournament tournament) {
        this.repository.save(Tournament.class, tournament, HibernateUtils.getSession());
    }

    @Override
    public void update(Tournament tournament) {
        this.repository.update(Tournament.class, tournament, HibernateUtils.getSession());
    }
}
