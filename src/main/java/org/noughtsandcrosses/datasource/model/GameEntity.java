package org.noughtsandcrosses.datasource.model;

import java.util.UUID;

public class GameEntity {
    private final UUID id;
    private final int[][] gameField;

    public GameEntity(UUID id, int[][] gameField) {
        this.id = id;
        this.gameField = deepCopy(gameField);
    }

    public UUID getId() {
        return id;
    }

    public int[][] getGameField() {
        return deepCopy(gameField);
    }

    private int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
}
