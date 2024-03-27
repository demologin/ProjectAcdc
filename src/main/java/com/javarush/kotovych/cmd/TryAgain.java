package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.constants.LoggerConstants;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class TryAgain {


    @GetMapping("/try-again")
    public ModelAndView tryAgain(@RequestParam(Constants.NAME) String questName,
                                 HttpServletRequest request) {
        log.debug(LoggerConstants.REDIRECTING_TO_QUEST_LOG, questName);
        log.info(LoggerConstants.USER_IS_TRYING_AGAIN_QUEST_LOG, questName);
        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, Constants.START);
        return new ModelAndView(Constants.REDIRECT_QUEST_NAME + questName);
    }
}
