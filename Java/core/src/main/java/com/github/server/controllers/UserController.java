package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.User;
import com.github.server.exceptions.*;
import com.github.server.payload.Envelope;
import com.github.server.payload.PrivateToken;
import com.github.server.services.IUserService;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.PattenMatcher;
import com.github.server.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
