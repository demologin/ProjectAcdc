package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class QuestController {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @GetMapping("/quest")
    public ModelAndView showQuest(@RequestParam(Constants.NAME) String questName,
                                  @SessionAttribute(name = Constants.CURRENT_PART, required = false) String currentPart,
                                  @CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id,
                                  HttpServletRequest request) {
        if (currentPart == null) {
            return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
        }

        if (userService.checkIfExists(id)) {
            User user = userService.get(id).get();
            setStatistics(user, currentPart);

            SessionAttributeSetter.addSessionAttribute(request, Constants.NAME, questName);

            if (questService.checkIfExists(questName)) {
                ModelAndView modelAndView = new ModelAndView(chooseTemplate(currentPart));
                Optional<Quest> questOptional = questService.get(questName);
                Quest quest = questOptional.get();
                modelAndView.addObject(Constants.QUEST, quest);
                modelAndView.addObject(Constants.QUESTION, quest.getQuestions().get(currentPart));
                modelAndView.addObject(Constants.AUTHOR, user.getLogin().equals(quest.getAuthor()));

                System.out.println(modelAndView.getModel().get(Constants.AUTHOR));
                return modelAndView;
            }
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }

    @PostMapping("/quest")
    public ModelAndView changeCurrentPart(@RequestParam(Constants.CURRENT_PART) String currentPart,
                                          @SessionAttribute(Constants.NAME) String questName,
                                          HttpServletRequest request) {
        SessionAttributeSetter.addSessionAttribute(request, Constants.CURRENT_PART, currentPart);
        return new ModelAndView(Constants.REDIRECT_QUEST_NAME + questName);
    }

    private String chooseTemplate(String currentPart) {
        if (currentPart.contains(Constants.WIN)) {
            return Constants.WIN_TEMPLATE;
        } else if (currentPart.contains(Constants.GAME_OVER)) {
            return Constants.GAME_OVER_TEMPLATE;
        } else {
            return Constants.QUEST_TEMPLATE;
        }
    }

    private void setStatistics(User user, String currentPart) {
        if (currentPart.contains(Constants.WIN)) {
            int wins = user.getWins();
            user.setWins(wins + 1);
        } else if (currentPart.contains(Constants.GAME_OVER)) {
            int losses = user.getLosses();
            user.setLosses(losses + 1);
        }
    }
}
