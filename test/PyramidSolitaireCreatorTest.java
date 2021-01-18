import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import org.junit.Test;

/** Tests for {@link PyramidSolitaireCreator}s. */
public class PyramidSolitaireCreatorTest {

  @Test
  //Test for GameType toString
  public void gameTypeToStringTest() {
    assertEquals(GameType.BASIC.toString(), "basic");
    assertEquals(GameType.RELAXED.toString(), "relaxed");
    assertEquals(GameType.MULTIPYRAMID.toString(), "multipyramid");
  }

  @Test
  //Test for PyramidSolitaireCreator create
  public void createTest() {
    assertEquals((new PyramidSolitaireCreator().create(GameType.BASIC)
            instanceof BasicPyramidSolitaire),
        true);
    assertEquals((new PyramidSolitaireCreator().create(GameType.RELAXED)
            instanceof RelaxedPyramidSolitaire),
        true);
    assertEquals((new PyramidSolitaireCreator().create(GameType.MULTIPYRAMID)
            instanceof MultiPyramidSolitaire),
        true);
  }
}
