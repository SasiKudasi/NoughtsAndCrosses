package org.noughtsandcrosses.domain.model;

import java.util.UUID;

public class GameModel {
    private UUID id;
    private int [][] gameField;

    public GameModel(UUID id, int[][] gameField) {
        this.id = id;
        this.gameField = gameField;
    }
}
