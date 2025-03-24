package org.noughtsandcrosses.datasource.mapper;

import org.noughtsandcrosses.datasource.model.GameEntity;
import org.noughtsandcrosses.domain.model.GameModel;

public class GameMapper {
    public static GameEntity toEntity(GameModel gameModel) {
        return new GameEntity(gameModel.getId(), gameModel.getGameField().getGameField());
    }

    public static GameModel toDomain(GameEntity gameEntity) {
        GameModel gameModel = new GameModel();
        gameModel.setId(gameEntity.getId());
        gameModel.getGameField().setGameField(gameEntity.getGameField());
        return gameModel;
    }
}
