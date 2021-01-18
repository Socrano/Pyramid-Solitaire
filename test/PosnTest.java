import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw04.Posn;
import org.junit.Test;

/** Tests for {@link Posn}s. */
public class PosnTest {
  Posn p1 = new Posn(1, 2);
  Posn p2 = new Posn(3, 4);

  @Test
  public void posnTest() {
    assertEquals(p1.getCard(), 2);
    assertEquals(p2.getCard(), 4);
    assertEquals(p1.getRow(), 1);
    assertEquals(p2.getRow(), 3);
  }
}
