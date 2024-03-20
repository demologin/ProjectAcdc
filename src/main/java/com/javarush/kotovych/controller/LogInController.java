package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.exception.AppException;
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

import java.util.Optional;

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
    public ModelAndView logIn(@RequestParam(Constants.USERNAME) String username, @RequestParam("password") String password, HttpServletResponse response) {
        ModelAndView loginPage = new ModelAndView("redirect:/login");
        User user;
        try {
            user = userService.get(username).orElseThrow(() -> {
                log.debug("User {} not found", username);
                throw new AppException("User %s not found".formatted(username));
            });
        } catch (Exception e){
            return loginPage;
        }

        if (user.getPassword().equals(password)) {
            long id = user.getId();
            Cookie idCookie = new Cookie(Constants.ID, String.valueOf(id));
            idCookie.setPath("/");
            idCookie.setMaxAge(Constants.DEFAULT_COOKIE_LIVING_TIME);
            response.addCookie(idCookie);
            return new ModelAndView("redirect:/");
        } else {
            return loginPage;
        }
    }
}
