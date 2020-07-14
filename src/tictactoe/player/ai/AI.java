package tictactoe.player.ai;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.player.Player;

import java.util.Random;

public interface AI extends Player {

    Random random = new Random();

    default void moveRandomly(Board board) {
        int boardSize = board.getBoardSize();

        boolean validMove = false;
        while (!validMove) {
            int x = random.nextInt(boardSize) + 1;
            int y = random.nextInt(boardSize) + 1;

            if (x <= boardSize && x >= 1 && y <= boardSize && y >= 1) {
                validMove = board.putCell(x, y);
            }
        }
    }

    default boolean checkNextTurnWin(Board board, Cell cell) {
        for (int i = 1; i < board.getBoardSize() + 1; i++) {
            for (int j = 1; j < board.getBoardSize() + 1; j++) {
                if (board.willWin(i, j, cell)) {
                    board.putCell(i, j);
                    return true;
                }
            }
        }
        return false;
    }
}
