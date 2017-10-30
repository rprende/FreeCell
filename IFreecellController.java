package cs3500.hw03;

import cs3500.hw02.FreecellOperations;

import java.util.List;

/**
 * Interface for a FreecellController and is paramaterized over card type.
 * @param <K> type of card
 */
public interface IFreecellController<K> {

  /**
   * Interprets input from player and plays the game accordingly.
   * Tells user if game can start, if move is invalid, or if the input was invalid.
   * Allows the user to quit gae prematurely.
   * Indicates when the game is over.
   * @param deck the deck of cards to play the game with
   * @param model the Freecell model of the game
   * @param numCascades number of cascade piles
   * @param numOpens number of open piles
   * @param shuffle indicates if the deck is to be shuffled or not
   * @throws IllegalArgumentException if the model or deck are null
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades, int numOpens,
                boolean shuffle);
}
