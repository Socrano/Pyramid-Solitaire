import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Number;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/** Tests for {@link BasicPyramidSolitaire}s. */
public class BasicPyramidSolitaireTest {
  BasicPyramidSolitaire testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
  BasicPyramidSolitaire testBasicPyramidSolitaire2 = new BasicPyramidSolitaire();
  List deck = testBasicPyramidSolitaire1.getDeck();


  // set up the initial condition
  protected void initTestConditions() {
    this.testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    this.testBasicPyramidSolitaire2 = new BasicPyramidSolitaire();
    this.deck = testBasicPyramidSolitaire1.getDeck();
    this.testBasicPyramidSolitaire1.startGame(deck,
        false, 7, 1);
  }

  @Test
  public void getDeckTest() {
    initTestConditions();



    assertEquals(deck.size(), 52);
    boolean duplicate = false;
    for (Object c1 : deck) {
      int duplicateTimes = 0;
      for (Object c2 : deck) {
        if (c1.equals(c2)) {
          duplicateTimes = duplicateTimes + 1;
        }
      }
      duplicate = duplicate || (duplicateTimes != 1);
    }

    assertEquals(duplicate, false);
    //Because there only 52 different cards, and we find the length of List
    //is 52 and there is no duplicate cards. So
    //this card contains 52 different cards
  }

  //The role of startGame, especially make the state start
  // will be presented in the following test


  @Test(expected = IllegalArgumentException.class)
  public void startFameTestForIllegalArgumentException() {
    initTestConditions();
    testBasicPyramidSolitaire2.startGame(null,
        false, 7, 1);
    testBasicPyramidSolitaire2.startGame(new ArrayList<Card>(),
        false, 7, 1);
    Collections.fill(deck,"1");
    testBasicPyramidSolitaire2.startGame(deck,
        false, 7, 1);
    initTestConditions();
    Collections.fill(deck, new Card(Card.Number.One, Card.Suit.Club));
    testBasicPyramidSolitaire2.startGame(deck,
        false, 7, 1);
    initTestConditions();
    testBasicPyramidSolitaire2.startGame(deck,
        false, -1, 1);
    testBasicPyramidSolitaire2.startGame(deck,
        false, 7, -1);
    testBasicPyramidSolitaire2.startGame(deck,
        false, 17, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void totalRemoveTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.remove(1, 1);
    testBasicPyramidSolitaire2.remove(2, 2);
    testBasicPyramidSolitaire2.remove(1, 1, 2, 2);
    testBasicPyramidSolitaire2.remove(3, 3, 4, 4);
    testBasicPyramidSolitaire2.removeUsingDraw(1, 1,1);
    testBasicPyramidSolitaire2.removeUsingDraw(2, 2,2);
  }

  //test the IllegalStateException for discard, and same for the following
  //test accoridng to their name
  @Test(expected = IllegalStateException.class)
  public void discardDrawTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.discardDraw(1);
    testBasicPyramidSolitaire2.discardDraw(2);
  }

  @Test
  public void totalGetNumTestForNotStartGame() {
    initTestConditions();
    assertEquals(testBasicPyramidSolitaire2.getNumDraw(), -1);
    assertEquals(testBasicPyramidSolitaire2.getNumRows(), -1);
  }

