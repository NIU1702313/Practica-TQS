package cat.uab.Controller;

import cat.uab.Model.Cell;
import cat.uab.View.CellView;

public class CellController {
  private Cell model;
  private CellView view;
  private int nBombs;

  public CellController(int nBombs, boolean isBomb) {
    this.model = new Cell(isBomb);
    this.view = new CellView();
    this.nBombs = nBombs;
  }

  public boolean reveal() {
    return this.model.reveal();
  }

  public void toggleFlag() {
    this.model.toggleFlag();
  }

  public boolean getIsMine() {
    return this.model.getIsMine();
  }

  public boolean getIsRevealed() {
    return this.model.getIsRevealed();
  }

  public boolean getIsFlagged() {
    return this.getIsFlagged();
  }

  public void render() {
    this.view.showCell(this.model, this.nBombs);
  }
}
