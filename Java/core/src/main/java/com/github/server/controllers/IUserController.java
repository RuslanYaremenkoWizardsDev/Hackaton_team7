package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;

public interface IUserController {

    String authorize(UserAuthDto userAuthDto);

    void register(UserRegDto userRegDto);

    void update(UserRegDto userRegDto);

    String getInvites(String email);

    void acceptInvite(String email, String tournamentName);

    void declineInvite(String email, String tournamentName);

    void createRequest(String email, String tournamentName);

}
