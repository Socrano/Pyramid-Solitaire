package cs3500.pyramidsolitaire.model.hw04;


import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * A creator to build specific model.
 */
public class PyramidSolitaireCreator {

  /**
   * A constructor to call a new PyramidSolitaireCreator.
   */
  public PyramidSolitaireCreator() {
    //It does not have any field
  }

  /**
   * A enum to present GameType.
   */
  public enum GameType {
    BASIC("basic"),
    RELAXED("relaxed"),
    MULTIPYRAMID("multipyramid");

    private final String value;
    GameType(String value) {
      this.value = value;
    }


    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Use the type to create a corresponding model.
   *
   * @param type  The type you want for the game.
   * @return PyramidSolitaireModel   The corresponding model according to the given type.
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    switch (type) {
      case BASIC: return new BasicPyramidSolitaire();
      case RELAXED: return new RelaxedPyramidSolitaire();
      case MULTIPYRAMID: return new MultiPyramidSolitaire();
      default: return null;
    }
  }
}
