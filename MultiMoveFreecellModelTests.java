package cs3500.hw02;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.MultiMoveFreecellModel;

/**
 * Tests for methods in the MultiMoveFreecellModel class.
 * The method getGameState() was used to test most of these methods to see their results printed
 * out because some of these methods are void.
 */
public class MultiMoveFreecellModelTests {
  MultiMoveFreecellModel model = new MultiMoveFreecellModel();

  /**
   * Tests the method create by printing it to a String.
   */
  @Test
  public void testCreateFreecellModel() {
    FreecellModelCreator creator = new FreecellModelCreator();
    assertEquals("FreecellModel",
            creator.toString(creator.create(FreecellModelCreator.GameType.SINGLEMOVE)));
  }

  /**
   * Tests the method create by printing it to a String.
   */
  @Test
  public void testCreateMultiMove() {
    FreecellModelCreator creator = new FreecellModelCreator();
    assertEquals("MultiMoveFreecellModel",
            creator.toString(creator.create(FreecellModelCreator.GameType.MULTIMOVE)));
  }

  /**
   * Tests the multiple card move functionality of the move method.
   */
  @Test
  public void testMultiMove() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.CASCADE, 37);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "C1: A♦\n" +
            "C2: 2♦\n" +
            "C3: 3♦\n" +
            "C4: 4♦\n" +
            "C5: 5♦\n" +
            "C6: 6♦\n" +
            "C7: 7♦\n" +
            "C8: 8♦\n" +
            "C9: 9♦\n" +
            "C10: 10♦\n" +
            "C11: J♦\n" +
            "C12: Q♦\n" +
            "C13:\n" +
            "C14: A♥\n" +
            "C15: 2♥\n" +
            "C16: 3♥\n" +
            "C17: 4♥\n" +
            "C18: 5♥\n" +
            "C19: 6♥\n" +
            "C20: 7♥\n" +
            "C21: 8♥\n" +
            "C22: 9♥\n" +
            "C23: 10♥\n" +
            "C24: J♥\n" +
            "C25: Q♥\n" +
            "C26: K♥\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: K♦, Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣", model.getGameState());
  }

  /**
   * Tests that the multimove functionality of the move function indicates when a move is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalid() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.CASCADE, 30);
    assertEquals("invalid move", model.getGameState());
  }

  /**
   * Tests that you cannot make a multimove to an open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveToOpen() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.OPEN, 3);
    assertEquals("invalid move", model.getGameState());
  }

  /**
   * Tests that you cannot make a multimove to a foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveToFoundation() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 3);
    assertEquals("invalid move", model.getGameState());
  }

  /**
   * Tests that you can make a single move to a foundation pile.
   */
  @Test
  public void testMultiMoveWithOneCardToFoundation() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    assertEquals("F1:\n" +
            "F2: A♦\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "C1:\n" +
            "C2: 2♦\n" +
            "C3: 3♦\n" +
            "C4: 4♦\n" +
            "C5: 5♦\n" +
            "C6: 6♦\n" +
            "C7: 7♦\n" +
            "C8: 8♦\n" +
            "C9: 9♦\n" +
            "C10: 10♦\n" +
            "C11: J♦\n" +
            "C12: Q♦\n" +
            "C13: K♦\n" +
            "C14: A♥\n" +
            "C15: 2♥\n" +
            "C16: 3♥\n" +
            "C17: 4♥\n" +
            "C18: 5♥\n" +
            "C19: 6♥\n" +
            "C20: 7♥\n" +
            "C21: 8♥\n" +
            "C22: 9♥\n" +
            "C23: 10♥\n" +
            "C24: J♥\n" +
            "C25: Q♥\n" +
            "C26: K♥\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣", model.getGameState());
  }

  /**
   * Tests that you can make a single move to an open pile.
   */
  @Test
  public void testMultiMoveWithOneCardToOpen() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: A♦\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "C1:\n" +
            "C2: 2♦\n" +
            "C3: 3♦\n" +
            "C4: 4♦\n" +
            "C5: 5♦\n" +
            "C6: 6♦\n" +
            "C7: 7♦\n" +
            "C8: 8♦\n" +
            "C9: 9♦\n" +
            "C10: 10♦\n" +
            "C11: J♦\n" +
            "C12: Q♦\n" +
            "C13: K♦\n" +
            "C14: A♥\n" +
            "C15: 2♥\n" +
            "C16: 3♥\n" +
            "C17: 4♥\n" +
            "C18: 5♥\n" +
            "C19: 6♥\n" +
            "C20: 7♥\n" +
            "C21: 8♥\n" +
            "C22: 9♥\n" +
            "C23: 10♥\n" +
            "C24: J♥\n" +
            "C25: Q♥\n" +
            "C26: K♥\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36: 10♠\n" +
            "C37: J♠\n" +
            "C38: Q♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣", model.getGameState());
  }

  /**
   * Tests that you can make multiple multimoves.
   */
  @Test
  public void testMultiMoveWithTwoMultiMoves() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.CASCADE, 37);
    model.move(PileType.CASCADE, 35, 0, PileType.CASCADE, 23);
    model.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 37);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "C1: A♦\n" +
            "C2: 2♦\n" +
            "C3: 3♦\n" +
            "C4: 4♦\n" +
            "C5: 5♦\n" +
            "C6: 6♦\n" +
            "C7: 7♦\n" +
            "C8: 8♦\n" +
            "C9: 9♦\n" +
            "C10: 10♦\n" +
            "C11: J♦\n" +
            "C12: Q♦\n" +
            "C13:\n" +
            "C14: A♥\n" +
            "C15: 2♥\n" +
            "C16: 3♥\n" +
            "C17: 4♥\n" +
            "C18: 5♥\n" +
            "C19: 6♥\n" +
            "C20: 7♥\n" +
            "C21: 8♥\n" +
            "C22: 9♥\n" +
            "C23: 10♥\n" +
            "C24:\n" +
            "C25: Q♥\n" +
            "C26: K♥\n" +
            "C27: A♠\n" +
            "C28: 2♠\n" +
            "C29: 3♠\n" +
            "C30: 4♠\n" +
            "C31: 5♠\n" +
            "C32: 6♠\n" +
            "C33: 7♠\n" +
            "C34: 8♠\n" +
            "C35: 9♠\n" +
            "C36:\n" +
            "C37: J♠\n" +
            "C38: K♦, Q♠, J♥, 10♠\n" +
            "C39: K♠\n" +
            "C40: A♣\n" +
            "C41: 2♣\n" +
            "C42: 3♣\n" +
            "C43: 4♣\n" +
            "C44: 5♣\n" +
            "C45: 6♣\n" +
            "C46: 7♣\n" +
            "C47: 8♣\n" +
            "C48: 9♣\n" +
            "C49: 10♣\n" +
            "C50: J♣\n" +
            "C51: Q♣\n" +
            "C52: K♣", model.getGameState());
  }

  /**
   * Tests if the game is over using a multimove model with valid multimoves.
   */
  @Test
  public void testMultiMoveGameOver() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.CASCADE, 37);
    for (int i = 0; i < 12; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    for (int j = 13; j < 26; j++) {
      model.move(PileType.CASCADE, j, 0, PileType.FOUNDATION, 1);
    }
    for (int k = 26; k < 36; k++) {
      model.move(PileType.CASCADE, k, 0, PileType.FOUNDATION, 2);
    }
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 37, 1, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 2);
    for (int l = 39; l < 52; l++) {
      model.move(PileType.CASCADE, l, 0, PileType.FOUNDATION, 3);
    }
    assertEquals(true, model.isGameOver());
  }

  /**
   * Tests that the game is not over.
   */
  @Test
  public void testMultiGameOver() {
    model.startGame(model.getDeck(), 52, 13, false);
    model.move(PileType.CASCADE, 37, 0, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 0, PileType.CASCADE, 37);
    model.move(PileType.CASCADE, 35, 0, PileType.CASCADE, 23);
    model.move(PileType.CASCADE, 23, 0, PileType.CASCADE, 37);
    assertEquals(false, model.isGameOver());
  }
}