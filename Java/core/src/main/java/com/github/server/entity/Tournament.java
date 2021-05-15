package com.github.server.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Tournament implements Serializable {

    private Integer id;

    private String name;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastDayReg() {
        return lastDayReg;
    }

    public void setLastDayReg(Date lastDayReg) {
        this.lastDayReg = lastDayReg;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getListPlayers() {
        return listPlayers;
    }

    public void setListPlayers(String listPlayers) {
        this.listPlayers = listPlayers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tournament() {
    }

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
        this.name = nameTournament;
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mode, that.mode) && Objects.equals(place, that.place) && Objects.equals(startDate, that.startDate) && Objects.equals(lastDayReg, that.lastDayReg) && Objects.equals(difficult, that.difficult) && Objects.equals(maxPlayers, that.maxPlayers) && Objects.equals(scenario, that.scenario) && Objects.equals(listPlayers, that.listPlayers) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, mode, place, startDate, lastDayReg, difficult, maxPlayers, scenario, listPlayers, status);
    }

    @Override
    public String
    toString() {
        return "Tournament{" +
                "id=" + id +
                ", nameTournament='" + name + '\'' +
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
