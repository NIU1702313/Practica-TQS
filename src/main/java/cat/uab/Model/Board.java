package cat.uab.Model;

import java.util.List;

import cat.uab.RandomNumberGenerator;
import cat.uab.Controller.CellController;

public class Board {
    private CellController[][] board;
    private int rows;
    private int columns;
    private int numMines;
    private RandomNumberGenerator rng;
    private int[][] mines;

    public Board(int rows, int columns, int numMines, RandomNumberGenerator rng) {
        this.rows = rows;
        this.columns = columns;
        this.numMines = numMines;
        this.rng = rng;
        this.board = new CellController[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.board[i][j] = new CellController(0, false);
            }
        }
    }

    public boolean reveal(int row, int column) {
        if (!this.areValidCoords(row, column)) {
            throw new IllegalArgumentException("Row or column out of range");
        }

        this.placeMinesIfNeeded(row, column);

        if (this.board[row][column].getIsMine()) {
            return true;
        }

        if (this.board[row][column].getIsRevealed()) {
            return false;
        }

        this.board[row][column].reveal();
        if (this.board[row][column].getNMines() == 0) {
            int[][] surroundingCells = this.getSurroundingCells(row, column);
            for (int[] cell : surroundingCells) {
                if (this.areValidCoords(cell[0], cell[1]) && !this.board[cell[0]][cell[1]].getIsRevealed()) {
                    this.reveal(cell[0], cell[1]);
                }
            }
        }

        return false;
    }

    public void toggleFlag(int row, int column) {
        if (!this.areValidCoords(row, column)) {
            throw new IllegalArgumentException("Row or column out of range");
        }

        if (this.areMinesPlaced()) {
            this.board[row][column].toggleFlag();
        }
    }

    private void placeMinesIfNeeded(int row, int column) {
        if (this.areMinesPlaced()) {
            return;
        }

        this.mines = this.generateMines();
        while (this.isMine(row, column) || this.getSurroundingMines(row, column) != 0) {
            this.mines = this.generateMines();
        }

        this.board = new CellController[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.board[i][j] = new CellController(this.getSurroundingMines(i, j), false);
            }
        }

        for (int i = 0; i < this.numMines; i++) {
            int r = mines[i][0];
            int c = mines[i][1];
            this.board[r][c] = new CellController(9, true);
        }
    }

    private int[][] getSurroundingCells(int row, int column) {
        return new int[][] {
                new int[] { row - 1, column - 1 },
                new int[] { row - 1, column },
                new int[] { row - 1, column + 1 },
                new int[] { row, column - 1 },
                new int[] { row, column + 1 },
                new int[] { row + 1, column - 1 },
                new int[] { row + 1, column },
                new int[] { row + 1, column + 1 },
        };
    }

    private int getSurroundingMines(int row, int column) {
        int count = 0;

        int[][] surroundingCells = this.getSurroundingCells(row, column);
        for (int[] cell : surroundingCells) {
            if (this.areValidCoords(cell[0], cell[1]) && this.isMine(cell[0], cell[1])) {
                count++;
            }
        }

        return count;
    }

    private boolean isMine(int row, int column) {
        return List.of(this.mines).stream().anyMatch(mine -> mine[0] == row && mine[1] == column);
    }

    private boolean areValidCoords(int row, int column) {
        return (row >= 0 && row < this.rows) && (column >= 0 && column < this.columns);
    }

    private int[][] generateMines() {
        this.mines = new int[this.numMines][2];
        for (int i = 0; i < this.numMines; i++) {
            boolean duplicate;
            int row;
            int column;
            do {
                duplicate = false;
                row = this.rng.randomInt(0, this.rows - 1);
                column = this.rng.randomInt(0, this.columns - 1);
                for (int j = 0; j < i; j++) {
                    if (this.mines[j][0] == row && this.mines[j][1] == column) {
                        duplicate = true;
                        break;
                    }
                }
            } while (duplicate);

            this.mines[i][0] = row;
            this.mines[i][1] = column;
        }
        return this.mines;
    }

    public boolean checkWin() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (!this.board[i][j].getIsRevealed() && !this.board[i][j].getIsMine()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void showMines() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                CellController cell = this.board[i][j];
                if (cell.getIsMine() != cell.getIsFlagged()) {
                    cell.toggleFlag();
                }
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getNumMines() {
        return this.numMines;
    }

    public boolean areMinesPlaced() {
        return this.mines != null;
    }

    public CellController getCell(int row, int column) {
        return this.board[row][column];
    }
}