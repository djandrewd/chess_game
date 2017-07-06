package ua.danit.chess.api.rules;

import ua.danit.chess.api.dao.GameState;
import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Figure;
import ua.danit.chess.api.game.MoveResult;
import ua.danit.chess.api.game.Point;

/**
 * GameRule.
 * <p/>
 * Common rules for all two players game
 *
 * @param <T> the type parameter
 * @author Andrey Minov
 * @since 2017.06
 */
public interface GameRule<T extends Figure> {

  /**
   * Init game for two with colors describing each members. Game must be started with colorA!
   *
   * @param colorA    the color of figures for first player.
   * @param colorB    the color of figures for second player.
   * @param gameState the game state previously stored in data store.
   */
  void init(Color colorA, Color colorB, GameState gameState);

  /**
   * Try to check if current game is finished.
   *
   * @return true if game is finished, false otherwise.
   */
  boolean isFinished();

  /**
   * Gets current winner color if game is finished, -1 otherwise.
   *
   * @return the winner color, of -1 if game is not yer finished.
   */
  Color getWinnerColor();

  /**
   * Gets current color which turn is.
   *
   * @return current color which turn is.
   */
  Color getCurrentTurn();

  /**
   * Move figure of color from position A to position B using.
   *
   * @param color        the color of figure to move.
   * @param positionFrom the position where figure is located.
   * @param positionEnd  the position where figure must be moved.
   * @return result of current move: error code, beated figures and flag indicating the game is won
   */
  MoveResult move(Color color, Point positionFrom, Point positionEnd);

  /**
   * Undo last move made by player.
   *
   * @param moveId to undo move.
   * @return result of current move: error code, beated figures and flag indicating the game is won
   */
  MoveResult undoMove(long moveId);

  /**
   * Redo last undo move made by player.
   *
   * @param moveId to redo move.
   * @return result of current move: error code, beated figures and flag indicating the game is won
   */
  MoveResult redoMove(long moveId);

  /**
   * Gets current playing board.
   *
   * @return the playing board for figures playing.
   */
  Board<T> getPlayingBoard();

}
