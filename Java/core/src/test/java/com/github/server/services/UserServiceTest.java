package com.github.server.services;

import com.github.server.config.ServiceConfig;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

    private static IUserService userService = ServiceConfig.getUserService();

    @Before
    public void setUp() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByLogin() {
        System.out.println(userService.findByLogin("admin"));
        System.out.println("=============================================");
    }

    @Test
    public void findByEmail() {
        System.out.println(userService.findByEmail("admin@admin.com"));
        System.out.println("=============================================");
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {

    }
}