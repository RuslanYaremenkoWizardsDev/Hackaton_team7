package com.github.server.controller;

import com.github.server.config.ControllerConfig;
import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import org.junit.Test;

public class ControllerTests {

    IUserController userController = ControllerConfig.getUserController();

    IAdminController adminController = ControllerConfig.getAdminController();

    @Test
    public void createRequest(){
        userController.createRequest("vasya@vasya.com", "football");
    }

}
