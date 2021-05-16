package com.github.server.controllers;

import com.github.server.entity.Tournament;
import com.github.server.exceptions.JsonParseException;
import com.github.server.services.*;
import com.github.server.utils.JsonHelper;

public class AdminController implements IAdminController {

    private final IPlayerService playerService;

    private final ITournamentService tournamentService;

    private final IPlayerInviteService playerInviteService;

    private final IPlayerRequestService playerRequestService;

    private final IUserService userService;

    public AdminController(IPlayerService playerService, ITournamentService tournamentService, IPlayerInviteService playerInviteService, IPlayerRequestService playerRequestService, IUserService userService) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.playerInviteService = playerInviteService;
        this.playerRequestService = playerRequestService;
        this.userService = userService;
    }

    @Override
    public void invitePlayer(String login) {

    }

    @Override
    public void createTournament(Tournament tournament) {
        tournamentService.insert(tournament);
    }

    @Override
    public void updateTournament(Tournament tournament) {
        tournamentService.update(tournament);
    }

    @Override
    public String findAllTournaments() {
        return JsonHelper.toJson(tournamentService.findAll()).orElseThrow(JsonParseException::new);
    }

    @Override
    public String findTournamentsByMode(String mode) {
        return JsonHelper.toJson(tournamentService.findByMode(mode)).orElseThrow(JsonParseException::new);
    }

    @Override
    public String findTournamentsByStatus(String status) {
        return JsonHelper.toJson(tournamentService.findByStatus(status)).orElseThrow(JsonParseException::new);
    }

    @Override
    public String findAllPlayers() {
        return JsonHelper.toJson(playerService.findAll()).orElseThrow(JsonParseException::new);
    }

    @Override
    public String findAllUsers() {
        return JsonHelper.toJson(userService.findAll()).orElseThrow(JsonParseException::new);
    }

    @Override
    public String getRequests() {
        return null;
    }

    @Override
    public void createInvite(String userLogin, String tournamentName) {

    }

    @Override
    public void acceptRequest(String userLogin, String tournamentName) {

    }

    @Override
    public void declineRequest(String userLogin, String tournamentName) {

    }

}
