package com.javarush.kotovych.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String login;
    private String password;

    private int wins;
    private int losses;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString(){
        return login;
    }
}
