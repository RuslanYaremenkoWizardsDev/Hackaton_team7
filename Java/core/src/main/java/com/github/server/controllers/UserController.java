package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.User;
import com.github.server.exceptions.ForbiddenException;
import com.github.server.exceptions.InternalServerError;
import com.github.server.payload.Envelope;
import com.github.server.payload.PrivateToken;
import com.github.server.services.IUserService;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.PattenMatcher;
import com.github.server.utils.TokenProvider;

public class UserController implements IUserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
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
    public String getInvites(String userLogin) {
        return null;
    }

    @Override
    public void acceptInvite(String userLogin, String tournamentName) {

    }

    @Override
    public void declineInvite(String userLogin, String tournamentName) {

    }

    @Override
    public void createRequest(String userLogin, String tournamentName) {

    }

}
