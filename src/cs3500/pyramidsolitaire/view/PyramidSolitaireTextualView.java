package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the PyramidSolitaireTextualView to given textual view of a
 * pyramid.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  public final Appendable state;

  /**
   * Constructs a PyramidSolitaireTextualView.
   *
   * @param model   the model of the PyramidSolitaireTextualView.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
    this.state = null;
  }

  /**
   * Constructs a PyramidSolitaireTextualView.
   *
   * @param model   the model of the PyramidSolitaireTextualView.
   * @param state   the appendable to transmit state of model.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable state) {
    this.model = model;
    this.state = state;
    Objects.requireNonNull(this.state);
    try {
      render();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Trans a card or a null to string.
   *
   * @param row  The number of row the card at.
   * @param card The number of card the car at.
   * @return   A string present the card or the null.
   */
  private String presentCard(int row, int card) {
    if (this.model.getCardAt(row, card) == null) {
      return ".  ";
    } else {
      String result = this.model.getCardAt(row, card).toString();
      if (this.model.getCardAt(row, card).toString().length() == 2) {
        result = result + " ";
      }

      return result;
    }
  }

  /**
   * Given a textual view of a pyramid.
   *
   * @return   A text to present pyramid.
   */
  @Override
  public String toString() {
    if (this.model.getNumRows() == -1) {
      return "";
    }

    boolean empty = true;
    for (int row = 0; row <= this.model.getNumRows() - 1; row++) {
      for (int card = 0; card <= row; card++) {
        empty = empty && this.model.getCardAt(row, card) == null;
      }
    }

    if (empty) {
      return "You win!";
    }

    if (this.model.isGameOver()) {
      return "Game over. Score: " + this.model.getScore();
    }

    String resultPresent = "";

    for (int row = 0; row <= this.model.getNumRows() - 1; row++) {
      int card = 0;
      resultPresent = resultPresent + "  ".repeat(this.model.getNumRows() - 1 - row);
      while (card >= 0) {
        try {
          resultPresent = resultPresent + this.presentCard(row, card) + " ";
          card++;
        } catch (Exception e) {
          break;
        }
      }
      resultPresent = resultPresent.stripTrailing() + "\n";
    }

    resultPresent = resultPresent + "Draw:";

    for (int i = 0; i < this.model.getDrawCards().size(); i++) {
      if (i == 0) {
        resultPresent = resultPresent + " " + this.model.getDrawCards().get(i).toString();
      } else {
        resultPresent = resultPresent + ", " + this.model.getDrawCards().get(i).toString();
      }
    }

    return resultPresent;
  }

  /**
   * To get the textual view of the model without considering if it is over.
   *
   * @return A string show the textual view of the model.
   */
  public String finalState() {
    if (this.model.getNumRows() == -1) {
      return "";
    }

    String resultPresent = "";

    for (int row = 0; row <= this.model.getNumRows() - 1; row++) {
      resultPresent = resultPresent + "  ".repeat(this.model.getNumRows() - 1 - row);
      for (int card = 0; card <= row; card++) {
        resultPresent = resultPresent + this.presentCard(row, card) + " ";
      }
      resultPresent = resultPresent.stripTrailing() + "\n";
    }

    resultPresent = resultPresent + "Draw:";

    for (int i = 0; i < this.model.getDrawCards().size(); i++) {
      if (i == 0) {
        resultPresent = resultPresent + " " + this.model.getDrawCards().get(i).toString();
      } else {
        resultPresent = resultPresent + ", " + this.model.getDrawCards().get(i).toString();
      }
    }

    return resultPresent;
  }

  @Override
  public void render() throws IOException {
    this.state.append(this.toString() + "\n");
  }
}
