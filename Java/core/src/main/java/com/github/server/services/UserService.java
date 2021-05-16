package com.github.server.services;

import com.github.server.entity.User;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Collection;

public class UserService implements IUserService {

    private final IRepository<User> userRepository;

    public UserService(IRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll(User.class, HibernateUtils.getSession());
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findBy(User.class, "id", id, HibernateUtils.getSession());
    }

    @Override
    public User findByLogin(String login) {
        return this.userRepository.findBy(User.class, "login", login, HibernateUtils.getSession());
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findBy(User.class, "email", email, HibernateUtils.getSession());
    }

    @Override
    public void insert(User user) {
        this.userRepository.save(User.class, user, HibernateUtils.getSession());
    }

    @Override
    public void update(User user) {
        this.userRepository.update(User.class, user, HibernateUtils.getSession());
    }

}
