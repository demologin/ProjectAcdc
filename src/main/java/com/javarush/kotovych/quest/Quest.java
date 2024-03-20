package com.javarush.kotovych.quest;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Quest {
    private String name;
    private String description;
    private long id;

    public Quest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
