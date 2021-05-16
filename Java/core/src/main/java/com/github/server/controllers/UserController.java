package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.PlayerInvite;
import com.github.server.entity.PlayerRequest;
import com.github.server.entity.User;
import com.github.server.exceptions.ForbiddenException;
import com.github.server.exceptions.InternalServerError;
import com.github.server.payload.Envelope;
import com.github.server.payload.PrivateToken;
import com.github.server.services.*;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.PattenMatcher;
import com.github.server.utils.TokenProvider;

import java.util.ArrayList;
import java.util.Collection;

public class UserController implements IUserController {

    private final IUserService userService;

    private final IPlayerService playerService;

    private final ITournamentService tournamentService;

    private final IPlayerInviteService playerInviteService;

    private final IPlayerRequestService playerRequestService;

    public UserController(IUserService userService, IPlayerService playerService, ITournamentService tournamentService, IPlayerInviteService playerInviteService, IPlayerRequestService playerRequestService) {
        this.userService = userService;
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.playerInviteService = playerInviteService;
        this.playerRequestService = playerRequestService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user;
        if (PattenMatcher.isValidEmail(userAuthDto.getLogin())) {
            user = this.userService.findByEmail(userAuthDto.getLogin());
        } else {
            user = this.userService.findByLogin(userAuthDto.getLogin());
        }
        if(user == null) {
            throw new ForbiddenException();
        }
        PrivateToken token = new PrivateToken(user);
        String encodedToken = TokenProvider.encode(token);
        Envelope env = new Envelope(user.getRole(), encodedToken);
        return JsonHelper.toJson(env).orElseThrow(InternalServerError::new);
    }

    @Override
    public void register(UserRegDto userRegDto) {
        userService.insert(userRegDto.toUser());
    }

    @Override
    public void update(UserRegDto userRegDto) {
        User userToUpdate = userService.findByEmail(userRegDto.getEmail());
        User updatedUser = new User(
                userToUpdate.getId(),
                userRegDto.getLogin(),
                userRegDto.getEmail(),
                userRegDto.getPassword(),
                userToUpdate.getRole()
        );
        userService.update(updatedUser);
    }

    @Override
    public String getInvites(String email) {
        String userLogin = userService.findByEmail(email).getLogin();
        Collection<PlayerInvite> invites = playerInviteService.findByPlayer(userLogin);
        Collection<String> tournaments = new ArrayList<>();
        for (PlayerInvite invite : invites) {
            tournaments.add(invite.getNameTournament());
        }
        return JsonHelper.toJson(tournaments).orElseThrow();
    }

    @Override
    public void acceptInvite(String email, String tournamentName) {
        String userLogin = userService.findByEmail(email).getLogin();
        PlayerInvite invite = playerInviteService.findInvite(userLogin, tournamentName);
        tournamentService.addPlayer(tournamentName, userLogin);
        playerInviteService.deleteInvite(invite);
    }

    @Override
    public void declineInvite(String email, String tournamentName) {
        String userLogin = userService.findByEmail(email).getLogin();
        PlayerInvite invite = playerInviteService.findInvite(userLogin, tournamentName);
        playerInviteService.deleteInvite(invite);
    }

    @Override
    public void createRequest(String email, String tournamentName) {
        String userLogin = userService.findByEmail(email).getLogin();
        playerRequestService.createRequest(
                new PlayerRequest(
                        tournamentName,
                        userLogin,
                        "WAITING"
                )
        );
    }

}
