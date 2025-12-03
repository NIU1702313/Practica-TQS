package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import cat.uab.RandomNumberGenerator;

public class BoardTest {
    @Test
    public void test_Constructor() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        Board Board = new Board(4, 5, 3, rng);

        assertEquals(4, Board.getRows());
        assertEquals(5, Board.getColumns());
        assertEquals(3, Board.getNumMines());
    }

    @Test
    public void test_reveal_Range() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Board Board = new Board(4, 5, 3, rng);

        try {
            Board.reveal(-1, -1);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        try {
            Board.reveal(4, 5);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        Board.reveal(0, 0);
    }

}
