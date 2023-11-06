package model;

public class Game {
    private Board board;

    public Game(int rows, int cols, int totalMines) {
        board = new Board(rows, cols, totalMines);
    }

    public void play() {
        board.playGame();
    }
}
