package cat.uab.View;

import cat.uab.Model.Board;

public class BoardView {
    public void showBoard(Board board) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.print("  ");
        for (int j = 0; j < board.getColumns(); j++) {
            System.out.printf("%s ", alphabet.charAt(j));
        }
        System.out.print("\n");

        for (int i = 0; i < board.getRows(); i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < board.getColumns(); j++) {
                board.getCell(i, j).render();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}