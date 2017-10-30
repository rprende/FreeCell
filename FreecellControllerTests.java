package cs3500.hw02;

import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import cs3500.hw03.FreecellController;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the methods in the FreecellController class.
 */
public class FreecellControllerTests {
  FreecellModel model = new FreecellModel();
  List<Card> deck = model.getDeck();

  /**
   * Tests the playGame method when the game is over.
   */
  @Test
  public void testGameOverThroughController() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 C5 1 F1 C6 1 F1 C7 1 F1 C8 "
            +
            "1 F1 C9 1 F1 C10 1 F1 C11 1 F1 C12 1 F1 C13 1 F1 C14 1 F2 C15 1 F2 C16 1 F2 C17 1 F2 "
            +
            "C18 1 F2 C19 1 F2 C20 1 F2 C21 1 F2 C22 1 F2 C23 1 F2 C24 1 F2 C25 1 F2 C25 1 F2 C26 1"
            +
            " F2 C27 1 F3 C28 1 F3 C29 1 F3 C30 1 F3 C31 1 F3 C32 1 F3 C33 1 F3 C34 1 F3 C35 1 F3 "
            +
            "C36 1 F3 C37 1 F3 C38 1 F3 C39 1 F3 C40 1 F4 C41 1 F4 C42 1 F4 C43 1 F4 C44 1 F4 C45 1"
            +
            " F4 C46 1 F4 C47 1 F4 C48 1 F4 C49 1 F4 C50 1 F4 C51 1 F4 C52 1 F4");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 52, 52, false);
    assertEquals("Game over.", out.toString().substring(out.toString().length() - 10,
            out.toString().length()));
  }

  /**
   * Tests the playGame method when the game cannot be started.
   */
  @Test
  public void testCouldNotStartGame() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 2, -1, false);
    assertEquals("Could not start game.", out.toString());
  }

  /**
   * Tests the playGame method when the game is quit prematurely by the user.
   */
  @Test
  public void testGameQuitPrematurelyWithQ() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Game quit prematurely.",
            out.toString().substring(out.toString().length() - 22, out.toString().length()));
  }

  /**
   * Tests the playGame method when the game is quit prematurely by the user.
   */
  @Test
  public void testGameQuitPrematurelyWithq() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Game quit prematurely.",
            out.toString().substring(out.toString().length() - 22, out.toString().length()));
  }

  /**
   * Tests the playGame method when an invalid move is input by the user.
   */
  @Test
  public void testInvalidMove() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 13 F1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Invalid move.",
            out.toString().substring(out.toString().length() - 13, out.toString().length()));
  }

  /**
   * Tests the playGame method when the user gives malformed input for the source pile.
   */
  @Test
  public void testPleaseInputAgainWithBadSource() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("X1 13 F1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Please input again.",
            out.toString().substring(out.toString().length() - 19, out.toString().length()));
  }

  /**
   * Tests the playGame method when the user gives malformed input for the destination pile.
   */
  @Test
  public void testPleaseInputAgainWithBadDestination() throws Exception {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 13 X1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Please input again.",
            out.toString().substring(out.toString().length() - 19, out.toString().length()));
  }

  /**
   * Tests the playGame method when the user gives malformed input for the card index.
   */
  @Test
  public void testPleaseInputAgainWithBadCardIndex() throws Exception {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 what F1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("Invalid card index. Please input again.",
            out.toString().substring(out.toString().length() - 39, out.toString().length()));
  }

  /**
   * Tests the playGame method when making two valid moves consecutively.
   */
  @Test
  public void testPlayGameWithTwoMoves() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 1 F1 C2 1 F1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 52, 52, false);
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
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
            "C52: K♣F1: A♦\n" +
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
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
            "C52: K♣F1: A♦, 2♦\n" +
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
            "C1:\n" +
            "C2:\n" +
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
            "C52: K♣", out.toString());
  }

  /**
   * Tests the method playGame when given two valid moves then an invalid move.
   */
  @Test
  public void testPlayGameWithTwoMovesInvalidMove() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("C1 1 F1 C2 1 F1 C2 1 F1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, model, 52, 52, false);
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
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
            "C52: K♣F1: A♦\n" +
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
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
            "C52: K♣F1: A♦, 2♦\n" +
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
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
            "C1:\n" +
            "C2:\n" +
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
            "C52: K♣\n" +
            "Invalid move.", out.toString());
  }
}

