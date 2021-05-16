package com.github.server.handlers;

import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.exceptions.BadRequest;
import com.github.server.exceptions.ExpiredTokenException;
import com.github.server.exceptions.ForbiddenException;
import com.github.server.exceptions.NotFound;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.TokenProvider;
import org.hibernate.exception.ConstraintViolationException;
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

    private final IAdminController adminController;

    public HttpHandler(IUserController userController, IAdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
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
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setStatus(204);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String url = req.getRequestURI();
        String tokenStr = req.getHeader("Token");
        if (!TokenProvider.checkToken(tokenStr)) {
            throw new ExpiredTokenException();
        }
        try (ServletOutputStream out = resp.getOutputStream()) {
            switch (url) {
                case "/main/tournaments":
                    String resultTours = this.adminController.findAllTournaments();
                    out.write(resultTours.getBytes());
                    resp.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "/main/create":
                    String resultUsers = this.adminController.findAllUsers();
                    out.write(resultUsers.getBytes());
                    resp.setStatus(HttpServletResponse.SC_OK);
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }

        } catch (ExpiredTokenException e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Throwable e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            try (ServletOutputStream out = resp.getOutputStream()) {
                switch (url) {
                    case "/auth":
                        UserAuthDto authDto = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                        if (authDto == null) {
                            throw new BadRequest();
                        }
                        String result = Optional.of(this.userController.authorize(authDto)).orElseThrow(BadRequest::new);
                        resp.setContentType("application/json");
                        resp.setStatus(HttpServletResponse.SC_OK);
                        out.write(result.getBytes());
                        break;
                    case "/reg":
                        UserRegDto regDto = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                        if (regDto == null) {
                            throw new BadRequest();
                        }
                        this.userController.register(regDto);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    default:
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        break;
                }
            } catch (BadRequest e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (ForbiddenException e) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } catch (ConstraintViolationException e) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            } catch (ExpiredTokenException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Throwable e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String url = req.getRequestURI();
        String tokenStr = req.getHeader("Token");
        if (!TokenProvider.checkToken(tokenStr)) {
            throw new ExpiredTokenException();
        }

    }
}
