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

    public void test_ConstructorP(){
        Cell cell = new Cell(true);
        assertTrue(cell.getIsMine());
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsRevealed());
        

    }

    

}
