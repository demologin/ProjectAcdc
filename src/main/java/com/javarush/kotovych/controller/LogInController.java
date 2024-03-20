package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class LogInController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView logIn(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setPath("/");
        usernameCookie.setMaxAge(Constants.DEFAULT_COOKIE_LIVING_TIME);

        Cookie passwordCookie = new Cookie("password", password);
        passwordCookie.setPath("/");
        usernameCookie.setMaxAge(Constants.DEFAULT_COOKIE_LIVING_TIME);

        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        return new ModelAndView("redirect:/");
    }
}
