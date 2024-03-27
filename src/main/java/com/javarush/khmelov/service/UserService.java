package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Game;
import com.javarush.khmelov.entity.GameState;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        User userPattern = User.builder().id(id).build();
        return userRepository.find(userPattern).findFirst();
    }

    public Optional<User> get(String login, String password) {
        User patternUser = User
                .builder()
                .login(login)
                .password(password)
                .build();
        return userRepository.find(patternUser).findAny();
    }

}