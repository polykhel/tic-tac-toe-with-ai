package tictactoe.player.user;

import tictactoe.Board;
import tictactoe.player.Player;

import java.util.Scanner;

public class User implements Player {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void move(Board board) {
        boolean validMove = false;

        while (!validMove) {
            try {
                System.out.println("Enter the coordinates: ");
                String[] userInput = scanner.nextLine().split(" ");

                int x = Integer.parseInt(userInput[0]);
                int y = Integer.parseInt(userInput[1]);
                int boardSize = board.getBoardSize();

                if (x > boardSize || x < 1 || y > boardSize || y < 1) {
                    System.out.println("Coordinates should be from 1 to " + boardSize + "!");
                } else {
                    validMove = board.putCell(x, y);
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not a valid coordinate!");
            }
        }

        System.out.println(board);
    }
}
