package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import cat.uab.RandomNumberGenerator;

public class BoardTest {
    @Test
    public void test_Constructor() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        Board board = new Board(4, 5, 3, rng);

        assertEquals(4, board.getRows());
        assertEquals(5, board.getColumns());
        assertEquals(3, board.getNumMines());
    }

    @Test
    public void test_reveal_Range() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Board board = new Board(4, 5, 3, rng);

        try {
            board.reveal(-1, -1);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        try {
            board.reveal(4, 5);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        board.reveal(0, 0);
    }

}
