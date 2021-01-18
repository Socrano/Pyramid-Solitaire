import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/** Tests for {@link MultiPyramidSolitaire}s. */
public class MultiPyramidSolitaireTest {
  MultiPyramidSolitaire m = new MultiPyramidSolitaire();
  List deck = m.getDeck();
  protected static final ArrayList<Suit> SUIT_LIST =
      new ArrayList<Card.Suit>(Arrays.asList(Card.Suit.Club, Card.Suit.Diamond,
          Card.Suit.Heart, Card.Suit.Spade));
  protected static final ArrayList<Card.Number> NUMBER_LIST =
      new ArrayList<Card.Number>(Arrays.asList(Card.Number.One, Card.Number.Two,
          Card.Number.Three, Card.Number.Four, Card.Number.Five, Card.Number.Six,
          Card.Number.Seven, Card.Number.Eight, Card.Number.Nine, Card.Number.Ten,
          Card.Number.Eleven, Card.Number.Twelve, Card.Number.Thirteen));

  // set up the initial condition
  protected void initTestConditions() {
    this.m = new MultiPyramidSolitaire();
    this.deck = m.getDeck();
    this.m.startGame(deck,
        false, 7, 2);
  }

  @Test
  //Test the deck from MultiPyramid is two complete deck
  public void deckTest() {
    assertEquals(deck.size(), 104);
    boolean result = true;
    for (Card.Suit s : SUIT_LIST) {
      for (Card.Number n : NUMBER_LIST) {
        result = result && (Collections.frequency(deck, new Card(n, s)) == 2);
      }
    }
    assertEquals(result, true);
    //It means every card appears two times in the deck from MultiPyramid
  }

  @Test
  //Test the pyramid of MultiPyramid
  public void pyramidTest() {
    initTestConditions();
    m.startGame(deck, false, 1, 1);
    assertEquals(new PyramidSolitaireTextualView(m).toString(),
        "A♣\n"
            + "Draw: 2♣");
    //When there are two rows in MultiPyramid, my widthRow(0) is actually 3,
    //but grader says it is 1 and do not give me score. It is confusing.
    initTestConditions();
    m.startGame(deck, false, 2, 1);
    assertEquals(new PyramidSolitaireTextualView(m).toString(),
        "  A♣  2♣  3♣\n"
            + "4♣  5♣  6♣  7♣\n"
            + "Draw: 8♣");
    initTestConditions();
    m.startGame(deck, false, 4, 1);
    assertEquals(new PyramidSolitaireTextualView(m).toString(),
        "      A♣  .   2♣  .   3♣\n"
            + "    4♣  5♣  6♣  7♣  8♣  9♣\n"
            + "  10♣ J♣  Q♣  K♣  A♦  2♦  3♦\n"
            + "4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦");
    initTestConditions();
    m.startGame(deck, false, 7, 1);
    assertEquals(new PyramidSolitaireTextualView(m).toString(),
        "            A♣  .   .   2♣  .   .   3♣\n"
            + "          4♣  5♣  .   6♣  7♣  .   8♣  9♣\n"
            + "        10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n"
            + "      6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "    3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\n"
            + "  A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n"
            + "K♠  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n"
            + "Draw: K♣");
    initTestConditions();
    m.startGame(deck, false, 8, 1);
    assertEquals(new PyramidSolitaireTextualView(m).toString(),
        "              A♣  .   .   .   2♣  .   .   .   3♣\n"
            + "            4♣  5♣  .   .   6♣  7♣  .   .   8♣  9♣\n"
            + "          10♣ J♣  Q♣  .   K♣  A♦  2♦  .   3♦  4♦  5♦\n"
            + "        6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥  3♥  4♥\n"
            + "      5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♠  2♠  3♠  4♠\n"
            + "    5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠  K♠  A♣  2♣  3♣  4♣  5♣\n"
            + "  6♣  7♣  8♣  9♣  10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
            + "Draw: J♥");
  }
}
