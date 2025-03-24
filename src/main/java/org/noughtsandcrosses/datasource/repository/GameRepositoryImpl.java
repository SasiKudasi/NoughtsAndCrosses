package org.noughtsandcrosses.datasource.repository;

import org.noughtsandcrosses.datasource.mapper.GameMapper;
import org.noughtsandcrosses.datasource.model.GameStorage;
import org.noughtsandcrosses.domain.model.GameModel;

import java.util.UUID;

public class GameRepositoryImpl implements GameRepository {
    public final GameStorage storage = new GameStorage();

    @Override
    public void saveGame(GameModel game) {
        storage.save(game.getId(), GameMapper.toEntity(game));
    }

    @Override
    public GameModel getGame(UUID id) {
        var entity = storage.get(id);
        return entity != null ? GameMapper.toDomain(entity) : null;
    }
}
