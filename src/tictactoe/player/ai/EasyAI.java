package tictactoe.player.ai;

import tictactoe.Board;

public class EasyAI implements AI {

    @Override
    public void move(Board board) {
        moveRandomly(board);

        System.out.println("Making move level \"easy\"");
        System.out.println(board);

    }

}