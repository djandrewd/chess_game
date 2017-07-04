package ua.danit.chess.figures.draughts;

import ua.danit.chess.Point;
import ua.danit.chess.figures.AbstractColoredFigure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static ua.danit.chess.Point.create;

/**
 * This is king figure in draughts. This figure can move by diagonal in any type of steps.
 * Men figure can become king when accessing opposite player starting line.
 *
 * @author Andrey Minov
 */
public class King extends AbstractColoredFigure {
    /**
     * Instantiates a new King figure.
     *
     * @param color the color, might be 0 or 1 when using standart chess rules.
     */
    public King(int color) {
        super(color);
    }

    @Override
    public List<Point> movePath(Point positionFrom, Point positionTo) {
        if (!positionFrom.isValid() || !positionTo.isValid()) {
            return Collections.emptyList();
        }
        int diffX = positionTo.getxCoordinate() - positionFrom.getxCoordinate();
        int diffY = positionTo.getyCoordinate() - positionFrom.getyCoordinate();
        if (abs(diffX) != abs(diffY)) {
            return Collections.emptyList();
        }
        int signX = (int) signum(diffX);
        int signY = (int) signum(diffY);
        if (positionTo.getxCoordinate() + diffX * signX < 0 || positionTo.getyCoordinate() + diffY * signY < 0) {
            return Collections.emptyList();
        }
        List<Point> move = new ArrayList<>();
        for (int i = 1; i <= abs(diffX); i++) {
            Point point = create(positionFrom.getxCoordinate() + i * signX, positionFrom.getyCoordinate() + i * signY);
            move.add(point);
        }
        return move;
    }
}
