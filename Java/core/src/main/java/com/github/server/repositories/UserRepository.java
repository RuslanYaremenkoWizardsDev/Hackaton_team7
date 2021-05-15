package com.github.server.repositories;

import com.github.server.entity.User;

import java.util.Collection;

public class UserRepository implements IUserRepository {

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User findBy(String field, Object value) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

}
