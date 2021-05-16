package com.github.server.config;

import com.github.server.entity.*;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;

public class RepositoryConfig {

    private static final IRepository<User> userRepository = new Repository<>(User.class);

    private static final IRepository<Player> playerRepository = new Repository<>(Player.class);

    private static final IRepository<Tournament> tournamentRepository = new Repository<>(Tournament.class);

    private static final IRepository<PlayerInvite> playerInviteRepository = new Repository<>(PlayerInvite.class);

    public static final IRepository<PlayerRequest> playerRequestRepository = new Repository<>(PlayerRequest.class);

    public static IRepository<User> getUserRepository() {
        return userRepository;
    }

    public static IRepository<Player> getPlayerRepository() {
        return playerRepository;
    }

    public static IRepository<Tournament> getTournamentRepository() {
        return tournamentRepository;
    }

    public static IRepository<PlayerInvite> getPlayerInviteRepository() {
        return playerInviteRepository;
    }

    public static IRepository<PlayerRequest> getPlayerRequestRepository() {
        return playerRequestRepository;
    }

}
