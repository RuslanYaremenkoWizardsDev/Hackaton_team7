package com.github.server.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenMatcher {

    public static boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
