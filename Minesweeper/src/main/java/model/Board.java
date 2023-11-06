package model;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private int rows;
    private int cols;
    private int totalMines;
    private Cell[][] cells;
    private boolean gameOver;
    private int unrevealedCount;

    public Board(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        cells = new Cell[rows][cols];
        gameOver = false;
        unrevealedCount = rows * cols;
        generateBoard();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            drawBoard();
            System.out.print("Enter row, column, action (e.g., 1 2 reveal/flag): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            String action = scanner.next();

            if (isValidCell(row, col)) {
                if (action.equalsIgnoreCase("reveal")) {
                    if (!cells[row][col].isRevealed()) {
                        cells[row][col].reveal();
                        unrevealedCount--;
                        if (cells[row][col].hasMine()) {
                            gameOver = true;
                            drawBoard();
                            System.out.println("Game over! You hit a mine.");
                            break;
                        }
                    }
                } else if (action.equalsIgnoreCase("flag")) {
                    cells[row][col].toggleFlagged();
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }

            if (unrevealedCount == totalMines) {
                gameOver = true;
                drawBoard();
                System.out.println("Congratulations! You've won!");
            }
        }
    }

    private void generateBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell();
            }
        }
        placeMines();
    }

    private void placeMines() {
        Random random = new Random();

        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!cells[row][col].hasMine()) {
                cells[row][col].setMine();
                minesPlaced++;
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void drawBoard() {
        System.out.println("Minesweeper Game");
        System.out.print(" ");
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + c);
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < cols; c++) {
                if (cells[r][c].isRevealed()) {
                    if (cells[r][c].hasMine()) {
                        System.out.print("* ");
                    } else {
                        int minesNearby = countMinesNearby(r, c);
                        if (minesNearby > 0) {
                            System.out.print(minesNearby + " ");
                        } else {
                            System.out.print("0 ");
                        }
                    }
                } else if (cells[r][c].isFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    private int countMinesNearby(int row, int col) {
        int count = 0;
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                int newRow = row + r;
                int newCol = col + c;
                if (isValidCell(newRow, newCol) && cells[newRow][newCol].hasMine()) {
                    count++;
                }
            }
        }
        return count;
    }
}
