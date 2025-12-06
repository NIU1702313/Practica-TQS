package cat.uab.Model;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;

    public Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public boolean reveal() {
        if (this.isRevealed)
            return false;

        this.isRevealed = true;
        return this.isMine;
    }

    public void toggleFlag() {
        this.isFlagged = !this.isFlagged;
    }

    public boolean getIsMine() {
        return this.isMine;
    }

    public boolean getIsRevealed() {
        return this.isRevealed;
    }

    public boolean getIsFlagged() {
        return this.isFlagged;
    }
}