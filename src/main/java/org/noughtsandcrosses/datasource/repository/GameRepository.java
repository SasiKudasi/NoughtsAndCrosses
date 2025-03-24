package org.noughtsandcrosses.datasource.repository;

import org.noughtsandcrosses.domain.model.GameModel;

import java.util.UUID;

public interface GameRepository {
    void saveGame(GameModel game);
    GameModel getGame(UUID id);
}
