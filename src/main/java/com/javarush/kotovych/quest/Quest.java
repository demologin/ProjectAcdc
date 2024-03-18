package com.javarush.kotovych.quest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {
    public String name;
    public long id;
    public void load(){

    }
}
