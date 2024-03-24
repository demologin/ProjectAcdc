package com.javarush.kotovych.quest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class Quest {
    private String name;
    private String description;
    private long id;
    private Map<String, Question> questions;

    public Quest(String name, String description, Map<String, Question> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
    }
}