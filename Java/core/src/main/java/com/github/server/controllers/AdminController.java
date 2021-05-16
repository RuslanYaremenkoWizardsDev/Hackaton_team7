package com.github.server.controllers;

import com.github.server.dto.PlayerRequestDto;
import com.github.server.entity.PlayerInvite;
import com.github.server.entity.PlayerRequest;
import com.github.server.entity.Tournament;
import com.github.server.exceptions.JsonParseException;
import com.github.server.services.*;
import com.github.server.utils.JsonHelper;

import java.util.ArrayList;
import java.util.Collection;

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
        Collection<PlayerRequest> requests = playerRequestService.findAll();
        Collection<PlayerRequestDto> requestDtos = new ArrayList<>();
        for (PlayerRequest request: requests) {
            requestDtos.add(new PlayerRequestDto(request));
        }
        return JsonHelper.toJson(requestDtos).orElseThrow();
    }

    @Override
    public void createInvite(String userLogin, String tournamentName) {
        playerInviteService.createInvite(new PlayerInvite(tournamentName, userLogin));
    }

    @Override
    public void acceptRequest(String userLogin, String tournamentName) {
        PlayerRequest request = playerRequestService.findRequest(userLogin, tournamentName);
        tournamentService.addPlayer(tournamentName, userLogin);
        playerRequestService.deleteRequest(request);
    }

    @Override
    public void declineRequest(String userLogin, String tournamentName) {
        PlayerRequest request = playerRequestService.findRequest(userLogin, tournamentName);
        playerRequestService.deleteRequest(request);
    }

}
