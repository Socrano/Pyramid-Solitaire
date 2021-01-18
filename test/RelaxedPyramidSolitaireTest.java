import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.util.List;
import org.junit.Test;

/** Tests for {@link RelaxedPyramidSolitaire}s. */
public class RelaxedPyramidSolitaireTest {
  RelaxedPyramidSolitaire r = new RelaxedPyramidSolitaire();
  List deck = r.getDeck();

  // set up the initial condition
  protected void initTestConditions() {
    this.r = new RelaxedPyramidSolitaire();
    this.deck = r.getDeck();
    this.r.startGame(deck,
        false, 7, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  //To test that relax remove works correctly
  public void relaxedRemove() {
    initTestConditions();
    r.removeUsingDraw(1, 6, 0);
    assertEquals(new PyramidSolitaireTextualView(r).toString(),
        "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + ".   10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥, 5♥");
    r.remove(5, 0, 6, 1);
    assertEquals(new PyramidSolitaireTextualView(r).toString(),
        "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  .   4♦  5♦  6♦  7♦  8♦\n"
            + ".   .   J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥, 5♥");
    //For incorect situation, relax remove will not work
    r.remove(6, 2, 5, 1);
    assertEquals(new PyramidSolitaireTextualView(r).toString(),
        "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  .   4♦  5♦  6♦  7♦  8♦\n"
            + ".   .   J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥, 5♥");
  }

  @Test
  //Test the isGameOver work correctly when the rule changes
  public void isGameOverTesT() {
    initTestConditions();
    r.removeUsingDraw(1, 6, 0);
    r.remove(6, 2, 6, 6);
    r.remove(6, 3, 6, 5);
    r.remove(6, 4);
    r.remove(5, 2, 5, 5);
    r.remove(5, 3, 5, 4);
    r.remove(4, 2);
    for (int i = 0; i < 23; i++) {
      r.discardDraw(0);
    }
    assertEquals(r.isGameOver(), false);

    //For the same condition in BASIC, the game is over
    BasicPyramidSolitaire b = new BasicPyramidSolitaire();
    b.startGame(b.getDeck(),  false, 7, 3);
    b.removeUsingDraw(1, 6, 0);
    b.remove(6, 2, 6, 6);
    b.remove(6, 3, 6, 5);
    b.remove(6, 4);
    b.remove(5, 2, 5, 5);
    b.remove(5, 3, 5, 4);
    b.remove(4, 2);
    for (int i = 0; i < 23; i++) {
      b.discardDraw(0);
    }
    assertEquals(b.isGameOver(), true);
  }
}
