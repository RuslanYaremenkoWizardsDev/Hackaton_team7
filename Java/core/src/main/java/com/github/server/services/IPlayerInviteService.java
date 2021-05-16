package com.github.server.services;

import com.github.server.entity.PlayerInvite;
import com.github.server.entity.Tournament;

import java.util.Collection;

public interface IPlayerInviteService {

    PlayerInvite findByTournament(String tournamentName);

    Collection<PlayerInvite> findByPlayer(String user);


}
