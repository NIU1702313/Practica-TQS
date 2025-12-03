package cat.uab.View;

import cat.uab.Model.Cell;

public class CellView {
  public void showCell(Cell cell, int nBombs) {
    if (cell.getIsRevealed()) {
      switch (nBombs) {
        case 0:
          System.out.print(" ");
          return;

        case 1:
          System.out.print("\u001B[34m1\u001B[0m");
          return;

        case 2:
          System.out.print("\u001B[32m2\u001B[0m");
          return;

        case 3:
          System.out.print("\u001B[33m3\u001B[0m");
          return;

        case 4:
          System.out.print("\u001B[35m4\u001B[0m");
          return;

        case 5:
          System.out.print("\u001B[31m5\u001B[0m");
          return;

        case 6:
          System.out.print("\u001B[31m6\u001B[0m");
          return;

        case 7:
          System.out.print("\u001B[36m7\u001B[0m");
          return;

        case 8:
          System.out.print("\u001B[0m8\u001B[0m");
          return;

        default:
          break;
      }
    } else {
      System.out.print("■");
    }
  }
}
