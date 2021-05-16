package com.github.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "playerInvite", schema = "public")
public class PlayerInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nameTournament")
    private String nameTournament;

    @Column(name = "user")
    private String user;

    @Column(name = "status")
    private String status;

    public PlayerInvite() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInvite that = (PlayerInvite) o;
        return Objects.equals(nameTournament, that.nameTournament) && Objects.equals(user, that.user) && Objects.equals(status, that.status);
    }

    @Override
    public String toString() {
        return "PlayerInvite{" +
                "nameTournament='" + nameTournament + '\'' +
                ", user='" + user + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTournament, user, status);
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlayerInvite(String nameTournament, String user, String status) {
        this.nameTournament = nameTournament;
        this.user = user;
        this.status = status;
    }
}
