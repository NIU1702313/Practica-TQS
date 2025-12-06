package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cat.uab.Controller.CellController;

public class CellTest {
    @Test
    public void test_constructor() {
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsMine());
        assertFalse(cell.getIsRevealed());
    }

    @Test
    public void test_constructorWithParameters() {
        Cell cell = new Cell(true);
        assertTrue(cell.getIsMine());
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsRevealed());
    }

    @Test
    public void test_reveal() {
        Cell cell = new Cell();
        assertFalse(cell.getIsRevealed());
        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());

        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());
    }

    @Test
    public void test_toggleFlag() {
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        cell.toggleFlag();
        assertTrue(cell.getIsFlagged());

        cell.toggleFlag();
        assertFalse(cell.getIsFlagged());
    }

    @Test
    public void test_viewSurroundingMines() {
        // De l'1 al 8 són valors vàlids que es podria trobar en una partida
        // normal.
        // Els valors 0 i 9 són els valors frontera, i els -1, 1, 8 i 10 són
        // els valors límit.
        for (int i = -1; i <= 10; i++) {
            CellController cc = new CellController(i, false);
            cc.reveal();
            cc.render();

            assertEquals(i, cc.getNMines());
        }
    }
}
