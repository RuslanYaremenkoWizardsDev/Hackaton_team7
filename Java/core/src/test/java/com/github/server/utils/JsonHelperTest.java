package com.github.server.utils;

import com.github.server.entity.Tournament;
import com.github.server.entity.User;
import com.github.server.payload.Role;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class JsonHelperTest {

    private final Tournament tournament = new Tournament(0,
            "testTournament",
            "1",
            "hardmode",
            "Kyiv",
            new Date(212121L).toString(),
            new Date(912121L).toString(),
            "hard",
            32,
            "norm",
            "1,2",
            "ended");

    private final String tournamentStr = "{" +
            "\"id\":0," +
            "\"name\":\"testTournament\"," +
            "\"description\":\"1\"," +
            "\"mode\":\"hardmode\"," +
            "\"place\":\"Kyiv\"," +
            "\"dateStart\":212121," +
            "\"dateRegEnd\":912121," +
            "\"level\":\"hard\"," +
            "\"maxPlayers\":32," +
            "\"scenario\":\"norm\"," +
            "\"players\":\"1,2\"," +
            "\"status\":\"ended\"" +
            "}";

    private final Tournament tournamentNull = new Tournament(null,
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
            "\"dateStart\":null," +
            "\"dateRegEnd\":null," +
            "\"level\":null," +
            "\"maxPlayers\":null," +
            "\"scenario\":null," +
            "\"players\":null," +
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
    public void toJsonNull() {
        String str = JsonHelper.toJson(null).orElse("s");
        assertEquals("null", str);
    }

    @Test
    public void fromJson() {
        Optional<Tournament> tournamentTest = JsonHelper.fromJson(this.tournamentStr, Tournament.class);
        Tournament test = tournamentTest.orElseThrow();
        assertEquals(this.tournament, test);
    }

    @Test
    public void fromJsonAllNull() {
        Tournament actual = JsonHelper.fromJson(tournamentStrNull, Tournament.class).orElseThrow();
        assertEquals(tournamentNull, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void fromJsonIncorrectJson() {
        JsonHelper.fromJson("{\"id\":\"321}", Tournament.class).orElseThrow();
    }

    @Test
    public void toJsonCollection(){
        Collection<User> users = new ArrayList<>();
        users.add(new User(null, "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER")));
        users.add(new User(null, "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER")));
        users.add(new User(null, "secondUserLogin", "secondUserEmail", "secondUserPassword", Role.valueOf("USER")));
        System.out.println(JsonHelper.toJson(users).orElseThrow());
    }

    @Test
    public void toJsonStringCollection(){
        Collection<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        System.out.println(JsonHelper.toJson(strings).orElseThrow());
    }

}