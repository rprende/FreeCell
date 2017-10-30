package cs3500.hw02;

import static java.lang.Integer.valueOf;

/**
 * Represents a playing card.
 */
public class Card {
  public final Suit suit;
  public final CardValue value;

  /**
   * Constructs a Card in terms of its suit and value.
   * @param suit  suit (Diamond, Heart, Club, or Spade) of the card
   * @param value value (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, K, Q) of the card
   */
  public Card(Suit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * Returns a String of the Card's value and suit.
   * @return the String concatentation of the Card's value and suit
   */
  public String toString() {
    return this.value.v + this.suit.symbol;
  }

  /**
   * Returns an int representing the value of the Card so that
   * the values can be compared easily.
   * Ace becomes 1, Jack becomes 11, Queen becomes 12, and King becomes 13.
   * @return the int representation of the Card's value
   */
  public int getNumberValue() {
    int newValue = 0;
    if (this.value.v.equals("A")) {
      newValue = 1;
    } else if (this.value.v.equals("J")) {
      newValue = 11;
    } else if (this.value.v.equals("Q")) {
      newValue = 12;
    } else if (this.value.v.equals("K")) {
      newValue = 13;
    } else {
      newValue = valueOf(this.value.v);
    }
    return newValue;
  }

  /**
   * Returns a string of the name of the Pile that the Card can be added to.
   * Returns "foundation" if the Card to be added (represented by newCard) is one more than
   * the value of this Card.
   * Returns "cascade" if the Card to be added is one less than the value of this Card.
   * If the Card cannot be added to the Pile, it returns an empty string.
   * @param newCard Card to be added to a Pile
   * @return a String representing the type of Pile the given Card (newCard) can be added to
   */
  public String canBeAdded(Card newCard) {
    if ((newCard.getNumberValue() - this.getNumberValue()) == 1) {
      return "foundation";
    }
    else if ((this.getNumberValue() - newCard.getNumberValue()) == 1) {
      return "cascade";
    }
    else {
      return "";
    }
  }

  /**
   * Determines if two cards are of alternating colors, depending on their suits.
   * @param newCard one of the cards to compare
   * @return if the two cards have alternating suits/colors
   */
  public boolean alternatingSuit(Card newCard) {
    return (((this.suit.equals(Suit.DIAMOND) || this.suit.equals(Suit.HEART))
            &&
            (newCard.suit.equals(Suit.CLUB) || newCard.suit.equals(Suit.SPADE)))
            ||
            ((this.suit.equals(Suit.CLUB) || this.suit.equals(Suit.SPADE))
                    &&
                    (newCard.suit.equals(Suit.HEART) || newCard.suit.equals(Suit.DIAMOND))));
  }

  /**
   * Determines if a given card's value is one less than a card's value.
   * @param newCard one card to be compared
   * @return if the two card's values have a difference of one
   */
  public boolean isBelow(Card newCard) {
    return ((this.getNumberValue() - newCard.getNumberValue()) == 1);
  }

  /**
   * Determines if a given card's value is one more than a card's value.
   * @param newCard one card to be compared
   * @return if the two card's values have a difference of one
   */
  public boolean isAbove(Card newCard) {
    return (newCard.getNumberValue() - this.getNumberValue() == 1);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Card)) {
      return false;
    }
    Card c = (Card)o;
    return (this.suit == c.suit && this.value == c.value);
  }

  @Override
  public int hashCode() {
    if (this.suit == Suit.CLUB) {
      return 1 + this.getNumberValue();
    }
    else if (this.suit == Suit.HEART) {
      return 25 + this.getNumberValue();
    }
    else if (this.suit == Suit.DIAMOND) {
      return 100 + this.getNumberValue();
    }
    else {
      return 350 + this.getNumberValue();
    }
  }
}

