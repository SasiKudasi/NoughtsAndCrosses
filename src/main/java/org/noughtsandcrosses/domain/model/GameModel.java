package org.noughtsandcrosses.domain.model;

import java.util.UUID;

public class GameModel {

    private UUID id;


    private GameField gameField;

    public GameModel() {
        this.id = UUID.randomUUID();
        this.gameField = new GameField();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
