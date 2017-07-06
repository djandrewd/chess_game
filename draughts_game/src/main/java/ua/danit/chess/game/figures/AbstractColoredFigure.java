package ua.danit.chess.game.figures;

import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Figure;

/**
 * Presented figure that has color inside.
 *
 * @author Andrey Minov
 */
public abstract class AbstractColoredFigure implements Figure {

  /**
   * Color of concrete figure.
   */
  private Color color;

  /**
   * Instantiates a new Abstract colored figure.
   *
   * @param color the color, might be 0 or 1 when using standart chess rules.
   */
  protected AbstractColoredFigure(Color color) {
    this.color = color;
  }

  /**
   * Gets color of current figure..
   *
   * @return the color of concrete figure
   */
  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AbstractColoredFigure that = (AbstractColoredFigure) o;

    return color == that.color;
  }

  @Override
  public int hashCode() {
    return color != null ? color.hashCode() : 0;
  }

  @Override
  public String toString() {
    return String.valueOf(color);
  }
}
