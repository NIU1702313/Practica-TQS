package cat.uab.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import cat.uab.RandomNumberGenerator;

public class GameTest {
    
    //Tests TDD
    @Test
    public void test_CreacioTaulerCorrectament()
    {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        Game game = new Game(4,5,3,rng);
        
        assertEquals(4,game.getRows());
        assertEquals(5, game.getColumns());
        assertEquals(3, game.getNumMines());
        
    }




    



}
