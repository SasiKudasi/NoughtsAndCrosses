package org.noughtsandcrosses.domain.service;

import org.noughtsandcrosses.domain.model.GameField;

import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {
    private static final int CROSS = 1;   // AI
    private static final int ZERO = 2;    // Человек
    private static final int FREECELL = 0;

    // Структура для хранения оценки хода и его координат
    private static class Move {
        int score;
        int row;
        int col;

        Move(int score) {
            this.score = score;
        }
    }

    public int minimax(int player, GameField field) {
        Move bestMove = minimaxRecursive(player, field);
        return bestMove.score; // Возвращаем оценку лучшего хода
    }

    private Move minimaxRecursive(int player, GameField field) {
        int[][] board = field.getGameField();

        // Проверяем состояние игры
        int winner = isGameWining(field);
        if (winner == CROSS) return new Move(10);   // AI выиграл
        if (winner == ZERO) return new Move(-10);   // Игрок выиграл
        if (isBoardFull(board)) return new Move(0); // Ничья

        List<int[]> availableMoves = getFreeCells(field);
        Move bestMove = new Move(player == CROSS ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        // Перебираем все доступные ходы
        for (int[] cell : availableMoves) {
            int row = cell[0];
            int col = cell[1];

            board[row][col] = player; // Делаем ход
            int score = minimaxRecursive(player == CROSS ? ZERO : CROSS, field).score;
            board[row][col] = FREECELL; // Откатываем ход

            // Выбираем лучший ход
            if (player == CROSS) { // AI (ищем максимум)
                if (score > bestMove.score) {
                    bestMove.score = score;
                    bestMove.row = row;
                    bestMove.col = col;
                }
            } else { // Игрок (ищем минимум)
                if (score < bestMove.score) {
                    bestMove.score = score;
                    bestMove.row = row;
                    bestMove.col = col;
                }
            }
        }

        return bestMove;
    }

    // Проверка на ничью (поле заполнено)
    private boolean isBoardFull(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == FREECELL) return false;
            }
        }
        return true;
    }

    // Получение списка свободных ячеек (возвращает координаты строка-столбец)
    private List<int[]> getFreeCells(GameField gameField) {
        List<int[]> freeCells = new ArrayList<>();
        int[][] board = gameField.getGameField();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == FREECELL) {
                    freeCells.add(new int[]{i, j});
                }
            }
        }

        return freeCells;
    }

    /***
     * Проверка на завершение игры
     * @param field класс игрового поля
     * @return 1 в случае победы крестиков, 2 в случае победы ноликов, -1 в случае если игра продолжается
     */
    @Override
    public int isGameWining(GameField field) {
        int[][] gameField = field.getGameField();

        // Проверка по строкам и столбцам
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] != FREECELL && gameField[i][0] == gameField[i][1] && gameField[i][1] == gameField[i][2])
                return gameField[i][0];
            if (gameField[0][i] != FREECELL && gameField[0][i] == gameField[1][i] && gameField[1][i] == gameField[2][i])
                return gameField[0][i];
        }

        // Проверка диагоналей
        if (gameField[0][0] != FREECELL && gameField[0][0] == gameField[1][1] && gameField[1][1] == gameField[2][2])
            return gameField[0][0];
        if (gameField[0][2] != FREECELL && gameField[0][2] == gameField[1][1] && gameField[1][1] == gameField[2][0])
            return gameField[0][2];

        return -1; // Игра продолжается
    }

    @Override
    public GameField findBestMove(GameField field, int player) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        int[][] board = field.getGameField();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == FREECELL) { // Если клетка свободна
                    board[i][j] = player; // Пробуем ход
                    int score = minimax(2, field); // Запускаем минимакс для противника
                    board[i][j] = FREECELL; // Откатываем ход

                    if (score > bestScore) { // Если нашли лучший ход
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        if (bestMove[0] != -1 && bestMove[1] != -1) { // Проверяем, что нашли ход
            board[bestMove[0]][bestMove[1]] = player;
        }
        return field;
    }

    @Override
    public boolean isValidMove(GameField previousField, GameField currentField, int player) {
        var prevBoard = previousField.getGameField();
        var curBoard = currentField.getGameField();
        int moveCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (prevBoard[i][j] != FREECELL) {
                    if (prevBoard[i][j] != curBoard[i][j]) return false;
                } else {
                    if (curBoard[i][j] == player)
                        moveCount++;
                }
            }
        }
        return moveCount == 1;
    }

}
