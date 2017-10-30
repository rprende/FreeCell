package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.Card;
import cs3500.hw02.PileType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *  Represents a user providing input to the game Freecell.
 */
public class FreecellController implements IFreecellController<Card> {
  private Readable rd;
  private Appendable ap;

  /**
   * Constructs a FreecellController in terms of a Readable object and an Appendable object.
   * @param rd represents the string of input from a user
   * @param ap represents the output of the program
   * @throws IllegalStateException if rd or ap are null
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;

    if (rd == null || ap == null ) {
      throw new IllegalStateException("Readable and/or Appendable cannot be null.");
    }
  }

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
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) {
    Scanner sc = new Scanner(rd);
    String input = "";
    PileType source = PileType.FOUNDATION;
    int pileNumber = 0;
    int cardIndex = 0;
    PileType destination = PileType.FOUNDATION;
    int destPileNumber = 0;
    int count = 0;
    boolean isSet = false;
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Deck and/or model cannot be null.");
    }
    else {
      try {
        try {
          model.startGame(deck, numCascades, numOpens, shuffle);
          ap.append(model.getGameState());
        } catch (IllegalArgumentException e) {
          ap.append("Could not start game.");
          return;
        }
        while (!model.isGameOver() && sc.hasNext()) {
          input = sc.next();
          if (input.equals("q") || input.equals("Q")) {
            ap.append(model.getGameState());
            ap.append("\nGame quit prematurely.");
            return;
          }
          else if (count == 0) {
            try {
              source = checkValidPile(input);
              pileNumber = Integer.parseInt(input.substring(1, input.length())) - 1;
              count = 1;
            } catch (IllegalArgumentException e) {
              ap.append("\nPlease input again.");
            }
          }
          else if (count == 2) {
            try {
              destination = checkValidPile(input);
              destPileNumber = Integer.parseInt(input.substring(1, input.length())) - 1;
              count = 3;
            } catch (IllegalArgumentException e) {
              ap.append("\nPlease input again.");
            }
            if (count == 3) {
              try {
                model.move(source, pileNumber, cardIndex, destination, destPileNumber);
                ap.append(model.getGameState());
                count = 0;
              } catch (IllegalArgumentException e) {
                ap.append("\nInvalid move.");
                count = 0;
              }
            }
          }
          else if (count == 1) {
            try {
              cardIndex = Integer.valueOf(input) - 1;
              count = 2;
            } catch (NumberFormatException e) {
              ap.append("\nInvalid card index. Please input again.");
            }
          }
        }
        if (model.isGameOver()) {
          ap.append(model.getGameState());
          ap.append("\nGame over.");
          return;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Checks if the given input can represent a valid Pile.
   * @param input String representing the player's input
   * @return PileType indicated by input
   * @throws IllegalArgumentException if the input indicates an invalid Pile.
   */
  private PileType checkValidPile(String input) {
    if (input.substring(0, 1).equals("C")) {
      return PileType.CASCADE;
    } else if (input.substring(0, 1).equals("O")) {
      return PileType.OPEN;
    } else if (input.substring(0, 1).equals("F")) {
      return PileType.FOUNDATION;
    } else {
      throw new IllegalArgumentException("invalid pile");
    }
  }
}