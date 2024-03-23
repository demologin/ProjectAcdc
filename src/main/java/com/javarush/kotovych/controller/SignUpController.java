package com.javarush.kotovych.controller;


import com.javarush.kotovych.constants.Constants;
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

import java.util.Optional;

@Slf4j
@RestController
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView getSignupPage() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signUp(@RequestParam(Constants.USERNAME) String username, @RequestParam("password") String password,  HttpServletResponse response) {
        Optional<User> existingUser = userService.get(username);
        if(existingUser.isEmpty()){
            User user = new User(username, password);
            userService.create(user);

            long id = user.getId();
            CookieSetter.addCookie(response, Constants.ID, String.valueOf(id));


            log.info("User with username {} created", username);
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("signup");
        }
    }
}
