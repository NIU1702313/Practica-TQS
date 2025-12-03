package cat.uab.View;

import cat.uab.Model.Board;

public class BoardView {
    public void showBoard(Board Board) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.print("  ");
        for (int j = 0; j < Board.getColumns(); j++) {
            System.out.printf("%s ", alphabet.charAt(j));
        }
        System.out.print("\n");

        for (int i = 0; i < Board.getRows(); i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < Board.getColumns(); j++) {
                Board.getCell(i, j).render();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}