package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Provide an abstract class for BASIC/RELAXED/MULTIPYRAMID to decrease duplication.
 */
public class AbstractPyramidSolitaireModel implements PyramidSolitaireModel<Card> {

  /**
   * Represents the basic attributes of BasicPyramidSolitaire.
   */
  protected ArrayList<Card[]> pyramid;
  protected boolean startOrNot = false;
  protected ArrayList<Card> draw;
  protected int drawNum;
  protected int rowNum;
  protected static final ArrayList<Card.Suit> SUIT_LIST =
      new ArrayList<Card.Suit>(Arrays.asList(Card.Suit.Club, Card.Suit.Diamond,
          Card.Suit.Heart, Card.Suit.Spade));
  protected static final ArrayList<Card.Number> NUMBER_LIST =
      new ArrayList<Card.Number>(Arrays.asList(Card.Number.One, Card.Number.Two,
          Card.Number.Three, Card.Number.Four, Card.Number.Five, Card.Number.Six,
          Card.Number.Seven, Card.Number.Eight, Card.Number.Nine, Card.Number.Ten,
          Card.Number.Eleven, Card.Number.Twelve, Card.Number.Thirteen));

  /**
   * Constructs a BasicPyramidSolitaire.
   */
  public AbstractPyramidSolitaireModel() {
    this.pyramid = new ArrayList<Card[]>();
    this.startOrNot = false;
    this.draw = new ArrayList<Card>();
    this.drawNum = 0;
    this.rowNum = 0;
  }

  /**
   * Decide if the game is started, if not throw an exception.
   *
   * @throws IllegalStateException if game is not begin yet
   */
  protected void gameStartOrNot() {
    if (!this.startOrNot) {
      throw new IllegalStateException("The game hasn't been started yet");
    }
  }

  /**
   * Return the accumulated result of the given number.
   *
   * @param number   the  max number for the accumulated.
   * @return the accumulated result.
   */
  protected int accumulate(int number) {
    int result = 0;
    if (number < 0)  {
      result =  -1;
    } else {
      for (int i = 0; i <= number; i++) {
        result = result + i;
      }
    }
    return result;
  }

  /**
   * Return if the given list contains all cards.
   *
   * @param givenDeck  a list need to be checked if it contains all card.
   * @return boolean   if the given list contains all cards.
   */
  protected boolean checkCards(List givenDeck) {
    List completeList = this.getDeck();
    boolean resultCheck = true;
    for (int i = 0; i < completeList.size(); i++) {
      resultCheck = resultCheck && givenDeck.contains(completeList.get(i));
    }
    return resultCheck;
  }

  /**
   * Set the deck to the structure Pyramid.
   *
   * @param deck    The list needed to be set.
   * @param row    The row of pyramid.
   */
  protected void setDeckToPyramid(List deck, int row) {
    ArrayList<Card[]> result = new ArrayList<Card[]>();
    for (int i = 0; i <= row; i++) {
      result.add(new Card[i + 1]);
    }

    for (int i = 0; i < this.accumulate(row + 1); i++) {
      int rowNum = this.getRowNumber(i, row);

      Array.set(result.get(rowNum), i - this.accumulate(rowNum), deck.get(i));
    }

    this.pyramid = result;
  }

  /**
   * Set the deck to the Draw.
   *
   * @param deck    The list needed to be set.
   * @param row    The row of pyramid.
   */
  protected void getDrawList(List deck, int row) {
    ArrayList<Card> result = new ArrayList<Card>();
    for (int i = this.accumulate(row + 1); i < deck.size(); i++) {
      result.add((Card) deck.get(i));
    }
    this.draw = result;
  }

