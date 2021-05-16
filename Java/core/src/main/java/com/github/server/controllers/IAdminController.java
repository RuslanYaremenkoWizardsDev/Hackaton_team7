package com.github.server.controllers;

import com.github.server.entity.Tournament;

public interface IAdminController {

    void invitePlayer(String login);

    void createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    String findAllTournaments();

    String findTournamentsByMode(String mode);

    String findTournamentsByStatus(String status);

    String findAllPlayers();

    String findAllUsers();

    String getRequests();

    void createInvite(String userLogin, String tournamentName);

    void acceptRequest(String userLogin, String tournamentName);

    void declineRequest(String userLogin, String tournamentName);

}
