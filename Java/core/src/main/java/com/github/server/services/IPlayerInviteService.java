package com.github.server.services;

import com.github.server.entity.PlayerInvite;

import java.util.Collection;

public interface IPlayerInviteService {

    PlayerInvite findInvite(String userLogin, String tournamentName);

    Collection<PlayerInvite> findByTournament(String tournamentName);

    Collection<PlayerInvite> findByPlayer(String user);

    void createInvite(PlayerInvite invite);

    void deleteInvite(PlayerInvite invite);

}
