package cat.uab.Model;

import cat.uab.RandomNumberGenerator;
import cat.uab.Controller.CellController;

public class Board {
    private CellController[][] board;
    private boolean areMinesPlaced = false;
    private int rows;
    private int columns;
    private int numMines;
    private RandomNumberGenerator rng;

    public Board(int rows, int columns, int numMines, RandomNumberGenerator rng) {
        this.rows = rows;
        this.columns = columns;
        this.numMines = numMines;
        this.rng = rng;
        this.board = new CellController[rows][columns];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                this.board[i][j] = new CellController(0, false);
    }

    public boolean reveal(int row, int column) {
        if (row < 0 || column < 0 || row >= this.rows || column >= this.columns)
            throw new IllegalArgumentException("Row or column out of range");

        if (!this.areMinesPlaced) {
            this.placeMines(row, column);
            return false;
        }

        return board[row][column].reveal();
    }

    private void placeMines(int row, int column) {
        if (this.areMinesPlaced)
            return;
        this.areMinesPlaced = true;

        board = new CellController[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                board[i][j] = new CellController(this.numMines, false);

        int[][] mines = this.generateMines();

        for (int i = 0; i < this.numMines; i++) {
            int r = mines[i][0];
            int c = mines[i][1];
            board[r][c] = new CellController(this.numMines, true);
        }
    }

    private int[][] generateMines() {
        int[][] mines = new int[this.numMines][2];
        for (int i = 0; i < this.numMines; i++) {
            mines[i][0] = this.rng.randomInt(0, this.rows - 1);
            mines[i][1] = this.rng.randomInt(0, this.columns - 1);
        }
        return mines;
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

    public boolean getMinesPlaced() {
        return this.areMinesPlaced;
    }

    public CellController getCell(int row, int column) {
        return this.board[row][column];
    }
}