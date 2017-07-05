package ua.danit.chess.api.game;

/**
 * Describe point at two dimensional figure.
 *
 * @author Andrey Minov
 */
public class Point {

    private int xCoordinate;
    private int yCoordinate;

    /**
     * Instantiates a new Point.
     *
     * @param xCoordinate the x axis coordinate of point
     * @param yCoordinate the y axis coordinate of point
     */
    private Point(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Gets X axis coordinate of point.
     *
     * @return the X axis coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Gets Y axis coordinate of point.
     *
     * @return Y axis coordinate of point.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Check if current point is valid : x and y coordinate is positive.
     *
     * @return the true in case x and y coordinate is greated or equals to zero.
     */
    public boolean isValid() {
        return xCoordinate >= 0 && yCoordinate >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (xCoordinate != point.xCoordinate) return false;
        return yCoordinate == point.yCoordinate;
    }

    @Override
    public int hashCode() {
        int result = xCoordinate;
        result = 31 * result + yCoordinate;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }

    /**
     * Creates a new Point instance.
     *
     * @param xCoordinate the x axis coordinate of point
     * @param yCoordinate the y axis coordinate of point
     */
    public static Point create(int xCoordinate, int yCoordinate) {
        return new Point(xCoordinate, yCoordinate);
    }
}
