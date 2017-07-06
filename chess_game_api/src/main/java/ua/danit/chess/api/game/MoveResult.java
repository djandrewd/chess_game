package ua.danit.chess.api.game;

import java.util.Map;

import com.google.common.base.MoreObjects;


/**
 * This entity holds result information about move.
 *
 * @author Andrey Minov
 */
public class MoveResult {
  private long moveId;
  private GameErrors error;
  private Color nextMove;
  private Color winner;
  private Map<Point, Figure> beatedFigures;

  /**
   * Instantiates a new Move result.
   *
   * @param error the error result for this move.
   *              Can be {@link GameErrors}.OK in case move is successful
   */
  public MoveResult(GameErrors error) {
    this.error = error;
  }


  /**
   * Instantiates a new Move result.
   *
   * @param error         the error result for this move. Will be {@link GameErrors}.
   *                      OK in case move is successful.
   * @param moveId        unique if for the move to help updo, redo last players move.
   * @param beatedFigures the beated figures during this move.
   * @param nextMove      color of player that is next turn.
   * @param winner        current winner if winMove is true.
   */
  public MoveResult(GameErrors error, long moveId, Map<Point, Figure> beatedFigures, Color nextMove,
                    Color winner) {
    this(error);
    this.beatedFigures = beatedFigures;
    this.nextMove = nextMove;
    this.winner = winner;
  }

  /**
   * Gets the error result for this move. Can be 0 in case move is successful..
   *
   * @return the error result for this move. Will be {@link GameErrors}.OK
   * in case move is successful.
   */
  public GameErrors getError() {
    return error;
  }

  /**
   * Gets winner color in case this move is winning one.
   *
   * @return the winner in case this move is winning one, null otherwise
   */
  public Color getWinner() {
    return winner;
  }

  /**
   * Gets color of player that will have next turn.
   *
   * @return color of player that will have next turn.
   */
  public Color getNextMove() {
    return nextMove;
  }

  /**
   * Gets beated figures.
   *
   * @return the beated figures
   */
  public Map<Point, Figure> getBeatedFigures() {
    return beatedFigures;
  }

  public long getMoveId() {
    return moveId;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
                      .add("moveId", moveId)
                      .add("error", error)
                      .add("nextMove", nextMove)
                      .add("winner", winner)
                      .add("beatedFigures", beatedFigures)
                      .toString();
  }
}
