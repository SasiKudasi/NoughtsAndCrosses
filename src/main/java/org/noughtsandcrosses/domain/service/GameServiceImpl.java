package org.noughtsandcrosses.domain.service;

import org.noughtsandcrosses.domain.model.GameField;

import java.util.ArrayList;

public class GameServiceImpl implements GameService {
    private final int CROSS = 1;
    private final int ZERO = 2;
    private final int FREECELL = 0;

    /***
     * Рекурсивная функция минимакс
     * getFreeCell - для поиска свободных клеток
     * GameEnd - для определения победы
     * isValidFieldGame - для валидации игрового поля текущей игры (проверь, что не изменены предыдущие ходы)
     * получаем все свободные ячейки
     * проверяем состояние игры
     * далее запускаем фор в котором для каждой свободной ячейки ставим ИИ и с новыми данными вызываем минимакс
     * и после этого где то сохраняем результат работы и кладем его например в лист
     * после из листа выбираем наилучший вариант
     * каждый раз надо в минимакс чередовать передачу хода игрока и ИИ
     * таким образом в конечном итоге мы получим просчёт всех возможных вариантов исходов событий игры
     */

    @Override
    public int gameEnd() {
        return 0;
    }

    @Override
    public int isGameWining(GameField field) {
        var gameField = field.getGameField();

        // проверка по строкам
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] != FREECELL && gameField[i][0] == gameField[i][1] && gameField[i][1] == gameField[i][2])
                return gameField[i][0];
        }
        // проверка по столбцам
        for (int i = 0; i < 3; i++) {
            if (gameField[0][i] != FREECELL && gameField[0][i] == gameField[1][i] && gameField[1][i] == gameField[2][i])
                return gameField[0][i];
        }


        if (gameField[0][0] != FREECELL && gameField[0][0] == gameField[1][1] && gameField[1][1] == gameField[2][2])
            return gameField[0][0];

        if (gameField[0][2] != FREECELL && gameField[0][2] == gameField[1][1] && gameField[1][1] == gameField[2][0])
            return gameField[0][2];

        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == FREECELL) {
                    isDraw = false;
                    break;
                }
            }
        }

        return isDraw ? 0 : -1;
    }

    @Override
    public boolean isValidFieldGame() {
        return false;
    }

    @Override
    public int minimax(int player, GameField field) {
        int[][] tmpFld = field.getGameField(); // Получаем текущее поле
        var availCellArr = getFreeCell(field); // Получаем список свободных клеток

        // Проверяем, завершена ли игра
        int winStatement = isGameWining(field);
        if (winStatement == CROSS) return 10;  // Если выиграл X (CROSS), вернуть 10
        if (winStatement == ZERO) return -10;  // Если выиграл O (ZERO), вернуть -10
        if (winStatement == -1) return  0;

        ArrayList<Integer> moves = new ArrayList<>();

        for (int i = 0; i < availCellArr.length; i++) {
            if (availCellArr[i] == 99) continue; // скипаем мусор

            int row = availCellArr[i] / 3;
            int col = availCellArr[i] % 3;

            int oldValue = tmpFld[row][col]; // Сохраняем старое значение
            tmpFld[row][col] = player; // Пробуем ход

            // Рекурсивный вызов minimax для следующего игрока
            var newField = new GameField();
            newField.setGameField(tmpFld);
            int move = minimax(player == CROSS ? ZERO : CROSS, newField);

            tmpFld[row][col] = oldValue; // Восстанавливаем поле

            moves.add(move);
        }

        // Выбираем лучший ход
        return player == CROSS
                ? moves.stream().max(Integer::compare).orElse(0)
                : moves.stream().min(Integer::compare).orElse(0);
    }

    @Override
    public int[] getFreeCell(GameField gameField) {

        var field = gameField.getGameField();
        var idx = 0;
        var idxArr = new int[9];
        for (int i = 0; i < idxArr.length; i++)
            idxArr[i] = 99; // заполняю каким то мусором, что бы в дальнейшем на него проверять
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == FREECELL)
                    idxArr[idx] = idx;
                idx++;
            }
        }
        return idxArr;
    }
}
