package com.github.server.entity;

import java.util.Date;
import java.util.Objects;

public class Tournament {

    private Integer id;

    private String nameTournament;

    private String description;

    private String mode;

    private String place;

    private Date startDate;

    private Date lastDayReg;

    private String difficult;

    private Integer maxPlayers;

    private String scenario;

    private String listPlayers;

    private String status;

    public Tournament(Integer id,
                      String nameTournament,
                      String description,
                      String mode,
                      String place,
                      Date startDate,
                      Date lastDayReg,
                      String difficult,
                      Integer maxPlayers,
                      String scenario,
                      String listPlayers,
                      String status) {
        this.id = id;
        this.nameTournament = nameTournament;
        this.description = description;
        this.mode = mode;
        this.place = place;
        this.startDate = startDate;
        this.lastDayReg = lastDayReg;
        this.difficult = difficult;
        this.maxPlayers = maxPlayers;
        this.scenario = scenario;
        this.listPlayers = listPlayers;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) && Objects.equals(nameTournament, that.nameTournament) && Objects.equals(description, that.description) && Objects.equals(mode, that.mode) && Objects.equals(place, that.place) && Objects.equals(startDate, that.startDate) && Objects.equals(lastDayReg, that.lastDayReg) && Objects.equals(difficult, that.difficult) && Objects.equals(maxPlayers, that.maxPlayers) && Objects.equals(scenario, that.scenario) && Objects.equals(listPlayers, that.listPlayers) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTournament, description, mode, place, startDate, lastDayReg, difficult, maxPlayers, scenario, listPlayers, status);
    }

    @Override
    public String
    toString() {
        return "Tournament{" +
                "id=" + id +
                ", nameTournament='" + nameTournament + '\'' +
                ", description='" + description + '\'' +
                ", mode='" + mode + '\'' +
                ", place='" + place + '\'' +
                ", startDate=" + startDate +
                ", lastDayReg=" + lastDayReg +
                ", difficult='" + difficult + '\'' +
                ", maxPlayers=" + maxPlayers +
                ", scenario='" + scenario + '\'' +
                ", listPlayers='" + listPlayers + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
