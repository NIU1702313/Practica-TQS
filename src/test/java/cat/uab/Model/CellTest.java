package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {
    @Test
<<<<<<< HEAD
    public void test_constructor() {
=======

    public void test_Constructor() {
>>>>>>> 81a64349bd34833b2f163f7e841bf4a16beeb907
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsMine());
        assertFalse(cell.getIsRevealed());
    }

    @Test
<<<<<<< HEAD
    public void test_constructorWithParameters() {
=======

    public void test_ConstructorP() {
>>>>>>> 81a64349bd34833b2f163f7e841bf4a16beeb907
        Cell cell = new Cell(true);
        assertTrue(cell.getIsMine());
        assertFalse(cell.getIsFlagged());
        assertFalse(cell.getIsRevealed());
<<<<<<< HEAD
=======

>>>>>>> 81a64349bd34833b2f163f7e841bf4a16beeb907
    }

    @Test
    public void test_reveal() {
        Cell cell = new Cell();
        assertFalse(cell.getIsRevealed());
        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());

<<<<<<< HEAD
        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());
=======
        // mirem que segueix sent true
        assertFalse(cell.reveal());
        assertTrue(cell.getIsRevealed());

>>>>>>> 81a64349bd34833b2f163f7e841bf4a16beeb907
    }

    @Test
    public void test_toggleFlag() {
        Cell cell = new Cell();
        assertFalse(cell.getIsFlagged());
        cell.toggleFlag();
        assertTrue(cell.getIsFlagged());

<<<<<<< HEAD
        cell.toggleFlag();
        assertFalse(cell.getIsFlagged());
    }
=======
        // mirem que s'ha girat
        cell.toggleFlag();
        assertFalse(cell.getIsFlagged());

    }

>>>>>>> 81a64349bd34833b2f163f7e841bf4a16beeb907
}
