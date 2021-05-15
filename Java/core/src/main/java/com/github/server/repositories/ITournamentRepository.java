package com.github.server.repositories;

import com.github.server.entity.Tournament;

import java.util.Collection;

public interface ITournamentRepository {

    Collection<Tournament> findAll();

    Collection<Tournament> findAllBy(String field, Object value);

    Tournament findBy(String field, Object value);

    Tournament save(Tournament tournament);

    void update(Tournament tournament);

    void delete(Tournament tournament);

}
