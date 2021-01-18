package cs3500.pyramidsolitaire.model.hw04;


import cs3500.pyramidsolitaire.model.hw02.Card;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Provide a model for MultiPyramid and build the pyramid specifically.
 */
public class MultiPyramidSolitaire extends AbstractPyramidSolitaireModel {
  int overrideRow;
  int notOverrideRow;
  int spaceOfFirstLine;

  /**
   * Constructs a MultiPyramidSolitaire.
   */
  public MultiPyramidSolitaire() {
    new AbstractPyramidSolitaireModel();
  }

  /**
   * To decide if the cord in the given posn in pyramid is null.
   *
   * @param l The list contains the posn that is null in pyramid.
   * @param p The posn show a card in pyramid.
   * @return boolean  If the card in the given posn is null.
   */
  protected boolean isNull(List l, Posn p) {
    boolean result = false;
    for (Object n : l) {
      result = result
          || (((Posn) n).getRow() == p.getRow() && ((Posn) n).getCard() == p.getCard());
    }
    return result;
  }

  @Override
  protected void setDeckToPyramid(List deck, int row) {
    int deckCount = 0;
    this.pyramid = new ArrayList<Card[]>();
    for (int i = 0; i <= row; i++) {
      pyramid.add(new Card[(this.notOverrideRow + 1) * 3 - 2 + i - this.notOverrideRow]);
    }

    for (int i = 0; i < this.accumulate(this.rowNum); i++) {
      int rowNum = this.getRowNumber(i, row);
      int card = i - this.accumulate(rowNum);
      Posn sample = new Posn(rowNum, card);
      List nullList = this.getNullList();


      if (isNull(nullList, sample)) {
        Array.set(pyramid.get(rowNum), card, null);
      } else {
        Array.set(pyramid.get(rowNum), card, deck.get(deckCount));
        deckCount++;
      }
    }
  }


  @Override
  protected int accumulate(int number) {
    int result = 0;
    if (number < 0) {
      result = -1;
    } else if (number == 0) {
      result = 0;
    } else {
      for (int i = 1; i <= number; i++) {
        result = result + (this.notOverrideRow + 1) * 3 - 2 + i - 1 - this.notOverrideRow;
      }
    }
    return  result;
  }

  @Override
  protected void getDrawList(List deck, int row) {
    ArrayList<Card> result = new ArrayList<Card>();
    for (int i = this.accumulate(row + 1) - this.getNullList().size();
        i < deck.size(); i++) {
      result.add((Card) deck.get(i));
    }
    this.draw = result;
  }

  /**
   * Get a list of Posn show the null in pyramid.
   *
   * @return List  A list of Posn show the null in pyramid.
   */
  protected List getNullList() {
    List nullList = new ArrayList<Posn>();
    if (this.rowNum > 3) {
      for (int row = 1; row <= this.notOverrideRow; row++) {
        for (int nullCard = this.spaceOfFirstLine + 1 - row; nullCard > 0; nullCard--) {
          nullList.add(new Posn(row - 1, row + nullCard - 1));
          nullList.add(new Posn(row - 1, row + this.spaceOfFirstLine + 1 - row
              + row + nullCard - 1));
        }
      }
    }
    return nullList;
  }

  @Override
  protected boolean checkCards(List givenDeck) {
    List completeList = this.getDeck();
    boolean resultCheck = true;
    for (int i = 0; i < completeList.size(); i++) {
      resultCheck = resultCheck
          && (Collections.frequency(givenDeck, completeList.get(i)) == 2);
    }
    return resultCheck;
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
        || (deck.size() != 104)
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


    this.drawNum = numDraw;
    this.rowNum = numRows;
    this.startOrNot = true;
    update();
    setDeckToPyramid(shuffledDeck, numRows - 1);
    getDrawList(shuffledDeck, numRows - 1);
  }

  /**
   * Update field overrideRow, notOverrideRow, spaceOfFirstLine in MultiPyramid.
   */
  protected void update() {
    this.overrideRow = (int)Math.ceil(this.rowNum / 2.0);
    this.notOverrideRow = this.rowNum - this.overrideRow;
    this.spaceOfFirstLine = this.rowNum - this.overrideRow - 1;
  }
}
