package cs3500.pyramidsolitaire.model.hw04;


import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.ArrayList;

/**
 * Present a relaxed pyramid model and specific method to run it.
 */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaireModel {
  /**
   * Constructs a RelaxedPyramidSolitaire.
   */
  public RelaxedPyramidSolitaire() {
    new AbstractPyramidSolitaireModel();
  }


  /**
   * To check if two card meet the requirement of relaxed remove.
   *
   * @param row1  The row number of first card.
   * @param card1  The card number of first card.
   * @param row2  The row number of second card.
   * @param card2  The card number of second card.
   * @return  boolean  If two card fit the requirement.
   */
  protected boolean checkExposedSpecial(int row1, int card1, int row2, int card2) {
    Card backups1 = this.pyramid.get(row1)[card1];
    Card backups2 = this.pyramid.get(row2)[card2];

    this.pyramid.get(row1)[card1] = null;
    boolean result1 = this.checkExposed(row2, card2);
    this.pyramid.get(row1)[card1] = backups1;

    this.pyramid.get(row2)[card2] = null;
    boolean result2 = this.checkExposed(row1, card1);
    this.pyramid.get(row2)[card2] = backups2;

    return result1 || result2;
  }

  /**
   * Check there is possible relaxed remove card in pyramid.
   *
   * @param exposedList  A list of exposed card.
   * @return boolean  If there is possible relaxed remove card.
   */
  protected boolean checkRelaxed(ArrayList<Card> exposedList) {
    boolean result = false;
    for (Card c : exposedList) {
      for (int row = 1; row < this.rowNum; row++) {
        for (int card = 0; card <= row; card++) {
          if (this.getCardAt(row, card) != null) {
            if (this.getCardAt(row, card).equals(c)) {
              if (card == 0) {
                result = result || (checkExposedSpecial(row, card, row - 1, card)
                    && (this.getCardAt(row, card).getCardNumber()
                    + this.getCardAt(row - 1, card).getCardNumber() == 13));
              } else if (card == row) {
                result = result || (checkExposedSpecial(row, card, row - 1, card - 1)
                    && (this.getCardAt(row, card).getCardNumber()
                    + this.getCardAt(row - 1, card - 1).getCardNumber() == 13));
              } else {
                result = result
                    || (checkExposedSpecial(row, card, row - 1, card - 1)
                    && (this.getCardAt(row, card).getCardNumber()
                    + this.getCardAt(row - 1, card - 1).getCardNumber() == 13))
                    || (checkExposedSpecial(row, card, row - 1, card)
                    && (this.getCardAt(row, card).getCardNumber()
                    + this.getCardAt(row - 1, card).getCardNumber() == 13));
              }
            }
          }
        }
      }
    }
    return result;
  }

  @Override
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

    result = result || checkRelaxed(exposedList);
    
    return result;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    gameStartOrNot();

    if ((row1 > this.rowNum - 1)
        || (row2 > this.rowNum - 1)
        || (row1 < card1)
        || (row2 < card2)
        || (row1 < 0)
        || (row2 < 0)
        || (card1 < 0)
        || (card2 < 0)
        || !this.checkExposedSpecial(row1, card1, row2, card2)
        || (this.getCardAt(row1, card1) == null)
        || (this.getCardAt(row2, card2) == null)
        || (((Card) this.getCardAt(row1, card1)).getCardNumber()
        + ((Card) this.getCardAt(row2, card2)).getCardNumber() != 13)) {
      throw new IllegalArgumentException("The attempted remove is invalid");
    }

    this.pyramid.get(row1)[card1] = null;
    this.pyramid.get(row2)[card2] = null;
  }
}
