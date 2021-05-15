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
        return this.userRepository.findAll(HibernateUtils.getSession(), User.class);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findBy(HibernateUtils.getSession(), User.class, "id", id);
    }

    @Override
    public User findByLogin(String login) {
        return this.userRepository.findBy(HibernateUtils.getSession(), User.class, "login", login);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findBy(HibernateUtils.getSession(), User.class, "email", email);
    }

    @Override
    public void insert(User user) {
        this.userRepository.save(HibernateUtils.getSession(), User.class, user);
    }

    @Override
    public void update(User user) {
        this.userRepository.update(HibernateUtils.getSession(), User.class, user);
    }

}
