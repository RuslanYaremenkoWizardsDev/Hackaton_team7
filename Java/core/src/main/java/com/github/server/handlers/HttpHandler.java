package com.github.server.handlers;

import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.Tournament;
import com.github.server.entity.User;
import com.github.server.exceptions.BadRequest;
import com.github.server.exceptions.ExpiredTokenException;
import com.github.server.exceptions.ForbiddenException;
import com.github.server.exceptions.NotFound;
import com.github.server.payload.PrivateToken;
import com.github.server.payload.Role;
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
        resp.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String url = req.getRequestURI();
        String tokenStr = req.getHeader("Token");
        try (ServletOutputStream out = resp.getOutputStream()) {
            if (!TokenProvider.checkToken(tokenStr)) {
                throw new ExpiredTokenException();
            }
            switch (url) {
                case "/main/tournaments":
                    String resultTours = this.adminController.findAllTournaments();
                    out.write(resultTours.getBytes());
                    resp.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "/main/create":
                    PrivateToken privateToken = TokenProvider.decode(tokenStr);
                    Role userRole = privateToken.getRole();
                    if (userRole == Role.ADMIN) {
                        String resultUsers = this.adminController.findAllUsers();
                        out.write(resultUsers.getBytes());
                        resp.setStatus(HttpServletResponse.SC_OK);
                    }
                    break;
                case "/main/tournamentInvite":
                    PrivateToken privateToken1 = TokenProvider.decode(tokenStr);
                    Role userRoleInv = privateToken1.getRole();
                    if (userRoleInv == Role.USER) {
                        String resultInv = this.userController.getInvites(privateToken1.getEmail());
                        out.write(resultInv.getBytes());
                        resp.setStatus(HttpServletResponse.SC_OK);
                    }
                    break;
                case "/main/tournamentRequest":
                    PrivateToken privateToken2 = TokenProvider.decode(tokenStr);
                    Role adminRole = privateToken2.getRole();
                    if (adminRole == Role.ADMIN) {
                        String resultReq = this.adminController.getRequests();
                        out.write(resultReq.getBytes());
                        resp.setStatus(HttpServletResponse.SC_OK);
                    }
                default:
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }

        } catch (ExpiredTokenException e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (ForbiddenException e) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
                    case "/main/create":
                        Tournament tournament = JsonHelper.fromJson(body, Tournament.class).orElseThrow(BadRequest::new);
                        if (tournament == null) {
                            throw new BadRequest();
                        }
                        this.adminController.createTournament(tournament);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/main/tournamentInvite":
                        Tournament tourCreateInv = JsonHelper.fromJson(body, Tournament.class).orElseThrow(BadRequest::new);
                        User userCreateInv = JsonHelper.fromJson(body, User.class).orElseThrow(BadRequest::new);
                        if (tourCreateInv == null) {
                            throw new BadRequest();
                        }
                        this.adminController.createInvite(userCreateInv.getLogin(), tourCreateInv.getName());
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/main/tournamentRequest":
                        Tournament tourCreateReq = JsonHelper.fromJson(body, Tournament.class).orElseThrow(BadRequest::new);
                        User userCreateReq = JsonHelper.fromJson(body, User.class).orElseThrow(BadRequest::new);
                        if (tourCreateReq == null || userCreateReq == null) {
                            throw new BadRequest();
                        }
                        this.userController.createRequest(userCreateReq.getEmail(), tourCreateReq.getName());
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/main/tournamentInvite/accept":
                        Tournament tournamentAcceptInv = JsonHelper.fromJson(body, Tournament.class).orElseThrow(BadRequest::new);
                        User userAcceptInv = JsonHelper.fromJson(body, User.class).orElseThrow(BadRequest::new);
                        if (tournamentAcceptInv == null || userAcceptInv == null) {
                            throw new BadRequest();
                        }
                        this.userController.acceptInvite(userAcceptInv.getEmail(), tournamentAcceptInv.getName());
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/main/tournamentRequest/accept":
                        Tournament tournamentAcceptReq = JsonHelper.fromJson(body, Tournament.class).orElseThrow(BadRequest::new);
                        User userAcceptReq = JsonHelper.fromJson(body, User.class).orElseThrow(BadRequest::new);
                        if (tournamentAcceptReq == null || userAcceptReq == null) {
                            throw new BadRequest();
                        }
                        this.adminController.acceptRequest(userAcceptReq.getLogin(), tournamentAcceptReq.getName());
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
        String body = req.getReader().lines().collect(Collectors.joining());
        String url = req.getRequestURI();
        if (url.contains("/updateUser")) {
            String tokenStr = req.getHeader("Token");
            if (!TokenProvider.checkToken(tokenStr)) {
                throw new ExpiredTokenException();
            }
            PrivateToken token = TokenProvider.decode(tokenStr);
            String userEmail = token.getEmail();
            UserAuthDto loginPassword = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
            UserRegDto userUpdate = new UserRegDto(loginPassword.getLogin(), userEmail, loginPassword.getPassword());
            this.userController.update(userUpdate);
        }
    }
}

