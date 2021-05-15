package com.github.server.handlers;

import com.github.server.controllers.IUserController;
import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.exceptions.BadRequest;
import com.github.server.exceptions.NotFound;
import com.github.server.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHandler extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(HttpHandler.class);

    private final IUserController userController;

    public HttpHandler(IUserController userController) {
        this.userController = userController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body");
        } catch (NotFound e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND 404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            System.out.println("Body:\n" + body);
            System.out.println(url);
            if(url.contains("/auth")) {
                UserAuthDto payload = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                String result = Optional.of(this.userController.authorize(payload)).orElseThrow(BadRequest::new);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                ServletOutputStream out = resp.getOutputStream();
                out.write(result.getBytes());
                out.flush();
                out.close();
                return;
            }
            if(url.contains("/reg")) {
                UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                this.userController.register(payload);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }


}
