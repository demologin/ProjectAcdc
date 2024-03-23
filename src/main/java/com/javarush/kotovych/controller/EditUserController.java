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
    public ModelAndView getEditUserPage(@CookieValue(value = "id", defaultValue = "0") long id) {
        ModelAndView modelAndView = new ModelAndView("edit-user");
        if(id != 0) {
            Optional<User> user = userService.get(id);
            if(user.isPresent()) {
                modelAndView.addObject("user", user.get());
                return modelAndView;
            }
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping("edit-user")
    public ModelAndView editUser(@RequestParam(Constants.USERNAME) String editUsername,
                                 @RequestParam(Constants.PASSWORD) String editPassword,
                                 @CookieValue(Constants.ID) String id) {

        ModelAndView editPage = new ModelAndView("redirect:/edit-user");

        if(editUsername.isBlank() || editPassword.isBlank()) {
            return editPage;
        }

        Optional<User> optionalUser = userService.get(Long.parseLong(id));
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLogin(editUsername);
            user.setPassword(editPassword);
            userService.update(user);
        }
        return new ModelAndView("redirect:/");
    }
}
