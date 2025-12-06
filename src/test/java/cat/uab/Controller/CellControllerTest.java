package cat.uab.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellControllerTest {
  @Test
  public void test_toggleFlag() {
    CellController cellController = new CellController(5, true);

    assertFalse(cellController.getIsFlagged());
    cellController.toggleFlag();
    assertTrue(cellController.getIsFlagged());
    cellController.toggleFlag();
    assertFalse(cellController.getIsFlagged());
  }
}
