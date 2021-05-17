package com.github.server.config;

import com.github.server.handlers.HttpHandler;

public class HandlerConfig {

    private static final HttpHandler handler = new HttpHandler(ControllerConfig.getUserController(), ControllerConfig.getAdminController());

    public static HttpHandler getHandler() {
        return handler;
    }
}
