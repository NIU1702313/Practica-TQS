package cat.uab.Controller;

import cat.uab.RandomNumberGenerator;
import cat.uab.Model.Board;
import cat.uab.View.BoardView;

public class BoardController {
  private BoardView gv;
  private Board Board;

  public BoardController(int rows, int columns, int numMines, RandomNumberGenerator rng) {
    this.gv = new BoardView();
    this.Board = new Board(rows, columns, numMines, rng);
  }

  public void render() {
    this.gv.showBoard(this.Board);
  }

  public boolean reveal(int row, int column) {
    return this.Board.reveal(row, column);
  }

  public int getRows() {
    return this.Board.getRows();
  }

  public int getColumns() {
    return this.Board.getColumns();
  }

  public int getNumMines() {
    return this.Board.getNumMines();
  }

  public boolean getMinesPlaced() {
    return this.Board.getMinesPlaced();
  }

  public CellController getCell(int row, int column) {
    return this.Board.getCell(row, column);
  }
}