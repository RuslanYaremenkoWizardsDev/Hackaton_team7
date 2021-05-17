package com.github.server.utils;

import com.github.server.entity.Tournament;
import com.github.server.entity.User;
import com.github.server.payload.Role;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class JsonHelperTest {

    private final Tournament tournament = new Tournament(
            "testTournament",
            "1",
            "hardmode",
            "Kyiv",
            new Date(212121L).toString(),
            new Date(912121L).toString(),
            "hard",
            32,
            "norm",
            "1,2"
    );

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

    private final Tournament tournamentNull = new Tournament(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

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
    public void toJsonNull() {
        String str = JsonHelper.toJson(null).orElse("s");
        assertEquals("null", str);
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