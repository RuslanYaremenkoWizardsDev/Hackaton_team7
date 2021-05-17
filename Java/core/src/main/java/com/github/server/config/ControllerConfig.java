package com.github.server.config;

import com.github.server.controllers.AdminController;
import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import com.github.server.controllers.UserController;

public class ControllerConfig {

    private static final IUserController userController = new UserController(
            ServiceConfig.getUserService(),
            ServiceConfig.getPlayerService(),
            ServiceConfig.getTournamentService(),
            ServiceConfig.getPlayerInviteService(),
            ServiceConfig.getPlayerRequestService()
    );

    private static final IAdminController adminController = new AdminController(
            ServiceConfig.getPlayerService(),
            ServiceConfig.getTournamentService(),
            ServiceConfig.getPlayerInviteService(),
            ServiceConfig.getPlayerRequestService(),
            ServiceConfig.getUserService()
    );

    public static IUserController getUserController() {
        return userController;
    }

    public static IAdminController getAdminController() {
        return adminController;
    }
}
