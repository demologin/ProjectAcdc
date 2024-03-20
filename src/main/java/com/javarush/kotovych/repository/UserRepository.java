package com.javarush.kotovych.repository;

import com.javarush.kotovych.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User>{

    private final Map<Long, User> map = new HashMap<>();
    private final Map<String, User> userMap = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    @Override
    public Collection<User> getAll() {
        return map.values();
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    public Optional<User> get(String login) {
        return Optional.ofNullable(userMap.get(login));
    }

    @Override
    public void create(User entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(User entity) {
        map.put(entity.getId(), entity);
        userMap.put(entity.getLogin(), entity);
    }

    @Override
    public void delete(User entity) {
        map.remove(entity.getId());
        userMap.remove(entity.getLogin());
    }
}
