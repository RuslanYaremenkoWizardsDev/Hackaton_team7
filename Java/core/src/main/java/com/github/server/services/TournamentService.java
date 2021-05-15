package com.github.server.services;

import com.github.server.entity.Tournament;
import com.github.server.repositories.ITournamentRepository;
import com.github.server.repositories.IUserRepository;
import com.github.server.repositories.TournamentRepository;
import com.github.server.repositories.UserRepository;

import java.util.Collection;

public class TournamentService implements ITournamentService {

    final private ITournamentRepository tournamentRepository = new TournamentRepository();

    @Override
    public Collection<Tournament> findAll() {
        return this.tournamentRepository.findAll();
    }

    @Override
    public Tournament findById(Long id) {
        return this.tournamentRepository.findBy("id", id);
    }

    @Override
    public Tournament findByName(String name) {
        return this.tournamentRepository.findBy("name", name);
    }

    @Override
    public Collection<Tournament> findByStatus(String status) {
        return this.tournamentRepository.findAllBy("status", status);
    }

    @Override
    public Tournament insert(Tournament tournament) {
        return this.tournamentRepository.save(tournament);
    }

    @Override
    public void update(Tournament tournament) {
        this.tournamentRepository.update(tournament);
    }
}
