package com.github.server.repository;

import com.github.server.entity.User;
import com.github.server.payload.Role;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;
import com.github.server.utils.HibernateUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepositoryTest {

    private static final IRepository<User> repository = new Repository<>();

    private static final List<User> mockData = new ArrayList<>();

    @BeforeClass
    public static void setUp() {

        mockData.add(new User(null, "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "secondUserLogin", "secondUserEmail", "secondUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN")));
        mockData.add(new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")));

        for (User user : mockData) {
            repository.save(User.class, user, HibernateUtils.getSession());
        }
    }

    @AfterClass
    public static void tearDown() {
        Collection<User> users = repository.findAll(User.class, HibernateUtils.getSession());
        for (User user : users) {
            repository.delete(User.class, user, HibernateUtils.getSession());
        }
    }

    @Test
    public void findByLoginFirstUser() {
        User act = repository.findBy(User.class, "login", "firstUserLogin", HibernateUtils.getSession());
        User exp = new User(act.getId(), "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailThirdUser() {
        User act = repository.findBy(User.class, "email", "thirdUserEmail", HibernateUtils.getSession());
        User exp = new User(act.getId(), "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByLoginFirstAdmin() {
        User act = repository.findBy(User.class, "login", "firstAdminLogin", HibernateUtils.getSession());
        User exp = new User(act.getId(), "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailSecondAdmin() {
        User act = repository.findBy(User.class, "email", "secondAdminEmail", HibernateUtils.getSession());
        User exp = new User(act.getId(), "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", act.getRole());
        Assert.assertEquals(exp, act);
    }

    @Test
    public void update() {
        User userToUpdate = repository.findBy(User.class, "login", "firstUserLogin", HibernateUtils.getSession());
        User exp = new User(
                userToUpdate.getId(),
                "newFirstUserLogin",
                "newFirstUserEmail",
                "newFirstUserPassword",
                Role.valueOf("ADMIN")
        );
        repository.update(User.class, exp, HibernateUtils.getSession());
        User act = repository.findBy(User.class, "id", exp.getId(), HibernateUtils.getSession());
        Assert.assertEquals(exp, act);
        repository.update(User.class, userToUpdate, HibernateUtils.getSession());
    }

    @Test
    public void delete() {
        Collection<User> exp = repository.findAll(User.class, HibernateUtils.getSession());
        exp.removeIf(u -> u.getEmail().equals("secondUserEmail"));
        User userToDelete = repository.findBy(User.class, "email", "secondUserEmail", HibernateUtils.getSession());
        repository.delete(User.class, userToDelete, HibernateUtils.getSession());
        Collection<User> act = repository.findAll(User.class, HibernateUtils.getSession());
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findAllAdmins() {
        Collection<User> exp = new ArrayList<>();
        Collection<User> act = repository.findAllBy(User.class, "role", Role.valueOf("ADMIN"), HibernateUtils.getSession());
        exp.add(new User(null, "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN")));
        exp.add(new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")));
    }


}
