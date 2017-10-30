package cs3500.hw02;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

/**
 * Tests for methods in the FreecellModel class.
 * The method getGameState() was used to test most of these methods to see their results printed
 * out because some of these methods are void.
 */
public class FreecellModelTests {
  FreecellModel model = new FreecellModel();
  FreecellModel model2 = new FreecellModel();
  List<Card> deck = model.getDeck();
  List<Card> deck2 = model.getDeck();

  /**
   * Tests the method startGame without shuffling the deck by printing out the game state.
   */
  @Test
  public void teststartGame() {
    model.startGame(deck, 4, 4, false);
    assertEquals("F1:\n" +
                    "F2:\n"
                    +
                    "F3:\n"
                    +
                    "F4:\n"
                    +
                    "O1:\n"
                    +
                    "O2:\n"
                    +
                    "O3:\n"
                    +
                    "O4:\n"
                    +
                    "C1: A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\n"
                    +
                    "C2: 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\n"
                    +
                    "C3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣\n"
                    +
                    "C4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣",
            model.getGameState());
  }

  /**
   * Tests that the method startGame throws an IllegalArgumentException when given too few cascade
   * piles to create.
   */
  @Test(expected = IllegalArgumentException.class)
  public void teststartGameInvalidCascadePile() {
    model.startGame(deck, 2, 8, false);
  }

  /**
   * Tests that the method startGame throws an IllegalArgumentException when given too few open
   * piles to create.
   */
  @Test(expected = IllegalArgumentException.class)
  public void teststartGameInvalidOpenPiles() {
    model.startGame(deck, 4, 0, false);
  }

  /**
   * Tests the method isGameOver when the game is not over.
   */
  @Test
  public void testisGameOver() {
    assertEquals(false, model.isGameOver());
  }

  /**
   * Tests the method getGameState.
   */
  @Test
  public void getGameState() {
    model.startGame(model.getDeck(), 7, 2, false);
    assertEquals("F1:\n"
            +
            "F2:\n"
            +
            "F3:\n"
            +
            "F4:\n"
            +
            "O1:\n"
            +
            "O2:\n"
            +
            "C1: A♦, 8♦, 2♥, 9♥, 3♠, 10♠, 4♣, J♣\n"
            +
            "C2: 2♦, 9♦, 3♥, 10♥, 4♠, J♠, 5♣, Q♣\n"
            +
            "C3: 3♦, 10♦, 4♥, J♥, 5♠, Q♠, 6♣, K♣\n"
            +
            "C4: 4♦, J♦, 5♥, Q♥, 6♠, K♠, 7♣\n"
            +
            "C5: 5♦, Q♦, 6♥, K♥, 7♠, A♣, 8♣\n"
            +
            "C6: 6♦, K♦, 7♥, A♠, 8♠, 2♣, 9♣\n"
            +
            "C7: 7♦, A♥, 8♥, 2♠, 9♠, 3♣, 10♣", model.getGameState());
  }

  /**
   * Tests the method getDeck().
   */
  @Test
  public void testGetDeck() {
    assertEquals( "[A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♥, 2♥, 3♥, 4♥, "
            +
            "5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, "
            +
            "K♠, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣]",
            model.getDeck().toString());
  }

  /**
   * Tests the method move when moving a card from a cascade pile to a foundation pile.
   */
  @Test
  public void testMoveToFoundationPile() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 0);
    assertEquals("F1: A♣\n"
                    +
                    "F2:\n"
                    +
                    "F3:\n"
                    +
                    "F4:\n"
                    +
                    "O1: K♣\n"
                    +
                    "O2: 9♣\n"
                    +
                    "O3: 5♣\n"
                    +
                    "O4:\n"
                    +
                    "C1: A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\n"
                    +
                    "C2: 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\n"
                    +
                    "C3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣\n"
                    +
                    "C4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠",
            model.getGameState());
  }

  /**
   * Tests the method move when moving a card from a cascade pile to an open pile.
   */
  @Test
  public void testMoveToOpenPile() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals("F1:\n" +
                    "F2:\n"
                    +
                    "F3:\n"
                    +
                    "F4:\n"
                    +
                    "O1: 10♣\n"
                    +
                    "O2:\n"
                    +
                    "O3:\n"
                    +
                    "O4:\n"
                    +
                    "C1: A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣\n"
                    +
                    "C2: 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\n"
                    +
                    "C3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣\n"
                    +
                    "C4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣",
            model.getGameState());
  }

  /**
   * Tests the method move when trying to move a card to the same place.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveSamePlace() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 0);
  }

  /**
   * Tests that the method move throws an IllegalArgumentException when you cannot move to a
   * cascade with an invalid move after another move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToCascadePileAfterMove() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.CASCADE, 1);
  }

  /**
   * Tests that the method move throws an IllegalArgumentException with an invalid move to a
   * cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToCascade() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 0);
  }

  /**
   * Tests that the method move throws an IllegalArgumentException with an invalid move to a
   * foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToFoundation() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 3);
  }

  /**
   * Tests the method canBeAdded when adding a card to a foundation pile.
   */
  @Test
  public void testCanBeAddedFoundation() {
    Card card1 = new Card(Suit.CLUB, CardValue.ACE);
    Card card2 = new Card(Suit.CLUB, CardValue.TWO);
    assertEquals("foundation", card1.canBeAdded(card2));
  }

  /**
   * Tests the method canBeAdded when adding a card to a cascade pile.
   */
  @Test
  public void testCanBeAddedCascade() {
    Card card1 = new Card(Suit.CLUB, CardValue.FIVE);
    Card card2 = new Card(Suit.CLUB, CardValue.FOUR);
    assertEquals("cascade", card1.canBeAdded(card2));
  }

  /**
   * Tests the method, canBeAdded, when it returns an empty string.
   */
  @Test
  public void testCanBeAdded() {
    Card card1 = new Card(Suit.CLUB, CardValue.ACE);
    Card card2 = new Card(Suit.CLUB, CardValue.FIVE);
    assertEquals("", card1.canBeAdded(card2));
  }

  /**
   * Tests the method getNumberValue().
   */
  @Test
  public void testGetNumberValue() {
    Card card1 = new Card(Suit.CLUB, CardValue.ACE);
    assertEquals(1, card1.getNumberValue());
  }

  /**
   * Tests the method getNumberValue().
   */
  @Test
  public void testGetNumberValue2() {
    Card card1 = new Card(Suit.CLUB, CardValue.THREE);
    assertEquals(3, card1.getNumberValue());
  }

  /**
   * Tests the method toString().
   */
  @Test
  public void testToString() {
    Card card1 = new Card(Suit.CLUB, CardValue.ACE);
    assertEquals("A♣", card1.toString());
  }

  /**
   * Tests if the deck is shuffled.
   */
  @Test
  public void testShuffle() {
    model.startGame(deck, 4, 4, true);
    model2.startGame(deck2, 4, 4, false);
    assertNotEquals(model.getGameState(), model2.getGameState());
  }

  @Test
  public void test() {
    model.startGame(deck, 4, 4, true);
    model2.startGame(deck2, 4, 4, false);
    assertNotEquals(model.getGameState(), model2.getGameState());
  }
}
