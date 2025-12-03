package cat.uab.Controller;

import cat.uab.RandomNumberGenerator;
import cat.uab.Model.Board;
import cat.uab.View.BoardView;

public class BoardController {
  private BoardView gv;
  private Board board;

  public BoardController(int rows, int columns, int numMines, RandomNumberGenerator rng) {
    this.gv = new BoardView();
    this.board = new Board(rows, columns, numMines, rng);
  }

  public void render() {
    this.gv.showBoard(this.board);
  }

  public boolean reveal(int row, int column) {
    return this.board.reveal(row, column);
  }

  public int getRows() {
    return this.board.getRows();
  }

  public int getColumns() {
    return this.board.getColumns();
  }

  public int getNumMines() {
    return this.board.getNumMines();
  }

  public boolean getMinesPlaced() {
    return this.board.getMinesPlaced();
  }

  public CellController getCell(int row, int column) {
    return this.board.getCell(row, column);
  }
}