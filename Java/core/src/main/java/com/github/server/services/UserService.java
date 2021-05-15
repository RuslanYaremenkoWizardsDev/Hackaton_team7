package com.github.server.services;

import com.github.server.entity.User;
import com.github.server.repositories.IUserRepository;
import com.github.server.repositories.UserRepository;

import java.util.Collection;

public class UserService implements IUserService {

    final private IUserRepository userRepository = new UserRepository();

    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findBy("id", id);
    }

    @Override
    public User findByLogin(String login) {
        return this.userRepository.findBy("login", login);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findBy("email", email);
    }

    @Override
    public User insert(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void update(User user) {
        this.userRepository.update(user);
    }

}
