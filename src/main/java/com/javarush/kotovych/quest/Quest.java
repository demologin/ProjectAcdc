package com.javarush.kotovych.quest;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

public class Quest {
    public String name;
    public String description;

    @Getter
    @Setter
    private long id;

    public Quest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
