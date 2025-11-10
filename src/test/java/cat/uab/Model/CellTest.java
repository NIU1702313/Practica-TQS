package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {

    // TDD

    @Test

    public void test_Constructor() {
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsMine());
        assertFalse(cell.getIsRevealed());

    }

    @Test

    public void test_ConstructorP() {
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

        // mirem que segueix sent true
        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());

    }

    @Test
    public void test_toggleFlag() {
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        cell.toggleFlag();
        assertTrue(cell.getIsFlagged());

        // mirem que s'ha girat
        cell.toggleFlag();
        assertFalse(cell.getIsFlagged());

    }

}
