package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents a Pile of Cards.
 */
public class Pile {
  public PileType type;
  public ArrayList<Card> cards;

  /**
   * Constructs a Pile in terms of what type of Pile it is and what cards it contains.
   * @param type type of the Pile (open, cascade, or foundation
   * @param cards list of Cards in the Pile
   */
  public Pile(PileType type, ArrayList<Card> cards) {
    this.type = type;
    this.cards = cards;
  }


}
