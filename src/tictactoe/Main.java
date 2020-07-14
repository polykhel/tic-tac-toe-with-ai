package tictactoe;

import tictactoe.player.Player;
import tictactoe.player.ai.EasyAI;
import tictactoe.player.ai.HardAI;
import tictactoe.player.ai.MediumAI;
import tictactoe.player.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String[] command = scanner.nextLine().split(" ");

            try {
                if (command[0].equals("exit")) {
                    break;
                } else if (command[0].equals("start")) {
                    Player player1 = getPlayer(command[1]);
                    Player player2 = getPlayer(command[2]);
                    Game game = new Game(player1, player2);
                    game.start();
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Bad parameters!");
            }
        }
    }

    private static Player getPlayer(String parameter) {
        switch (parameter) {
            case "user":
                return new User();
            case "easy":
                return new EasyAI();
            case "medium":
                return new MediumAI();
            case "hard":
                return new HardAI();
        }
        return null;
    }
}


