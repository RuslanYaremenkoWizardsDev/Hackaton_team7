package com.github.server.services;

import com.github.server.entity.PlayerRequest;
import com.github.server.repositories.IRepository;

import java.util.Collection;

public class PlayerRequestService implements IPlayerRequestService {

    private final IRepository<PlayerRequest> repository;

    public PlayerRequestService(IRepository<PlayerRequest> repository) {
        this.repository = repository;
    }

    @Override
    public PlayerRequest findRequest(String userLogin, String tournamentName) {
        return null;
    }

    @Override
    public Collection<PlayerRequest> findByTournament(String tournamentName) {
        return null;
    }

    @Override
    public Collection<PlayerRequest> findByPlayer(String user) {
        return null;
    }

    @Override
    public Collection<PlayerRequest> findAll() {
        return null;
    }

    @Override
    public void createRequest(PlayerRequest invite) {

    }

    @Override
    public void deleteRequest(PlayerRequest invite) {

    }

//    @Override
//    public PlayerRequest findByTournament(String tournamentName) {
//        return this.repository.findBy("nameTournament", tournamentName, HibernateUtils.getSession());
//    }
//
//    @Override
//    public Collection<PlayerRequest> findByPlayer(String user) {
//        return this.repository.findAllBy("user", user, HibernateUtils.getSession());
//    }
}
