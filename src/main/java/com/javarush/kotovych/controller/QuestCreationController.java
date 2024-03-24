package com.javarush.kotovych.controller;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.util.QuestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class QuestCreationController {

    @Autowired
    QuestService questService;

    @GetMapping("/create-quest")
    public ModelAndView getCreateQuestPage() {
        return new ModelAndView("create-quest");
    }

    @PostMapping("/create-quest")
    public ModelAndView createQuest(@RequestParam("json") String json) {
        Quest quest;
        try {
            quest = QuestParser.parseFromJson(json);
        } catch (Exception e) {
            return new ModelAndView("create-quest");
        }
        questService.createIfNotExists(quest);

        return new ModelAndView("redirect:/quest?name=" + quest.getName());
    }

}
