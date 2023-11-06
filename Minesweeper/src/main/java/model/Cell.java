package model;

public class Cell {
    private boolean hasMine;
    private boolean flagged;
    private boolean revealed;

    public Cell() {
        this.hasMine = false;
        this.flagged = false;
        this.revealed = false;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setMine() {
        hasMine = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void toggleFlagged() {
        flagged = !flagged;
    }

}
