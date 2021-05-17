package com.github.server;

import com.github.server.config.ServerConfig;
import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;

public class ServerApp {

    public static void main(String[] args) {

        try {
            ServerConfig.start();
        } catch (ServletException | LifecycleException e) {
            e.printStackTrace();
        }

    }

}
