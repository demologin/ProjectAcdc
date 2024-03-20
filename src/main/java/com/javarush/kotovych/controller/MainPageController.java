package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.QuestRepository;
import com.javarush.kotovych.repository.UserRepository;
import com.javarush.kotovych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class MainPageController {

    @Autowired
    private QuestRepository questService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView mainPage(@CookieValue(value = Constants.ID, defaultValue = "0") String id) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject(Constants.QUESTS, questService.getAll());
        Optional<User> user = userService.get(Long.parseLong(id));
        if (user.isPresent()) {
            modelAndView.addObject("loggedIn", true);
            modelAndView.addObject(Constants.USERNAME, user.get().getLogin());
        } else {
            modelAndView.addObject("loggedIn", false);
            modelAndView.addObject(Constants.USERNAME, Constants.LOGGED_IN);
        }

        return modelAndView;
    }
}
