package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class EditUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/edit-user")
    public ModelAndView getEditUserPage(@CookieValue(value = Constants.ID, defaultValue = "0") long id) {
        ModelAndView modelAndView = new ModelAndView(Constants.EDIT_USER);
        if (id != 0) {
            Optional<User> user = userService.get(id);
            if (user.isPresent()) {
                modelAndView.addObject(Constants.USER, user.get());
                return modelAndView;
            }
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }

    @PostMapping("/edit-user")
    public ModelAndView editUser(@RequestParam(Constants.USERNAME) String editUsername,
                                 @RequestParam(Constants.PASSWORD) String editPassword,
                                 @CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {

        ModelAndView editPage = new ModelAndView("redirect:/" + Constants.EDIT_USER);


        if (editUsername.isBlank() || editPassword.isBlank()) {
            return editPage;
        }

        if (userService.checkIfExists(id)) {
            Optional<User> optionalUser = userService.get(id);
            User user = optionalUser.get();
            user.setLogin(editUsername);
            user.setPassword(editPassword);
            userService.update(user);
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
