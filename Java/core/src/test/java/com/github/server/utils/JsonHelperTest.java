package com.github.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.server.entity.Tournament;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;
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

    private Tournament tournamentNull = new Tournament(null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);

    private String tournamentStrNull = "{" +
            "\"id\":null," +
            "\"name\":null," +
            "\"description\":null," +
            "\"mode\":null," +
            "\"place\":null," +
            "\"startDate\":null," +
            "\"lastDayReg\":null," +
            "\"difficult\":null," +
            "\"maxPlayers\":null," +
            "\"scenario\":null," +
            "\"listPlayers\":null," +
            "\"status\":null" +
            "}";

    @Test
    public void toJson() {
        String str = String.valueOf(JsonHelper.toJson(this.tournament).orElseThrow());
        assertEquals(this.tournamentStr, str);
    }

    @Test
    public void toJsonAllNull() {
        String str = String.valueOf(JsonHelper
                .toJson(tournamentNull)
                .orElseThrow());
        assertEquals(tournamentStrNull, str);
    }


    @Test
    public void toJson2() {
        String str = String.valueOf(JsonHelper.toJson(null).orElse("s"));
        assertEquals("null", str);
    }

    @Test
    public void fromJson() {
        Optional<Tournament> tournamentTest = JsonHelper.fromJson(this.tournamentStr, Tournament.class);
        Tournament test = tournamentTest.orElseThrow();
        assertEquals(this.tournament, test);
    }

    @Test
    public void fromJson2() {
        Tournament actual = JsonHelper.fromJson(tournamentStrNull, Tournament.class).orElseThrow();
        assertEquals(tournamentNull, actual);
    }
}