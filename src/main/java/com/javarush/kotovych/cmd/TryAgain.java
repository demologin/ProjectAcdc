package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TryAgain {

    @GetMapping("/try-again")
    public ModelAndView tryAgain(@RequestParam("name") String questName,
                                 HttpServletResponse response) {
        CookieSetter.addCookie(response, Constants.IN_GAME, "false");
        return new ModelAndView("redirect:/quest?name=" + questName);
    }
}
