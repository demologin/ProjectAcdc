package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserListController {
    @Autowired
    private UserService userService;

    @GetMapping("/user-list")
    public ModelAndView getUserListPage(@CookieValue(value = Constants.ID, defaultValue = "0") String id) {
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", userService.getAll());
        if (userService.checkIfExists(Long.parseLong(id))) {
            User user = userService.get(Long.parseLong(id)).get();
            modelAndView.addObject(Constants.LOGGED_IN, true);
        } else {
            modelAndView.addObject(Constants.LOGGED_IN, false);
        }
        return modelAndView;
    }
}
