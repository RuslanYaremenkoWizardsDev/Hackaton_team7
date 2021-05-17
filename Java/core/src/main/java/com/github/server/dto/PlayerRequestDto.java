package com.github.server.dto;

import com.github.server.entity.PlayerRequest;

import java.util.Objects;

public class PlayerRequestDto {

    private String nameTournament;

    private String user;

    public PlayerRequestDto() {
    }

    public PlayerRequestDto(String nameTournament, String user) {
        this.nameTournament = nameTournament;
        this.user = user;
    }

    public PlayerRequestDto(PlayerRequest playerRequest) {
        this.nameTournament = playerRequest.getNameTournament();
        this.user = playerRequest.getUser();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRequestDto that = (PlayerRequestDto) o;
        return Objects.equals(nameTournament, that.nameTournament) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTournament, user);
    }

    @Override
    public String toString() {
        return "PlayerRequestDto{" +
                "nameTournament='" + nameTournament + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

}
