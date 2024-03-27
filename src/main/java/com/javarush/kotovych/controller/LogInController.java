package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.CookieSetter;
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
        return new ModelAndView(Constants.LOGIN);
    }

    @PostMapping("/login")
    public ModelAndView logIn(@RequestParam(Constants.USERNAME) String username,
                              @RequestParam(Constants.PASSWORD) String password,
                              HttpServletResponse response) {
        ModelAndView loginPage = new ModelAndView(Constants.LOGIN);
        if (!userService.checkIfCorrect(username, password)) {
            log.debug(LoggerConstants.USER_NOT_FOUND_LOG, username);
            loginPage.addObject(Constants.ERROR, Constants.USER_NOT_FOUND_OR_INCORRECT_PASSWORD);
            log.info(LoggerConstants.USER_NOT_FOUND_LOG, username);
            return loginPage;
        }
        User user = userService.get(username).get();
        long id = user.getId();
        CookieSetter.addCookie(response, Constants.ID, String.valueOf(id));
        log.info(LoggerConstants.USER_LOGGED_IN_LOG, username);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
