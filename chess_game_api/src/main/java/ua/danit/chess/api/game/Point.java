package ua.danit.chess.api.game;

import com.google.common.base.MoreObjects;

/**
 * Describe point at two dimensional figure.
 *
 * @author Andrey Minov
 */
public class Point {

  private final int xaCoordinate;
  private final int yaCoordinate;

  /**
   * Instantiates a new Point.
   *
   * @param xaCoordinate the x axis coordinate of point
   * @param yaCoordinate the y axis coordinate of point
   */
  private Point(int xaCoordinate, int yaCoordinate) {
    if (xaCoordinate < 0 || yaCoordinate < 0) {
      throw new IllegalArgumentException("Coordinates must be positive values!");
    }
    this.xaCoordinate = xaCoordinate;
    this.yaCoordinate = yaCoordinate;
  }

  /**
   * Creates a new Point instance.
   *
   * @param xaCoordinate the x axis coordinate of point
   * @param yaCoordinate the y axis coordinate of point
   */
  public static Point create(int xaCoordinate, int yaCoordinate) {
    return new Point(xaCoordinate, yaCoordinate);
  }

  /**
   * Gets X axis coordinate of point.
   *
   * @return the X axis coordinate
   */
  public int getXaCoordinate() {
    return xaCoordinate;
  }

  /**
   * Gets Y axis coordinate of point.
   *
   * @return Y axis coordinate of point.
   */
  public int getYaCoordinate() {
    return yaCoordinate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point point = (Point) o;

    if (xaCoordinate != point.xaCoordinate) {
      return false;
    }
    return yaCoordinate == point.yaCoordinate;
  }

  @Override
  public int hashCode() {
    int result = xaCoordinate;
    result = 31 * result + yaCoordinate;
    return result;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
                      .add("xaCoordinate", xaCoordinate)
                      .add("yaCoordinate", yaCoordinate)
                      .toString();
  }
}
