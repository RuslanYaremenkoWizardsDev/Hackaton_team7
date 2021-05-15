package com.github.server.payload;

import com.github.server.entity.User;

import java.util.Date;
import java.util.Objects;

import static com.github.server.utils.DateUtils.addMinutes;
import static com.github.server.utils.DateUtils.getCurrentDate;

public class Token {

    private final Date date = new Date();

    private final String login;

    private final Role role;

    private final Date expireIn;

    private final Date createdAt;

    public Token(String login, Role role, Date expireIn, Date createdAt) {
        this.login = login;
        this.role = role;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public Token(User user, Date expireIn, Date createdAt) {
        this.login = user.getLogin();
        this.role = user.getRole();
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public Token(User user) {
        this.login = user.getLogin();
        this.role = user.getRole();
        this.createdAt = getCurrentDate();
        this.expireIn = addMinutes(date, 30);
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public Date getExpireIn() {
        return expireIn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(date, token.date) && Objects.equals(getLogin(), token.getLogin()) && Objects.equals(getRole(), token.getRole()) && Objects.equals(getExpireIn(), token.getExpireIn()) && Objects.equals(getCreatedAt(), token.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, getLogin(), getRole(), getExpireIn(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Token{" +
                "login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", expireIn=" + expireIn +
                ", createdAt=" + createdAt +
                '}';
    }
}
