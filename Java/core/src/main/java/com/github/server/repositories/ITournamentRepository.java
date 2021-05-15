package com.github.server.repositories;

import com.github.server.entity.Tournament;

import java.util.Collection;

public interface ITournamentRepository {

    Collection<Tournament> findAll();

    Tournament findBy(String field, Object value);

    void save(Tournament tournament);

    void update(Tournament tournament);

    void delete(Tournament tournament);

}
