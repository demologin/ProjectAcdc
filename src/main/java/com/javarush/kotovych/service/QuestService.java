package com.javarush.kotovych.service;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.controller.MainPageController;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import com.javarush.kotovych.util.QuestParser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@Service
@RestController
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @PostConstruct
    public void init() {
        addDefaultQuests();
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }

    public Optional<Quest> get(String name) {
        return questRepository.get(name);
    }

    public void createIfNotExists(Quest quest) {
        if (get(quest.getName()).isEmpty()) {
            create(quest);
        }
    }

    public boolean checkIfExists(long id) {
        Optional<Quest> userOptional = questRepository.get(id);
        return userOptional.isPresent();
    }

    public boolean checkIfExists(String name) {
        Optional<Quest> userOptional = questRepository.get(name);
        return userOptional.isPresent();
    }

    private void addDefaultQuests(){
        for(String path : Constants.DEFAULT_QUESTS) {
            Quest quest = QuestParser.parseFromJsonFile(MainPageController.class.getResource(path));
            createIfNotExists(quest);
        }
    }
}
