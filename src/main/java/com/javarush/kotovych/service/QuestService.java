package com.javarush.kotovych.service;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class QuestService {

    @Autowired
    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
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
}