  /**
   * Get an index and find which row should it stay.
   *
   * @param index  The index of the element should be dealt with.
   * @param maxRowNumber The total number of rows.
   * @return The row the element with given index should be placed.
   * @throws IllegalArgumentException If index or maxRowNumber is negative.
   */
  protected int getRowNumber(int index, int maxRowNumber) {
    if (index < 0 || maxRowNumber < 0) {
      throw new IllegalArgumentException("Index or maxRowNumber cannot be negative!!");
    }
    int result = 0;

    for (int i = 1; i <= maxRowNumber + 1; i++) {
      if (this.accumulate(i) >= index + 1
          && this.accumulate(i - 1) < index + 1) {
        result = i - 1;
        break;
      }
    }
    return result;
  }

  /**
   * Check is a card is exposed.
   *
   * @param row  The row Number of the card.
   * @param card The card number of the card.
   * @return A boolean to check is this card is exposed.
   */
  protected boolean checkExposed(int row, int card) {
    if (row == this.rowNum - 1) {
      return true;
    } else {
      return ((this.pyramid.get(row + 1)[card + 1] == null)
          && (this.pyramid.get(row + 1)[card] == null));
    }
  }

  /**
   * Find all cards are exposed.
   *
   * @return A list contains all cards are exposed.
   */
  protected ArrayList<Card> findExposed() {
    ArrayList<Card> exposedCard = new ArrayList<Card>();

    for (int arrayInd = 0; arrayInd <= this.rowNum - 1; arrayInd++) {
      for (int cardInd = 0; cardInd < this.pyramid.get(arrayInd).length; cardInd++) {
        if (this.checkExposed(arrayInd, cardInd)) {
          if (this.pyramid.get(arrayInd)[cardInd] != null) {
            exposedCard.add(this.pyramid.get(arrayInd)[cardInd]);
          }
        }
      }
    }


    return exposedCard;
  }

  /**
   * Get visual draw List.
   *
   * @return A list contains all visual draw cards.
   */
  protected ArrayList<Card> getVisualDrawList() {
    ArrayList<Card> result = new ArrayList<Card>();
    if (drawNum <= this.draw.size()) {
      for (int i = 0; i < drawNum; i++) {
        result.add(this.draw.get(i));
      }
    } else {
      for (Card c : this.draw) {
        result.add(c);
      }
    }

    return result;
  }

  /**
   * Find if there is any remove.
   *
   * @param drawList  The draw list to see if can do remove with the draw.
   * @param exposedList The exposed card list to see if there is any removes.
   * @return The boolean if there is any remove.
   */
  protected boolean findRemove(ArrayList<Card> drawList, ArrayList<Card> exposedList) {
    boolean result = false;

    for (Card c : exposedList) {
      result = result || (c.getCardNumber() == 13);
    }

    for (Card c1 : exposedList) {
      for (Card c2 : exposedList) {
        result = result || ((c1.getCardNumber() + c2.getCardNumber()) == 13);
      }
    }


    for (Card c1 : exposedList) {
      for (Card c2 : drawList) {
        result = result || ((c1.getCardNumber() + c2.getCardNumber()) == 13);
      }
    }


    return result;
  }


  @Override
  public List<Card> getDeck() {
    List deckList = new ArrayList<Card>();
    for (Card.Suit s : this.SUIT_LIST) {
      for (Card.Number n : this.NUMBER_LIST) {
        Card newCard = new Card(n, s);
        deckList.add(newCard);
      }
    }

    return deckList;
  }


  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    if (deck == null
        || (deck.size() != 52)
        || !(this.checkCards(deck))
        || (numRows <= 0)
        || (numDraw < 0)
        || ((this.accumulate(numRows) + numDraw) > deck.size())) {
      throw new IllegalArgumentException("Cannot start with wrong status");
    }


    List shuffledDeck = new ArrayList();
    for (Object c : deck) {
      shuffledDeck.add(c);
    }


    if (shuffle) {
      Collections.shuffle(shuffledDeck);
    }

    if (!this.startOrNot) {
      this.setDeckToPyramid(shuffledDeck, numRows - 1);
      this.getDrawList(shuffledDeck, numRows - 1);
      this.drawNum = numDraw;
      this.rowNum = numRows;
      this.startOrNot = true;
    }
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    gameStartOrNot();

