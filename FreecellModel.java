package cs3500.hw02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a model of the game Freecell.
 */
public class FreecellModel implements FreecellOperations<Card> {
  protected ArrayList<Pile> cascades;
  protected ArrayList<Pile> foundations;
  protected ArrayList<Pile> open;

  /**
   * Constructs a model of the game Freecell.
   * cascades represents all the cascade piles in the game.
   * foundations represents all the foundation piles in the game.
   * open represents all the open piles in the game.
   */
  public FreecellModel() {
    this.cascades = new ArrayList<Pile>();
    this.foundations = new ArrayList<Pile>();
    this.open = new ArrayList<Pile>();
  }

  /**
   * Returns a list of Cards, which represents a deck of playing cards, without dupilcates and
   * of size 52.
   * @return a list of Cards
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();
    Set<Card> dupes = new HashSet<>();
    for (Suit s : Suit.values()) {
      for (CardValue value : CardValue.values()) {
        Card newCard = new Card(s, value);
        deck.add(newCard);
        dupes.add(newCard);
      }
    }
    if (deck.size() != 52) {
      throw new IllegalArgumentException("invalid deck");
    } else if (dupes.size() < deck.size()) {
      throw new IllegalArgumentException("invalid deck");
    } else {
      return deck;
    }
  }

  /**
   * Starts a game in the Freecell Model by adding a given number of cascade piles to the list of
   * cascade piles and a given number of open piles to the list of open piles.
   * It shuffles the deck if shuffle is true and deals the deck of cards.
   * @param deck            the deck to be dealt
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   * @param shuffle         if true, shuffle the deck else deal the deck as-is
   * @throws IllegalArgumentException thrown if the deck is invalid or pile numbers are invalid
   */
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {
    foundations = new ArrayList<Pile>();
    open = new ArrayList<Pile>();
    cascades = new ArrayList<Pile>();
    Set<Card> dupes = new HashSet<>();
    if (deck.size() != 52) { //MADE CHANGES
      throw new IllegalArgumentException("invalid deck");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("too few cascade piles");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("too few open piles");
    }
    for (Card c : deck) {
      dupes.add(c);
    }
    if (dupes.size() < deck.size()) {
      throw new IllegalArgumentException("invalid deck");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }
    for (int i = 0; i < 4; i++) {
      ArrayList<Card> cards = new ArrayList<Card>();
      foundations.add(new Pile(PileType.FOUNDATION, cards));
    }
    for (int i = 0; i < numOpenPiles; i++) {
      ArrayList<Card> cards = new ArrayList<Card>();
      open.add(new Pile(PileType.OPEN, cards));
    }
    for (int i = 0; i < numCascadePiles; i++) {
      ArrayList<Card> cards = new ArrayList<Card>();
      cascades.add(new Pile(PileType.CASCADE, cards));
    }
    for (int k = 0; k < 52; k++) {
      if (k == 0) {
        cascades.get(0).cards.add(deck.get(0));
      } else {
        cascades.get(k % numCascadePiles).cards.add(deck.get(k));
      }
    }
  }

