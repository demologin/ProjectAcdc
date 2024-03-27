package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public Optional<User> get(String login) {
        return userRepository.get(login);
    }

    public boolean checkIfCorrect(String login, String password) {
        if (checkIfExists(login)) {
            User user = get(login).get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean checkIfExists(long id) {
        Optional<User> userOptional = userRepository.get(id);
        return userOptional.isPresent();
    }

    public boolean checkIfExists(String username) {
        Optional<User> userOptional = userRepository.get(username);
        return userOptional.isPresent();
    }

    public void delete(long id) {
        if (checkIfExists(id)) {
            User user = get(id).get();
            delete(user);
        }
    }
}
