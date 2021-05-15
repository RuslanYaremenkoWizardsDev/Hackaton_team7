package com.github.server.services;

import com.github.server.entity.Tournament;

import java.util.Collection;

public interface ITournamentService {

    Collection<Tournament> findAll();

    Tournament findById(Long id);

    Tournament findByName(String name);

    Collection<Tournament> findByStatus(String status);

    Tournament insert(Tournament tournament);

    void update(Tournament tournament);

}
