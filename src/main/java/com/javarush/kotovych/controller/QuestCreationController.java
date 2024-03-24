package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.QuestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class QuestCreationController {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @GetMapping("/create-quest")
    public ModelAndView getCreateQuestPage() {
        return new ModelAndView(Constants.CREATE_QUEST);
    }

    @PostMapping("/create-quest")
    public ModelAndView createQuest(@RequestParam(Constants.JSON) String json,
                                    @CookieValue(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        if (userService.checkIfExists(id)) {
            Quest quest;
            try {
                quest = QuestParser.parseFromJson(json);
            } catch (Exception e) {
                return new ModelAndView(Constants.CREATE_QUEST);
            }
            questService.createIfNotExists(quest);
            return new ModelAndView("redirect:/quest?name=" + quest.getName());
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
