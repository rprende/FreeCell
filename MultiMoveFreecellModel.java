package cs3500.hw04;

import java.util.List;
import java.util.ArrayList;
import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

/**
 * Represents a model of the game Freecell in which you can move multiple cards at once between
 * cascade piles and extends FreecellModel class.
 */
public class MultiMoveFreecellModel extends FreecellModel {

  /**
   * Moves multiple cards if they form a valid build and if there are enough open spots to make the
   * move.
   * Moves single cards by delegating to the move method in the FreecellModel class.
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is invalid
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if ((pileNumber < 0) || (cardIndex < 0) || (destPileNumber < 0)) {
      throw new IllegalArgumentException("invalid move");
    }
    if (source.equals(PileType.CASCADE) && destination.equals(PileType.CASCADE) &&
            cardIndex != (cascades.get(pileNumber).cards.size() - 1)) {
      makeMultiMove(pileNumber, cardIndex, destPileNumber);
    }
    else {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  /**
   * Moves multiple cards between cascade piles.
   * @param pileNumber the cascade pile to move the cards from
   * @param cardIndex the index of the card to start moving cards from
   * @param destPileNumber the cascade pile to move the cards to
   */
  private void makeMultiMove(int pileNumber, int cardIndex, int destPileNumber) {
    List<Card> sourcePile = cascades.get(pileNumber).cards;
    List<Card> destPile = cascades.get(destPileNumber).cards;
    List<Card> cardsToAdd = sourcePile.subList(cardIndex, sourcePile.size());
    if (checkBuild(cardsToAdd) && enoughSpots(cardsToAdd.size(), destPile) &&
            checkBuildWithDestination(destPile, cardsToAdd.get(0))) {
      List<Card> copy = new ArrayList<Card>(cardsToAdd);
      for (int i = 0; i < copy.size(); i++) {
        cascades.get(destPileNumber).cards.add(copy.get(i));
        cascades.get(pileNumber).cards.remove(cardIndex);
      }
      }
    else {
      throw new IllegalArgumentException("invalid move");
    }
  }

  /**
   * Determines if a given card forms a build with the last card in the pile it is being added to.
   * @param dest the pile of cards that the card is being moved to
   * @param newCard the card to be moved
   * @return if the card forms a build
   */
  private boolean checkBuildWithDestination(List<Card> dest, Card newCard) {
    if (dest.size() == 0) {
      return true;
    }
    else {
      Card lastCard = dest.get(dest.size() - 1);
      return lastCard.alternatingSuit(newCard) && lastCard.isBelow(newCard);
    }
  }

  /**
   * Checks if a given list of cards forms a build.
   * A build consists of cards that have consecutive decreasing value and alternating color.
   * @param cards list of cards to check
   * @return if the cards form a build
   */
  private boolean checkBuild(List<Card> cards) {
    for (int i = 1; i < cards.size(); i++) {
      Card lastCard = cards.get(i - 1);
      Card newCard = cards.get(i);
      if (lastCard.alternatingSuit(newCard) && lastCard.isBelow(newCard)) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines if there are enough empty open piles and cascade piles to make a multiple card
   * move. This is determined by the formula:  (N+1)*2K , where N is the number of empty open piles
   * and k is number of empty cascade piles.
   * @param numCards length of list of cards to be moved
   * @param dest destination pile of the cards to be moved
   * @return if there are enough spots to move
   */
  private boolean enoughSpots(int numCards, List<Card> dest) {
    int k = 0;
    int n = 0;
    for (Pile p : cascades) {
      if (p.cards.size() == 0) {
        k++;
      }
    }
    for (Pile o : open) {
      if (o.cards.size() == 0) {
        n++;
      }
    }
    if (dest.size() == 0) {
      double maxAmount = (n + 1) * Math.pow(2, k - 1);
      int max = (int)maxAmount;
      return (numCards - 1) < max;
    }
    else {
      double maxAmount = (n + 1) * Math.pow(2, k);
      int max = (int)maxAmount;
      return (numCards - 1) < max;
    }
  }
}
