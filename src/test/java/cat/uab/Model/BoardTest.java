package cat.uab.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.RandomNumberGenerator;
import cat.uab.Controller.BoardController;

public class BoardTest {
    public RandomNumberGenerator rng;

    @BeforeEach
    public void setUp() {
        this.rng = mock(RandomNumberGenerator.class);
    }

    @Test
    public void test_Constructor() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        BoardController board = new BoardController(4, 5, 3, rng);

        assertEquals(4, board.getRows());
        assertEquals(5, board.getColumns());
        assertEquals(3, board.getNumMines());
    }

    @Test
    public void test_reveal_Range() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        BoardController board = new BoardController(4, 5, 3, rng);

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

    @Test
    public void test_reveal_propagation() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        BoardController board = new BoardController(5, 5, 0, rng); // No mines for this test

        assertFalse(board.checkWin());

        board.reveal(2, 2);

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertTrue(board.getCell(i, j).getIsRevealed(), "Cell at (" + i + ", " + j + ") should be revealed");
            }
        }

        assertTrue(board.checkWin());
    }

    @Test
    public void test_toggleFlag_range() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        BoardController board = new BoardController(4, 5, 3, rng);

        // Valors frontera = (0,0) i (4,5)
        // Valors límit = (-1,-1), (1,1), (3,4), i (5,6)

        try {
            board.toggleFlag(-1, -1);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }
        board.toggleFlag(0, 0);
        board.toggleFlag(1, 1);

        // Cal que areMinesPlaced sigui true per poder cridar a toggleFlag i
        // poder testejar totes les condicions de la funció.
        board.reveal(0, 0);

        board.toggleFlag(3, 4);
        try {
            board.toggleFlag(4, 5);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }
        try {
            board.toggleFlag(5, 6);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        } catch (Exception e) {
            fail("Expected an IllegalArgumentException");
        }

    }

    @Test
    public void test_reveal_conditions() {
        // Mockegem el RandomNumberGenerator per col·locar mines a (1,1),
        // (2,2).
        when(this.rng.randomInt(anyInt(), anyInt()))
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(2);

        BoardController board = new BoardController(5, 5, 2, this.rng);

        // Cal que areMinesPlaced sigui false per poder testejar la condició
        // de que si són false es col·loquen les mines.
        assertFalse(board.areMinesPlaced());

        // Cridem a reveal per primer cop per col·locar les mines.
        board.reveal(0, 4);
        assertTrue(board.areMinesPlaced());
        assertTrue(board.getCell(0, 4).getIsRevealed());

        // Cridem a reveal per segon cop per comprovar que no fa res.
        board.reveal(0, 4);
        assertTrue(board.getCell(0, 4).getIsRevealed());

        // Ara revelem una cel·la que és una mina i comprovem que retorna true.
        assertTrue(board.reveal(1, 1));
    }

    @Test
    public void test_placeMines_onMine() {
        // Mockegem el RandomNumberGenerator per col·locar mines a (1,1)
        // i (2,2).
        when(this.rng.randomInt(anyInt(), anyInt()))
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(4)
                .thenReturn(4);

        BoardController board = new BoardController(5, 5, 2, this.rng);

        // Cridem a reveal per primer cop per col·locar les mines.
        board.reveal(1, 1);
        assertTrue(board.areMinesPlaced());
        assertTrue(board.getCell(3, 3).getIsMine());
        assertTrue(board.getCell(4, 4).getIsMine());
    }

    @Test
    public void test_placeMines_surroundingMinesNonZero() {
        // Mockegem el RandomNumberGenerator per col·locar mines a (1,1)
        // i (2,2).
        when(this.rng.randomInt(anyInt(), anyInt()))
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(4)
                .thenReturn(4);

        BoardController board = new BoardController(5, 5, 2, this.rng);

        // Cridem a reveal per primer cop per col·locar les mines.
        board.reveal(0, 0);
        assertTrue(board.areMinesPlaced());
        assertTrue(board.getCell(3, 3).getIsMine());
        assertTrue(board.getCell(4, 4).getIsMine());
    }

    @Test
    public void test_showMines() {
        // Mockegem el RandomNumberGenerator per col·locar mines a (1,1)
        // i (2,2).
        when(this.rng.randomInt(anyInt(), anyInt()))
                .thenReturn(0)
                .thenReturn(0)
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(0)
                .thenReturn(2)
                .thenReturn(0)
                .thenReturn(3);

        BoardController board = new BoardController(5, 5, 4, this.rng);

        // Fem pairwise testing:
        // - (0,0) està flaggejada i és mina -> Ha de quedar flaggejada
        // - (0,1) no està flaggejada i és mina -> Ha de ser flaggejada
        // - (0,2) està flaggejada i no és mina -> Ha de ser desflaggejada
        // - (0,3) no està flaggejada i no és mina -> Ha de quedar desflaggejada

        board.getCell(0, 0).toggleFlag();
        board.getCell(0, 2).toggleFlag();

        board.showMines();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertEquals(board.getCell(i, j).getIsFlagged(), board.getCell(i, j).getIsMine());
            }
        }
    }

    @Test
    public void test_duplicateMines() {
        // Mockegem el RandomNumberGenerator per generar mines duplicades.
        when(this.rng.randomInt(anyInt(), anyInt()))
                .thenReturn(0)
                .thenReturn(0)

                .thenReturn(0)
                .thenReturn(0)

                .thenReturn(0)
                .thenReturn(0)

                .thenReturn(0)
                .thenReturn(1);

        BoardController board = new BoardController(5, 5, 2, this.rng);

        // Col·loquem les mines.
        board.reveal(4, 4);

        // Comprovem que les mines finals són les dues últimes, ja que llavors
        // s'ha adonat que estaven duplicades.
        assertTrue(board.getCell(0, 0).getIsMine());
        assertTrue(board.getCell(0, 1).getIsMine());
    }
}
