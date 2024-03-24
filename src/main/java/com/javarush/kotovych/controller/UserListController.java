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
    public ModelAndView getUserListPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        ModelAndView modelAndView = new ModelAndView(Constants.USER_LIST);
        modelAndView.addObject(Constants.USERS, userService.getAll());
        if (userService.checkIfExists(id)) {
            User user = userService.get(id).get();
            modelAndView.addObject(Constants.USERNAME, user.getLogin());
            return modelAndView;
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
