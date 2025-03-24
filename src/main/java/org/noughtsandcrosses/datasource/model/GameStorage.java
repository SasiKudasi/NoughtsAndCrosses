package org.noughtsandcrosses.datasource.model;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GameStorage {
    private final ConcurrentHashMap<UUID, GameEntity> gameStorage = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void save(UUID id, GameEntity gameEntity) {
        lock.writeLock().lock(); // Блокируем запись
        try {
            gameStorage.put(id, gameEntity);
        } finally {
            lock.writeLock().unlock(); // Разблокируем
        }
    }

    public GameEntity get(UUID id) {
        return gameStorage.get(id);
    }
}
