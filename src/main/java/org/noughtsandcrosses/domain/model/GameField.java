package org.noughtsandcrosses.domain.model;

public class GameField {
    public int[][] getGameField() {
        return gameField;
    }

    public void setGameField(int[][] gameField) {
        this.gameField = gameField;
    }

    private int [][] gameField;

    public GameField() {
        this.gameField = new int[3][3];
    }



}
