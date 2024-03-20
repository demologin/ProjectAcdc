package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.Role;
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
    public ModelAndView getEditUserPage() {
        return new ModelAndView("edit-user");
    }

    @PostMapping("edit-user")
    public ModelAndView editUser(@RequestParam(Constants.USERNAME) String editUsername,
                                 @RequestParam(Constants.PASSWORD) String editPassword,
                                 @RequestParam(Constants.ROLE) String editRole,
                                 @CookieValue(Constants.ID) String id) {

        ModelAndView editPage = new ModelAndView("redirect:/edit-user");

        if(editUsername.isBlank() || editPassword.isBlank() || editRole.isBlank()) {
            return editPage;
        }

        Optional<User> optionalUser = userService.get(Long.parseLong(id));
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLogin(editUsername);
            user.setPassword(editPassword);
            user.setRole(Role.valueOf(editRole));
            userService.update(user);
        }
        return new ModelAndView("redirect:/");
    }
}
