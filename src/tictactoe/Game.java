package tictactoe;

import tictactoe.player.Player;

import static tictactoe.GameState.*;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        System.out.println(board);

        do {
            player1.move(board);
            if (board.checkGameState() != NOT_FINISHED) break;
            player2.move(board);
            if (board.checkGameState() != NOT_FINISHED) break;
        } while (true);

        switch (board.checkGameState()) {
            case DRAW:
                System.out.println("Draw");
                break;
            case X_WIN:
                System.out.println("X wins");
                break;
            case O_WIN:
                System.out.println("O wins");
                break;
            case INVALID:
                System.out.println("Impossible!");
        }
    }
}