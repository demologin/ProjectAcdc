package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainPageController {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView mainPage(@CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) String id,
                                 HttpServletRequest request) {

        request.getSession().invalidate();
        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, Constants.START);

        ModelAndView modelAndView = new ModelAndView(Constants.HOME_PAGE);
        modelAndView.addObject(Constants.QUESTS, questService.getAll());
        if (userService.checkIfExists(Long.parseLong(id))) {
            User user = userService.get(Long.parseLong(id)).get();
            modelAndView.addObject(Constants.LOGGED_IN, true);
            modelAndView.addObject(Constants.USERNAME, user.getLogin());
        } else {
            modelAndView.addObject(Constants.LOGGED_IN, false);
            modelAndView.addObject(Constants.USERNAME, Constants.NOT_LOGGED_IN);
        }

        return modelAndView;
    }
}
