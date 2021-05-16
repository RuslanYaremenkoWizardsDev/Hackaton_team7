package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;

public interface IUserController {

    String authorize(UserAuthDto userAuthDto);

    void register(UserRegDto userRegDto);

    void update(UserRegDto userRegDto);

    String getInvites(String userLogin);

    void acceptInvite(String userLogin, String tournamentName);

    void declineInvite(String userLogin, String tournamentName);

    void createRequest(String userLogin, String tournamentName);

}
