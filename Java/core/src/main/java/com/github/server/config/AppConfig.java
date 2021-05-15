package com.github.server.config;

import com.github.server.controllers.IUserController;
import com.github.server.controllers.UserController;
import com.github.server.entity.User;
import com.github.server.handlers.HttpHandler;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;
import com.github.server.services.IUserService;
import com.github.server.services.UserService;

public class AppConfig {

    private static final IRepository<User> userRepository = new Repository<>();

    private static final IUserService userService = new UserService(getUserRepository());

    private static final IUserController userController = new UserController(getUserService());

    private static final HttpHandler handler = new HttpHandler(getUserController());

    public static IRepository<User> getUserRepository() {
        return userRepository;
    }

    public static IUserService getUserService() {
        return userService;
    }

    public static IUserController getUserController() {
        return userController;
    }

    public static HttpHandler getHandler() {
        return handler;
    }
}