  @Test(expected = IllegalStateException.class)
  public void getRowWidthTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.getRowWidth(1);
    testBasicPyramidSolitaire2.getRowWidth(2);
  }

  @Test(expected = IllegalStateException.class)
  public void isGameOverTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.isGameOver();
  }

  @Test(expected = IllegalStateException.class)
  public void getScoreTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.getScore();
  }

  @Test(expected = IllegalStateException.class)
  public void getCardTestForIllegalStateException() {
    initTestConditions();
    testBasicPyramidSolitaire2.getCardAt(1, 1);
    testBasicPyramidSolitaire2.getDrawCards();
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeTestForIllegalArgumentException() {
    initTestConditions();
    testBasicPyramidSolitaire1.remove(1, -1);
    testBasicPyramidSolitaire1.remove(-1, 1);
    testBasicPyramidSolitaire1.remove(-1, 1, 1, 1);
    testBasicPyramidSolitaire1.remove(1, -1, 1, 1);
    testBasicPyramidSolitaire1.remove(1, 1, -1, 1);
    testBasicPyramidSolitaire1.remove(1, 1, 1, -1);
    testBasicPyramidSolitaire1.remove(1, 11);
    testBasicPyramidSolitaire1.remove(11, 1);
    testBasicPyramidSolitaire1.remove(1, 1);
    testBasicPyramidSolitaire1.remove(11, 1, 1, 1);
    testBasicPyramidSolitaire1.remove(1, 1, 11, 1);
    testBasicPyramidSolitaire1.remove(1, 1, 1, 3);
    testBasicPyramidSolitaire1.remove(1, 3, 1, 1);
    testBasicPyramidSolitaire1.remove(1, 1, 6, 1);
    testBasicPyramidSolitaire1.remove(6, 1, 1, 1);
    testBasicPyramidSolitaire1.startGame(deck, false, 5, 3);
    testBasicPyramidSolitaire1.remove(4, 2);
    testBasicPyramidSolitaire1.remove(4, 2);
    testBasicPyramidSolitaire1.remove(4, 2, 1, 1);
    testBasicPyramidSolitaire1.remove(1, 1, 4, 2);
    testBasicPyramidSolitaire1.remove(1, 1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawTestForIllegalArgumentException() {
    initTestConditions();
    testBasicPyramidSolitaire1.removeUsingDraw(1, 1, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 11, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 1, 11);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 1, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(-1, 1, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, -1, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 1, -1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 1, 11);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 11, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(11, 1, 1);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 6, 1);
    testBasicPyramidSolitaire1.startGame(deck, false, 5, 3);
    testBasicPyramidSolitaire1.remove(4,2);
    testBasicPyramidSolitaire1.removeUsingDraw(1, 4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawTestForIllegalArgumentException() {
    initTestConditions();
    testBasicPyramidSolitaire1.discardDraw(-1);
    testBasicPyramidSolitaire1.discardDraw(11);
  }

  @Test
  public void getNumDrawTest() {
    initTestConditions();
    assertEquals(this.testBasicPyramidSolitaire1.getNumDraw(), 1);
    assertEquals(this.testBasicPyramidSolitaire2.getNumDraw(), -1);
  }

  @Test
  public void getNumRowTest() {
    initTestConditions();
    assertEquals(this.testBasicPyramidSolitaire1.getNumRows(), 7);
    assertEquals(this.testBasicPyramidSolitaire2.getNumRows(), -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthTestForIllegalArgumentException() {
    initTestConditions();
    testBasicPyramidSolitaire1.getRowWidth(-1);
    testBasicPyramidSolitaire1.getRowWidth(11);
  }

  @Test
  public void isGameOverTest() {
    initTestConditions();
    assertEquals(this.testBasicPyramidSolitaire1.isGameOver(), false);
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 11);
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck, false, 1, 1);
    for (int i = 0; i < 51; i++) {
      testBasicPyramidSolitaire1.discardDraw(0);
    }
    assertEquals(this.testBasicPyramidSolitaire1.isGameOver(),true);
  }

  @Test
  public void getScoreTest() {
    initTestConditions();
    assertEquals(this.testBasicPyramidSolitaire1.getScore(), 185);
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 11);
    assertEquals(this.testBasicPyramidSolitaire1.getScore(), 1);
    testBasicPyramidSolitaire1.removeUsingDraw(10, 0, 0);
    assertEquals(this.testBasicPyramidSolitaire1.getScore(), 0);
  }

  @Test
  public void getCardAtTest() {
    initTestConditions();
    assertEquals(this.testBasicPyramidSolitaire1.getCardAt(0, 0),
        new Card(Number.One, Card.Suit.Club));
    assertEquals(this.testBasicPyramidSolitaire1.getCardAt(1, 0),
        new Card(Number.Two, Card.Suit.Club));
  }

  @Test
  public void getDrawCardTest() {
    initTestConditions();
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 1);
    assertEquals(testBasicPyramidSolitaire1.getDrawCards(),
        new ArrayList<Card>(Arrays.asList(new Card(Number.Two, Card.Suit.Club))));
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 2);
    assertEquals(testBasicPyramidSolitaire1.getDrawCards(),
        new ArrayList<Card>(Arrays.asList(new Card(Number.Two, Card.Suit.Club),
            new Card(Number.Three, Card.Suit.Club))));
  }

  @Test
  public void removeTest() {
    //the data of pyramid changes correctly, so remove work correctly
    initTestConditions();
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 5, 11);
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 2),
        new Card(Number.Thirteen, Suit.Club));
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 4),
        new Card(Number.Two, Suit.Diamond));
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 0),
        new Card(Number.Eleven, Suit.Club));
    testBasicPyramidSolitaire1.remove(4, 2);
    testBasicPyramidSolitaire1.remove(4, 4, 4, 0);
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 2),
        null);
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 4),
        null);
    assertEquals(testBasicPyramidSolitaire1.getCardAt(4, 0),
        null);
  }

  @Test
  public void removeUsingDrawTest() {
    //the data of pyramid changes correctly, so removeUsingDraw work correctly
    initTestConditions();
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 11);
    assertEquals(this.testBasicPyramidSolitaire1.getScore(), 1);
    testBasicPyramidSolitaire1.removeUsingDraw(10, 0, 0);
    assertEquals(this.testBasicPyramidSolitaire1.getScore(), 0);
  }

  @Test
  public void discardDrawTest() {
    //the data of draw list changes correctly, so discardDraw works correctly
    initTestConditions();
    testBasicPyramidSolitaire1 = new BasicPyramidSolitaire();
    testBasicPyramidSolitaire1.startGame(deck,  false, 1, 11);
    assertEquals(testBasicPyramidSolitaire1.getDrawCards().get(0),
        new Card(Number.Two, Card.Suit.Club));
    testBasicPyramidSolitaire1.discardDraw(0);
    assertEquals(testBasicPyramidSolitaire1.getDrawCards().get(0),
        new Card(Number.Three, Card.Suit.Club));
  }
}
