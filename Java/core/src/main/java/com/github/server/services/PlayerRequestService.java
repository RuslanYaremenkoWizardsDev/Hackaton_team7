package com.github.server.services;

import com.github.server.entity.PlayerRequest;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Collection;

public class PlayerRequestService implements IPlayerRequestService {

    private final IRepository<PlayerRequest> repository;

    public PlayerRequestService(IRepository<PlayerRequest> repository) {
        this.repository = repository;
    }

    @Override
    public PlayerRequest findRequest(String userLogin, String tournamentName) {
        return findByTournament(tournamentName)
                .stream()
                .filter(playerRequest -> playerRequest
                        .getUser()
                        .equals(userLogin))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Collection<PlayerRequest> findByTournament(String tournamentName) {
        return this.repository.findAllBy("tournamentName", tournamentName, HibernateUtils.getSession());
    }

    @Override
    public Collection<PlayerRequest> findByPlayer(String user) {
        return this.repository.findAllBy("user", user, HibernateUtils.getSession());
    }

    @Override
    public Collection<PlayerRequest> findAll() {
        return this.repository.findAll(HibernateUtils.getSession());
    }

    @Override
    public void createRequest(PlayerRequest invite) {
        this.repository.save(invite,HibernateUtils.getSession());
    }

    @Override
    public void deleteRequest(PlayerRequest invite) {
        this.repository.delete(invite,HibernateUtils.getSession());
    }
}
