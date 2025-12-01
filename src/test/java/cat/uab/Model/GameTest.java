package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import cat.uab.RandomNumberGenerator;

public class GameTest {
    @Test
    public void test_Constructor() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        Game game = new Game(4, 5, 3, rng);

        assertEquals(4, game.getRows());
        assertEquals(5, game.getColumns());
        assertEquals(3, game.getNumMines());
    }

    @Test
    public void test_reveal_Range() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Game game = new Game(4, 5, 3, rng);

        try {
            game.reveal(-1, -1);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        try {
            game.reveal(4, 5);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

        game.reveal(0, 0);
    }

}
