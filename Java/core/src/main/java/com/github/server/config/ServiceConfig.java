package com.github.server.config;

import com.github.server.services.*;

public class ServiceConfig {

    private static final IUserService userService = new UserService(RepositoryConfig.getUserRepository());

    private static final IPlayerService playerService = new PlayerService(RepositoryConfig.getPlayerRepository());

    private static final ITournamentService tournamentService = new TournamentService(RepositoryConfig.getTournamentRepository());

    private static final IPlayerRequestService playerRequestService = new PlayerRequestService(RepositoryConfig.getPlayerRequestRepository());

    private static final IPlayerInviteService playerInviteService = new PlayerInviteService(RepositoryConfig.getPlayerInviteRepository());

    public static IUserService getUserService() {
        return userService;
    }

    public static IPlayerService getPlayerService() {
        return playerService;
    }

    public static ITournamentService getTournamentService() {
        return tournamentService;
    }

    public static IPlayerRequestService getPlayerRequestService() {
        return playerRequestService;
    }

    public static IPlayerInviteService getPlayerInviteService() {
        return playerInviteService;
    }
}
