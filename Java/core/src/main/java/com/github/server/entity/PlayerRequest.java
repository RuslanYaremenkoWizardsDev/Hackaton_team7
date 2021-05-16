package com.github.server.entity;

import java.util.Arrays;
import java.util.Objects;

public class PlayerRequest {

    private String nameTournament;

    private String[] players;

    private String status;

    public PlayerRequest(String nameTournament, String[] players, String status) {
        this.nameTournament = nameTournament;
        this.players = players;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlayerRequest{" +
                "nameTournament='" + nameTournament + '\'' +
                ", players=" + Arrays.toString(players) +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRequest that = (PlayerRequest) o;
        return Objects.equals(nameTournament, that.nameTournament) && Arrays.equals(players, that.players) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nameTournament, status);
        result = 31 * result + Arrays.hashCode(players);
        return result;
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