  /**
   * Moves a card from one pile to another if the card meets the requirements to be moved to that
   * type of pile or throws an Exception if the move cannot be made.
   * Added checks for validity of pileNumber, cardIndex, and destPileNumber and abstracted
   * out some functionality in hw03.
   * Created helper methods (isAbove, isBelow, alternatingSuit)
   * to determine if a card could be added to a foundation or cascade pile in hw04.
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException thrown is move cannot be made
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if ((pileNumber < 0) || (cardIndex < 0) || (destPileNumber < 0)) {
      throw new IllegalArgumentException("invalid move");
    }
    Card newCard = findCardToBeAdded(source, pileNumber, cardIndex);
    if (destination.equals(PileType.FOUNDATION) && (foundations.size() > destPileNumber)) {
      if (foundations.get(destPileNumber).cards.size() == 0 && newCard.value.v.equals("A")) {
        foundations.get(destPileNumber).cards.add(newCard);
        removeFromSource(source, pileNumber, newCard);
      } else if (foundations.get(destPileNumber).cards.size() != 0
              &&
              foundations.get(destPileNumber).cards.get(foundations.get(destPileNumber)
                      .cards.size() - 1).canBeAdded(newCard).equals("foundation")
              &&
              foundations.get(destPileNumber).cards.get(foundations.get(destPileNumber)
                      .cards.size() - 1).suit.symbol.equals(newCard.suit.symbol)) {
        foundations.get(destPileNumber).cards.add(newCard);
        removeFromSource(source, pileNumber, newCard);
      } else {
        throw new IllegalArgumentException("move is not possible");
      }
    } else if (destination.equals(PileType.CASCADE) && (cascades.size() > destPileNumber)) {
      if (source.equals(PileType.CASCADE)) {
        Card lastCard = cascades.get(destPileNumber).cards
                .get(cascades.get(destPileNumber).cards.size() - 1);
        if (lastCard.alternatingSuit(newCard) && lastCard.isBelow(newCard)) {
          cascades.get(destPileNumber).cards.add(newCard);
          removeFromSource(source, pileNumber, newCard);
        } else {
          throw new IllegalArgumentException("move is not possible");
        }
      }
    } else if (destination.equals(PileType.OPEN) && (open.size() > destPileNumber)) {
      if (open.get(destPileNumber).cards.size() < 1) {
        open.get(destPileNumber).cards.add(newCard);
        removeFromSource(source, pileNumber, newCard);
      } else {
        throw new IllegalArgumentException("move is not possible");
      }
    }
  }

  /**
   * Added in hw03.
   * Returns the card to be added to a Pile.
   * @param source the source pile of the card
   * @param pileNumber the number of the pile the card was in
   * @param cardIndex the index of the card in the pile
   * @return the card to be moved
   * @throws IllegalArgumentException if the card cannot be moved from the pile
   */
  private Card findCardToBeAdded(PileType source, int pileNumber, int cardIndex) {
    if (source.equals(PileType.CASCADE)) {
      if ((cardIndex != (cascades.get(pileNumber).cards.size() - 1)) ||
              (cascades.size() <= pileNumber)) {
        throw new IllegalArgumentException("invalid move");
      } else {
        return cascades.get(pileNumber).cards.get(cardIndex);
      }
    } else if (source.equals(PileType.OPEN)) {
      if ((cardIndex != open.get(pileNumber).cards.size() - 1) || (open.size() <= pileNumber)) {
        throw new IllegalArgumentException("invalid move");
      } else {
        return open.get(pileNumber).cards.get(cardIndex);
      }
    } else if (source.equals(PileType.FOUNDATION)) {
      if ((cardIndex != foundations.get(pileNumber).cards.size() - 1) ||
              (foundations.size() <= pileNumber)) {
        throw new IllegalArgumentException("invalid move");
      } else {
        return foundations.get(pileNumber).cards.get(cardIndex);
      }
    }
    else {
      throw new IllegalArgumentException("invalid move");
    }
  }

  /**
   * Added in hw03.
   * Removes a given card from a Pile.
   * @param source pile to remove a card.
   * @param pileNumber number of the pile the card was in
   * @param card card to be removed
   */
  private void removeFromSource(PileType source, int pileNumber, Card card) {
    if (source.equals(PileType.OPEN)) {
      open.get(pileNumber).cards.remove(card);
    } else if (source.equals(PileType.CASCADE)) {
      cascades.get(pileNumber).cards.remove(card);
    } else {
      foundations.get(pileNumber).cards.remove(card);
    }
  }

  /**
   * Returns true if the game is over when each foundation pile contains all the cards of one suit.
   * Catches IndexOutOfBoundsException that would be thrown if isGameOver() is called before
   * startGame() is called.
   * @return true if the game is over, else false
   */
  @Override
  public boolean isGameOver() {
    try {
      return ((foundations.get(0).cards.size() == 13) && (foundations.get(1).cards.size() == 13)
              &&
              (foundations.get(2).cards.size() == 13) && (foundations.get(3).cards.size() == 13));
    }
    catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  /**
   * Returns a String representation of the current game by displaying the cards in each pile.
   * @return a String representation of the game
   */
  @Override
  public String getGameState() {
    String gameState = "";
    if (foundations.size() == 0) {
      return gameState;
    } else {
      for (int i = 0; i < foundations.size(); i++) {
        gameState += "F" + (i + 1) + ":";
        if (foundations.get(i).cards.toString().equals("[]")) {
          gameState += "\n";
        } else {
          gameState += foundations.get(i).cards.toString()
                  .replace("[", " ").replace("]", "");
          gameState += "\n";
        }
      }
      for (int i = 0; i < open.size(); i++) {
        gameState += "O" + (i + 1) + ":";
        if (open.get(i).cards.toString().equals("[]")) {
          gameState += "\n";
        } else {
          gameState += open.get(i).cards.toString()
                  .replace("[", " ").replace("]", "");
          gameState += "\n";
        }
      }
      for (int i = 0; i < cascades.size(); i++) {
        gameState += "C" + (i + 1) + ":";
        if (cascades.get(i).cards.toString().equals("[]")) {
          gameState += "\n";
        } else if (i != cascades.size() - 1) {
          gameState += cascades.get(i).cards.toString()
                  .replace("[", " ").replace("]", "");
          gameState += "\n";
        } else {
          gameState += cascades.get(i).cards.toString()
                  .replace("[", " ").replace("]", "");
        }
      }
      return gameState;
    }
  }
}
