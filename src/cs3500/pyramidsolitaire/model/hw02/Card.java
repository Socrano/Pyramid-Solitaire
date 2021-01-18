package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Present a card contains a suit and a value.
 */
public class Card {
  /**
   * Represents the basic attributes of Card.
   */
  private final Number num;
  private final Suit suit;

  /**
   * Constructs an card.
   *
   * @param num   the number of the card.
   * @param suit  the suit of the card.
   */
  public Card(Number num, Suit suit) {
    this.num = num;
    this.suit = suit;
  }

  /**
   * Get the number of the card.
   * @return the number of visible cards.
   */
  public int getCardNumber() {
    return this.num.getNumber();
  }

  @Override
  public String toString() {
    return this.num.toString() + this.suit.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (! (that instanceof Card)) {
      return false;
    }

    return this.num == ((Card) that).num
        && this.suit == ((Card) that).suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.num, this.suit);
  }

  /**
   * A enum to present suit.
   */
  public enum Suit {
    Club("♣"),
    Diamond("♦"),
    Heart("♥"),
    Spade("♠");

    private final String value;
    Suit(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * A enum to present card number.
   */
  public enum Number {
    One("A"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Ten("10"),
    Eleven("J"),
    Twelve("Q"),
    Thirteen("K");

    private final String value;
    Number(String value) {
      this.value = value;
    }

    /**
     * To make the Number present as a int.
     *
     * @return A int present the number
     */
    public int getNumber() {
      switch (this.value) {
        case "A": return 1;
        case "J": return 11;
        case "Q": return 12;
        case "K": return 13;
        default: return Integer.parseInt(this.value);
      }
    }

    @Override
    public String toString() {
      return this.value;
    }
  }
}
