package com.github.server.services;

import com.github.server.entity.PlayerRequest;

import java.util.Collection;

public interface IPlayerRequestService {

    PlayerRequest findRequest(String userLogin, String tournamentName);

    Collection<PlayerRequest> findByTournament(String tournamentName);

    Collection<PlayerRequest> findByPlayer(String user);

    void createInvite(PlayerRequest invite);

    void deleteInvite(PlayerRequest invite);

}
