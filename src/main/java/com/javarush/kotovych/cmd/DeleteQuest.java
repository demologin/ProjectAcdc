package com.javarush.kotovych.cmd;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DeleteQuest {

    @Autowired
    QuestService questService;

    @PostMapping("/delete-quest")
    public ModelAndView deleteQuest(@RequestParam(value = Constants.ID, defaultValue = Constants.DEFAULT_ID) long id) {
        if (questService.checkIfExists(id)) {
            questService.delete(id);
        }
        return new ModelAndView(Constants.MAIN_PAGE_REDIRECT);
    }
}
