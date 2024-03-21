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
    private Role role;

    private int wins;
    private int losses;

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString(){
        return login;
    }
}
