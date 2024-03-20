package com.javarush.kotovych.controller;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class MainPageController {

    @Autowired
    private QuestRepository questService;

    @GetMapping("/")
    public ModelAndView mainPage(@CookieValue(value = "username", defaultValue = "notLoggedIn") String username) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("quests", questService.getAll());
        if (!username.equals("notLoggedIn")) {
            modelAndView.addObject("loggedIn", true);
        } else {
            modelAndView.addObject("loggedIn", false);
        }
        modelAndView.addObject("username", username);

        return modelAndView;
    }
}
