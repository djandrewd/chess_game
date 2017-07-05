package ua.danit.chess.game.rules;

import ua.danit.chess.game.Point;
import ua.danit.chess.game.figures.Figure;

/**
 * Game board with figures and spots.
 * <p>
 *
 * @param <T> the type parameter
 * @author Andrey Minov
 */
public class Board<T extends Figure> {

    private int size;
    private Object[] figures;

    /**
     * Instantiates a new square Board with size equals size * size.
     *
     * @param size the of board edge.
     */
    public Board(int size) {
        this.size = size;
        this.figures = new Object[size * size];
    }

    /**
     * Place figure on position and return previous point on this position.
     *
     * @param figure the figure to place
     * @param point  the point where place the figure.
     * @return the figure previously placed on this position or null in case point is not existed.
     * @throws IllegalArgumentException in case point value is incorrect or extends bounds of board.
     */
    @SuppressWarnings("unchecked")
    public T placeFigure(T figure, Point point) {
        if (!point.isValid() || point.getxCoordinate() >= figures.length || point.getyCoordinate() >= figures.length) {
            throw new IllegalArgumentException("Incorrect point provided!");
        }
        Object value = figures[point.getxCoordinate() + size * point.getyCoordinate()];
        figures[point.getxCoordinate() + size * point.getyCoordinate()] = figure;
        return (T) value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Board [ \n");
        for (int i = 0; i < figures.length / size; i++) {
            for (int j = 0; j < figures.length / size; j++) {
                builder.append(String.format("%-10s ", figures[i * size + j]));
            }
            builder.append("\n");
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Gets figure at current point on the board.
     *
     * @param point the point where to search to point.
     * @return the figure on the board or null if figure not exists.
     * @throws IllegalArgumentException in case point value is incorrect or extends bounds of board.
     */
    @SuppressWarnings("unchecked")
    public T getFigure(Point point) {
        if (!point.isValid() || point.getxCoordinate() >= figures.length || point.getyCoordinate() >= figures.length) {
            throw new IllegalArgumentException("Incorrect point provided!");
        }
        return (T) figures[point.getxCoordinate() + size * point.getyCoordinate()];
    }
}
