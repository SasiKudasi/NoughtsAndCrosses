package org.noughtsandcrosses.domain.service;

import org.noughtsandcrosses.domain.model.GameField;

public interface GameService {

    int gameEnd();
    boolean isValidFieldGame();
    int minimax(int player, GameField field);
    int[] getFreeCell(GameField field);

    int isGameWining (GameField field);


}
