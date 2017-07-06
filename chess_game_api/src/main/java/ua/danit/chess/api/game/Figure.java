package ua.danit.chess.api.game;

import java.util.List;

/**
 * Describe abstraction for figure on table.
 *
 * @author Andrey Minov
 * @since 2017.06
 */
public interface Figure {
  /**
   * Get path of figure by making move from position A to position B.
   *
   * @param positionFrom position on board where figure is located.
   * @param positionTo   position where this fugure must be moved.
   * @return list of positions effected by this move. If move cannot be made list is empty.
   */
  List<Point> movePath(Point positionFrom, Point positionTo);

  /**
   * Gets color of current figure.
   *
   * @return the color of concrete figure
   */
  Color getColor();
}
