package com.github.server.config;

import com.github.server.handlers.HttpHandler;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class ServerConfig {

    public static void start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }
        tomcat.setPort(Integer.parseInt(webPort));
        Context ctx = tomcat.addWebapp("", new File(".").getAbsolutePath());
        tomcat.addServlet("", HttpHandler.class.getName(), AppConfig.getHandler());
        ctx.addServletMappingDecoded("/*", HttpHandler.class.getName());
        tomcat.start();
        tomcat.getServer().await();

    }

}
