package com.github.server.services;

import com.github.server.entity.PlayerInvite;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerInviteService implements IPlayerInviteService {

    private final IRepository<PlayerInvite> repository;

    public PlayerInviteService(IRepository<PlayerInvite> repository) {
        this.repository = repository;
    }

    @Override
    public PlayerInvite findInvite(String userLogin, String tournamentName) {
        Collection<PlayerInvite> playerInvites = findByTournament(tournamentName);
        return playerInvites.stream().filter(e -> e.getUser().equals(userLogin)).findFirst().orElseThrow();
    }

    @Override
    public Collection<PlayerInvite> findByTournament(String tournamentName) {
        return this.repository.findAllBy("nameTournament", tournamentName, HibernateUtils.getSession());
    }

    @Override
    public Collection<PlayerInvite> findByPlayer(String user) {
        return this.repository.findAllBy("user", user, HibernateUtils.getSession());
    }

    @Override
    public void createInvite(PlayerInvite invite) {
        this.repository.save(invite, HibernateUtils.getSession());
    }

    @Override
    public void deleteInvite(PlayerInvite invite) {
        this.repository.delete(invite, HibernateUtils.getSession());
    }
}
