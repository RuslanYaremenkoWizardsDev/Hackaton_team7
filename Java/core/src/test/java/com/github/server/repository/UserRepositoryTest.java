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
    public static void setUp(){

        mockData.add(new User(null, "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "secondUserLogin", "secondUserEmail", "secondUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN")));
        mockData.add(new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")));

        for (User user: mockData) {
            repository.save(HibernateUtils.getSession(), User.class, user);
        }
    }

    @AfterClass
    public static void tearDown(){
        Collection<User> users = repository.findAll(HibernateUtils.getSession(), User.class);
        for (User user : users ) {
            repository.delete(HibernateUtils.getSession(), User.class, user);
        }
    }

    @Test
    public void findByLoginFirstUser(){
        User act = repository.findBy(HibernateUtils.getSession(), User.class, "login", "firstUserLogin");
        User exp = new User(act.getId(), "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailThirdUser(){
        User act = repository.findBy(HibernateUtils.getSession(), User.class, "email", "thirdUserEmail");
        User exp = new User(act.getId(), "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByLoginFirstAdmin(){
        User act = repository.findBy(HibernateUtils.getSession(), User.class, "login", "firstAdminLogin");
        User exp = new User(act.getId(), "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailSecondAdmin(){
        User act = repository.findBy(HibernateUtils.getSession(), User.class, "email", "secondAdminEmail");
        User exp = new User(act.getId(), "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", act.getRole());
        Assert.assertEquals(exp, act);
    }

    @Test
    public void update(){
        User userToUpdate = repository.findBy(HibernateUtils.getSession(), User.class, "login", "firstUserLogin");
        User exp = new User(
                userToUpdate.getId(),
                "newFirstUserLogin",
                "newFirstUserEmail",
                "newFirstUserPassword",
                Role.valueOf("ADMIN")
        );
        repository.update(HibernateUtils.getSession(), User.class, exp);
        User act = repository.findBy(HibernateUtils.getSession(), User.class, "id", exp.getId());
        Assert.assertEquals(exp, act);
        repository.update(HibernateUtils.getSession(), User.class, userToUpdate);
    }

    @Test
    public void delete(){
        Collection<User> exp = repository.findAll(HibernateUtils.getSession(), User.class);
        exp.removeIf(u -> u.getEmail().equals("secondUserEmail"));
        User userToDelete = repository.findBy(HibernateUtils.getSession(), User.class, "email", "secondUserEmail");
        repository.delete(HibernateUtils.getSession(), User.class, userToDelete);
        Collection<User> act = repository.findAll(HibernateUtils.getSession(), User.class);
        Assert.assertEquals(exp, act);
    }

}
