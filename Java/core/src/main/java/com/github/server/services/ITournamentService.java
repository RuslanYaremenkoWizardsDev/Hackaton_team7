package com.github.server.services;

import com.github.server.entity.Tournament;

import java.util.Collection;

public interface ITournamentService {

    Collection<Tournament> findAll();

    Tournament findById(Long id);

    Tournament findByLogin(String login);

    Tournament findByEmail(String email);

    Tournament insert(Tournament tournament);

    void update(Tournament tournament);



}
