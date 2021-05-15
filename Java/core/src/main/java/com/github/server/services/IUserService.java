package com.github.server.services;

import com.github.server.entity.User;

import java.util.Collection;

public interface IUserService {

    Collection<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    User findByEmail(String email);

    User insert(User user);

    void update(User user);

}
