package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.User;
import com.github.server.exceptions.JsonParseException;
import com.github.server.exceptions.UserAlreadyExistException;
import com.github.server.payload.Token;
import com.github.server.services.IUserService;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.PattenMatcher;

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
        return JsonHelper.toJson(new Token(user)).orElseThrow(JsonParseException::new);
    }

    @Override
    public void register(UserRegDto userRegDto) {
        if (this.userService.findByEmail(userRegDto.getEmail()) != null) {
            throw new UserAlreadyExistException();
        }
        userService.insert(userRegDto.toUser());
    }

}
