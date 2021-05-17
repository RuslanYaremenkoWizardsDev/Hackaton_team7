package com.github.server.proxy;

import com.github.server.controllers.IUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserControllerProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(IUserController.class);

    private final IUserController userController;


    public UserControllerProxy(IUserController userController) {
        this.userController = userController;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Before call to method: {}, with args: {}", method.getName(), args);
        Object result = method.invoke(this.userController, args);
        log.info("After call to method: {}", result);
        return result;
    }

}
