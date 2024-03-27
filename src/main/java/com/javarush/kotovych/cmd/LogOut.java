package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogOut {

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        CookieSetter.addCookie(response, Constants.ID, Constants.DEFAULT_ID);
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
