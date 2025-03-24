package org.noughtsandcrosses.domain.service;

import org.noughtsandcrosses.domain.model.GameField;

public interface GameService {

    GameField findBestMove(GameField field, int player);
    boolean isValidMove(GameField previousField, GameField currentField, int player);
    int isGameWining(GameField field);

}
