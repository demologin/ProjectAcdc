package com.javarush.kotovych.util;

import com.javarush.kotovych.constants.Constants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CookieSetter {

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(Constants.DEFAULT_COOKIE_LIVING_TIME);
        response.addCookie(cookie);
    }
}
