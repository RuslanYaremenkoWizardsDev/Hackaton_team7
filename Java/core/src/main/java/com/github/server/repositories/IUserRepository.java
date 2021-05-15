package com.github.server.repositories;

import com.github.server.entity.User;

import java.util.Collection;

public interface IUserRepository {

    Collection<User> findAll();

    User findBy(String field, Object value);

    User save(User user);

    void update(User user);

    void delete(User user);

}
