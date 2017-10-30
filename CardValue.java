package cs3500.hw02;

/**
 * Enumeration of possible card values.
 */
public enum CardValue {
  ACE("A"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("10"),
  JACK("J"),
  QUEEN("Q"),
  KING("K");

  public String v;

  /**
   * Constructs a Card Value in terms of the String representing the value.
   * @param v the value of the Card
   */
  CardValue(String v) {
    this.v = v;
  }
}
