package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;

public class UserController implements IUserController{

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        return null;
    }

    @Override
    public void register(UserRegDto userRegDto) {

    }

}
