package model;

import java.util.Scanner;

public class MinesweeperGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of rows: ");
            int rows = scanner.nextInt();
            System.out.print("Enter the number of columns: ");
            int cols = scanner.nextInt();
            System.out.print("Enter the number of mines: ");
            int mines = scanner.nextInt();

            Game game = new Game(rows, cols, mines);
            game.play();
            System.out.print("Do you want to play again? (y/n): ");
            String playAgain = scanner.next();

            if (playAgain.equalsIgnoreCase("n")) {
                System.out.println("Exiting game.");
                break;
            } else if (playAgain.equalsIgnoreCase("y")) {
                System.out.println("Starting new game.");
                continue;
            } else {
                System.out.println("Invalid input. Exiting game.");
                break;
            }
        }
    }
}
