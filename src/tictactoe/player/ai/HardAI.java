package tictactoe.player.ai;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.GameState;

public class HardAI implements AI {

    private Cell myMove;

    @Override
    public void move(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int bestX = 0;
        int bestY = 0;
        myMove = board.getNextMove();

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                if (board.isEmpty(i, j)) {
                    board.putCellAI(i, j, myMove);
                    int score = minimax(board, 0, false);
                    board.putCellAI(i, j, Cell.EMPTY);
                    if (score > bestScore) {
                        bestScore = score;
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }

        board.putCellAI(bestX, bestY, myMove);
        board.setXMove(myMove);
        System.out.println("Making move level \"hard\"");
        System.out.println(board);
    }

    int minimax(Board board, int depth, boolean maximizing) {
        GameState state = board.checkGameState();
        if (state != GameState.NOT_FINISHED) {
            int score = 0;
            if (state == GameState.X_WIN) score = myMove == Cell.X ? 1 : -1;
            else if (state == GameState.O_WIN) score = myMove == Cell.O ? 1 : -1;
            return score;
        }

        int bestScore;
        if (maximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isEmpty(i, j)) {
                        board.putCellAI(i, j, myMove);
                        int score = minimax(board, depth + 1, false);
                        board.putCellAI(i, j, Cell.EMPTY);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isEmpty(i, j)) {
                        board.putCellAI(i, j, myMove.opposite());
                        int score = minimax(board, depth + 1, true);
                        board.putCellAI(i, j, Cell.EMPTY);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
