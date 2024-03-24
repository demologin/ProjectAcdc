package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class QuestController {
    @Autowired
    QuestService questService;

    @Autowired
    UserService userService;

    @GetMapping("/quest")
    public ModelAndView showQuest(@RequestParam(Constants.NAME) String questName,
                                  @CookieValue(value = Constants.CURRENT_PART, defaultValue = Constants.START) String currentPart,
                                  @CookieValue(value = Constants.ID) long id,
                                  HttpServletResponse response) {

        Optional<User> userOptional = userService.get(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            setStatistics(user, currentPart);

            CookieSetter.addCookie(response, Constants.NAME, questName);

            ModelAndView modelAndView = new ModelAndView(chooseTemplate(currentPart));
            Optional<Quest> questOptional = questService.get(questName);
            if (questOptional.isPresent()) {
                Quest quest = questOptional.get();
                modelAndView.addObject(Constants.QUEST, quest);
                modelAndView.addObject(Constants.QUESTION, quest.getQuestions().get(currentPart));
                return modelAndView;
            }
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/quest")
    public ModelAndView changeCurrentPart(@RequestParam(Constants.CURRENT_PART) String currentPart,
                                          @CookieValue(Constants.NAME) String questName,
                                          HttpServletResponse response) {
        CookieSetter.addCookie(response, Constants.CURRENT_PART, currentPart);
        return new ModelAndView("redirect:/quest?name=" + questName);
    }

    private String chooseTemplate(String currentPart){
        if(currentPart.contains(Constants.WIN)){
            return "winTemplate";
        } else if (currentPart.contains(Constants.GAME_OVER)) {
            return "game-over-template";
        } else{
           return "questTemplate";
        }
    }

    private void setStatistics(User user, String currentPart){
        if(currentPart.contains(Constants.WIN)) {
            int wins = user.getWins();
            user.setWins(wins + 1);
        } else if (currentPart.contains(Constants.GAME_OVER)) {
            int losses = user.getLosses();
            user.setLosses(losses + 1);
        }
    }
}
