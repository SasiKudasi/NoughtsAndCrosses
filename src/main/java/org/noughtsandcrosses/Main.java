package org.noughtsandcrosses;

import org.noughtsandcrosses.domain.model.GameField;
import org.noughtsandcrosses.domain.service.GameServiceImpl;

public class Main {

    public static void main(String[] args) {


        GameField field = new GameField();
        GameServiceImpl gameService = new GameServiceImpl();

        // Устанавливаем тестовое состояние поля
        int[][] testBoard = {
                {2, 0, 0},
                {0, 1, 2},
                {2, 0, 0}
        };

        field.setGameField(testBoard); // Передаем поле в объект

        // Найдём координаты лучшего хода
        var gameField = gameService.findBestMove(field, 1);
        var newField = gameField.getGameField();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(newField[i][j] +"| ");
            }
            System.out.println();
        }
    }
}