    if ((row1 > this.rowNum - 1)
        || (row2 > this.rowNum - 1)
        || (this.pyramid.get(row1).length < card1)
        || (this.pyramid.get(row2).length < card2)
        || (row1 < 0)
        || (row2 < 0)
        || (card1 < 0)
        || (card2 < 0)
        || !this.checkExposed(row1, card1)
        || !this.checkExposed(row2, card2)
        || (this.getCardAt(row1, card1) == null)
        || (this.getCardAt(row2, card2) == null)
        || (((Card) this.getCardAt(row1, card1)).getCardNumber()
        + ((Card) this.getCardAt(row2, card2)).getCardNumber() != 13)) {
      throw new IllegalArgumentException("The attempted remove is invalid");
    }

    this.pyramid.get(row1)[card1] = null;
    this.pyramid.get(row2)[card2] = null;
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
    gameStartOrNot();

    if (!this.checkExposed(row, card)
        || (row > this.rowNum - 1)
        || (card > this.pyramid.get(row).length)
        || (card < 0)
        || (row < 0)
        || (this.getCardAt(row, card) == null)
        || (((Card) this.getCardAt(row, card)).getCardNumber() != 13)) {
      throw new IllegalArgumentException("The attempted remove is invalid");
    }

    this.pyramid.get(row)[card] = null;
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    gameStartOrNot();

    if (!this.checkExposed(row, card)
        || (row > this.rowNum - 1)
        || (card > this.pyramid.get(row).length)
        || (card < 0)
        || (row < 0)
        || (this.getCardAt(row, card) == null)
        || (drawIndex < 0)
        || (drawIndex >= this.getVisualDrawList().size())
        || (((Card) this.getCardAt(row, card)).getCardNumber()
        + this.getVisualDrawList().get(drawIndex).getCardNumber() != 13)) {
      throw new IllegalArgumentException("The attempted remove is invalid");
    }

    this.pyramid.get(row)[card] = null;
    this.draw.remove(drawIndex);
  }


  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    gameStartOrNot();

    if (drawIndex < 0
        || drawIndex >= this.getVisualDrawList().size()) {
      throw new IllegalArgumentException("The index is invalid or no card is present there.");
    }

    this.draw.remove(drawIndex);
  }


  @Override
  public int getNumRows() {
    if (!this.startOrNot) {
      return -1;
    }

    return this.rowNum;
  }

  @Override
  public int getNumDraw() {
    if (!this.startOrNot) {
      return -1;
    }

    return this.getVisualDrawList().size();
  }


  @Override
  public int getRowWidth(int row) {
    gameStartOrNot();

    if (row > this.rowNum - 1 || row < 0) {
      throw new IllegalArgumentException("The row is invalid");
    }

    return row + 1;
  }


  @Override
  public boolean isGameOver() throws IllegalStateException {
    gameStartOrNot();

    return (!findRemove(this.draw, this.findExposed())
        && this.draw.size() <= this.drawNum)
        || this.getScore() == 0;
  }

  @Override
  public int getScore() throws IllegalStateException {
    gameStartOrNot();

    int score = 0;
    for (Card[] cardArray : this.pyramid) {
      for (Card c : cardArray) {
        if (c == null) {
          score = score + 0;
        } else {
          score = score + c.getCardNumber();
        }
      }
    }
    return score;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    gameStartOrNot();

    if (card > this.pyramid.get(row).length
        || row > this.rowNum
        || row < 0
        || card < 0) {
      throw new IllegalArgumentException("The coordinates are invalid");
    }

    return this.pyramid.get(row)[card];
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    gameStartOrNot();

    ArrayList<Card> result = new ArrayList<Card>();
    if (this.drawNum < this.draw.size() - 1) {
      for (int i = 0; i < this.drawNum; i++) {
        result.add(this.draw.get(i));
      }
    } else {
      result = this.draw;
    }

    return result;
  }
}
