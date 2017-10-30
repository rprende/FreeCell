package cs3500.hw02;

/**
 * Represents possible Suits that a Card can be.
 */
public enum Suit {
  DIAMOND("♦"),
  HEART("♥"),
  SPADE("♠"),
  CLUB("♣");

  public String symbol;

  /**
   * Constructs a Suit.
   * @param symbol the symbol of the Suit (spade, heart, club, or diamond)
   */
  Suit(String symbol) {
    this.symbol = symbol;
  }
}
