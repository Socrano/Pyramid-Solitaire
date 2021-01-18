package cs3500.pyramidsolitaire.model.hw04;

/**
 * A class used to present position information for the card in the pyramid.
 */
public class Posn {
  int row;
  int card;

  /**
   * Constructs a Posn which contains an int present row and an int present card.
   */
  public Posn(int row, int card) {
    this.row = row;
    this.card = card;
  }

  /**
   * Get the row of the Posn.
   *
   * @return int The row of the Posn.
   */
  public int getRow() {
    return row;
  }

  /**
   * Get the dard of the Posn.
   *
   * @return int The card of the Posn.
   */
  public int getCard() {
    return card;
  }
}
