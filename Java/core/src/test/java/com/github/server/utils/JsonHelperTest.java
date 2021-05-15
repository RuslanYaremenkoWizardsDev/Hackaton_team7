package com.github.server.utils;

import com.github.server.entity.Tournament;
import org.junit.Test;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class JsonHelperTest {

    private Tournament tournament = new Tournament(0,
            "testTournament",
            "",
            "hardmode",
            "Kyiv",
            new Date(212121L),
            new Date(912121L),
            "hard",
            32,
            "norm",
            "1,2",
            "ended");

    private String tournamentStr = "{" +
            "\"id\":0," +
            "\"name\":\"testTournament\"," +
            "\"description\":\"\"," +
            "\"mode\":\"hardmode\"," +
            "\"place\":\"Kyiv\"," +
            "\"startDate\":212121," +
            "\"lastDayReg\":912121," +
            "\"difficult\":\"hard\"," +
            "\"maxPlayers\":32," +
            "\"scenario\":\"norm\"," +
            "\"listPlayers\":\"1,2\"," +
            "\"status\":\"ended\"" +
            "}";

    @Test
    public void toJson() {
        String str = String.valueOf(JsonHelper.toJson(this.tournament).orElseThrow());
        assertEquals(str,this.tournamentStr);
    }

    @Test
    public void fromJson() {
        Optional<Tournament> tournamentTest = JsonHelper.fromJson(this.tournamentStr,Tournament.class);
        Tournament test = tournamentTest.orElseThrow();
        assertEquals(test,this.tournament);
    }
}