package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Creates a FreecellModel depending on whether or not multiple moves are to be allowed.
 */
public class FreecellModelCreator {

  /**
   * Enumeration of Game Types, including only allowing single moves and allowing single and
   * multiple moves.
   */
  public static enum GameType {
    SINGLEMOVE,
    MULTIMOVE;
  }

  /**
   * Returns a GameType depending on if multiple card moves are to be allowed or not.
   * @param type singlemove or multimove
   * @return the appropriate FreecellOperations
   */
  public static FreecellOperations create(GameType type) {
    if (type == (GameType.SINGLEMOVE)) {
      return new FreecellModel();
    }
    else {
      return new MultiMoveFreecellModel();
    }
  }

  /**
   * Returns a String based on the FreecellOperations type that is given.
   * Used to test the method create.
   * @param type the FreecellOperations given
   * @return String to represent that GameType
   */
  public String toString(FreecellOperations type) {
    if (type instanceof MultiMoveFreecellModel) {
      return "MultiMoveFreecellModel";
    }
    else {
      return "FreecellModel";
    }
  }
}
