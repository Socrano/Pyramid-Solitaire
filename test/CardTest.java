import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Number;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import org.junit.Test;



/** Tests for {@link Card}s. */
public class CardTest {
  BasicPyramidSolitaire cardGenerator = new BasicPyramidSolitaire();



  @Test
  public void getCardNumberTest() {
    assertEquals(((Card) cardGenerator.getDeck().get(0)).getCardNumber(),
        1);
    assertEquals(((Card) cardGenerator.getDeck().get(11)).getCardNumber(),
        12);
  }

  @Test
  public void toStringTest() {
    assertEquals(((Card) cardGenerator.getDeck().get(0)).toString(),
        "A♣");
    assertEquals(((Card) cardGenerator.getDeck().get(13)).toString(),
        "A♦");
  }

  @Test
  public void equalsTest() {
    assertEquals(((Card) cardGenerator.getDeck().get(0)).equals(
        new Card(Card.Number.One, Card.Suit.Club)),
        true);
    assertEquals(((Card) cardGenerator.getDeck().get(12)).equals(
        new Card(Number.Thirteen, Card.Suit.Club)),
        true);
    assertEquals(((Card) cardGenerator.getDeck().get(0)).equals(
        new Card(Card.Number.Two, Card.Suit.Club)),
        false);
    assertEquals(((Card) cardGenerator.getDeck().get(11)).equals(
        new Card(Card.Number.Two, Suit.Diamond)),
        false);
    assertEquals(((Card) cardGenerator.getDeck().get(0)).equals(
        "a"),
        false);
    assertEquals(((Card) cardGenerator.getDeck().get(0)).equals(
        1),
        false);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(((Card) cardGenerator.getDeck().get(0)).hashCode()
            == new Card(Card.Number.One, Card.Suit.Club).hashCode(),
        true);
    assertEquals(((Card) cardGenerator.getDeck().get(12)).hashCode()
            == new Card(Number.Thirteen, Card.Suit.Club).hashCode(),
        true);
    assertEquals(((Card) cardGenerator.getDeck().get(0)).hashCode()
            == new Card(Card.Number.Two, Card.Suit.Club).hashCode(),
        false);
    assertEquals(((Card) cardGenerator.getDeck().get(12)).hashCode()
            == new Card(Number.Thirteen, Suit.Diamond).hashCode(),
        false);
  }

  @Test
  public void suitToStringTest() {
    assertEquals(Suit.Club.toString(), "♣");
    assertEquals(Suit.Diamond.toString(), "♦");
    assertEquals(Suit.Heart.toString(), "♥");
    assertEquals(Suit.Spade.toString(), "♠");
  }

  @Test
  public void numberGetNumberTest() {
    assertEquals(Number.One.getNumber(), 1);
    assertEquals(Number.Two.getNumber(), 2);
    assertEquals(Number.Eleven.getNumber(), 11);
    assertEquals(Number.Twelve.getNumber(), 12);
    assertEquals(Number.Thirteen.getNumber(), 13);
  }

  @Test
  public void numberToStringTest() {
    assertEquals(Number.One.toString(), "A");
    assertEquals(Number.Two.toString(), "2");
    assertEquals(Number.Eleven.toString(), "J");
    assertEquals(Number.Twelve.toString(), "Q");
    assertEquals(Number.Thirteen.toString(), "K");
  }
}
