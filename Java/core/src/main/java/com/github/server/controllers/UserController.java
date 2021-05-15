package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.User;
import com.github.server.exceptions.UserAlreadyExistException;
import com.github.server.services.UserService;

import com.github.server.utils.PattenMatcher.*;

import static com.github.server.utils.PattenMatcher.isValidEmail;

public class UserController implements IUserController{

    private final UserService userService;

    private User user;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        if(isValidEmail(userAuthDto.getLogin())){
            User user = this.userService.findByEmail(userAuthDto.getLogin());
        } else {
            User user = this.userService.findByLogin(userAuthDto.getLogin());
        }
        return null;
        }

    @Override
    public void register(UserRegDto userRegDto) {
        if(this.userService.findByEmail(userRegDto.getEmail()) != null){
            throw new UserAlreadyExistException();
        }
        userService.insert(user);
    }

}
