package com.github.server.payload;

import com.github.server.entity.User;

import java.util.Date;
import java.util.Objects;

import static com.github.server.utils.DateUtils.addMinutes;
import static com.github.server.utils.DateUtils.getCurrentDate;

public class PrivateToken {

    private final String email;

    private final Role role;

    private final Date expireIn;

    private final Date createdAt;

    public PrivateToken(String email, Role role, Date expireIn, Date createdAt) {
        this.email = email;
        this.role = role;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user, Date expireIn, Date createdAt) {
        this.email = user.getEmail();
        this.role = user.getRole();
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user) {
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = getCurrentDate();
        this.expireIn = addMinutes(getCurrentDate(), 30);
    }

    public String getEmail() {
        return email;
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
        PrivateToken that = (PrivateToken) o;
        return Objects.equals(email, that.email) && role == that.role && Objects.equals(expireIn, that.expireIn) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role, expireIn, createdAt);
    }

    @Override
    public String toString() {
        return "Token{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", expireIn=" + expireIn +
                ", createdAt=" + createdAt +
                '}';
    }
}
