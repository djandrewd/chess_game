package ua.danit.chess.game.figures.draughts;

import java.util.List;

import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Point;

/**
 * This is king figure in draughts. This figure can move by diagonal in any type of steps.
 * Man figure can become king when accessing opposite player starting line.
 *
 * @author Andrey Minov
 */
public class King extends AbstractDraughtFigure {
  /**
   * Instantiates a new King figure.
   *
   * @param color the color, might be 0 or 1 when using standart chess rules.
   */
  public King(Color color) {
    super(color);
  }

  @Override
  public boolean isOnlyBeatMove(List<Point> move) {
    // King long as king figure can move in any way no matter if it beat something or not.
    return false;
  }

  @Override
  public String toString() {
    return "K[" + super.toString() + "]";
  }
}
