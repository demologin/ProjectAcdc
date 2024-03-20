package com.javarush.kotovych.repository;

import com.javarush.kotovych.quest.Quest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Repository
public class QuestRepository implements Repository<Quest> {
    private final Map<Long, Quest> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    private final Map<String, Quest> questMap = new HashMap<>();

    @Override
    public Collection<Quest> getAll() {
        return map.values();
    }

    @Override
    public Optional<Quest> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    public Optional<Quest> get(String name) {
        return Optional.ofNullable(questMap.get(name));
    }

    @Override
    public void create(Quest entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Quest entity) {
        map.put(entity.getId(), entity);
        questMap.put(entity.getName(), entity);
    }

    @Override
    public void delete(Quest entity) {
        map.remove(entity.getId());
    }
}
