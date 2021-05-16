package com.github.server.services;

import com.github.server.entity.PlayerInvite;

import java.util.Collection;

public interface IPlayerRequestService {

    PlayerInvite findByTournament(String tournamentName);

    Collection<PlayerInvite> findByPlayer(String user);


}
