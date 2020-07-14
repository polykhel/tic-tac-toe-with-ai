package tictactoe.player.ai;

import tictactoe.Board;

public class MediumAI implements AI {

    @Override
    public void move(Board board) {
        boolean done = false;
        if (tryWin(board)) done = true;

        if (!done && tryDefend(board)) done = true;

        if (!done) moveRandomly(board);

        System.out.println("Making move level \"medium\"");
        System.out.println(board);
    }

    private boolean tryWin(Board board) {
        return checkNextTurnWin(board, board.getNextMove());
    }

    private boolean tryDefend(Board board) {
        return checkNextTurnWin(board, board.getNextMove().opposite());
    }
}
