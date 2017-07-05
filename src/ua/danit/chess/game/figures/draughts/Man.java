package ua.danit.chess.game.figures.draughts;

import ua.danit.chess.game.Point;

import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

/**
 * This is ordinal type of figure in draughts.
 * It can move one step in every diagonal location.
 *
 * @author Andrey Minov
 */
public class Man extends AbstractDraughtFigure {

    /**
     * Maximum possible move made by man fugure.
     */
    private static final int MAX_MOVE = 2;

    /**
     * Instantiates a new Man figure.
     *
     * @param color the color, might be 0 or 1 when using standart chess rules.
     */
    public Man(int color) {
        super(color);
    }


    @Override
    public List<Point> movePath(Point positionFrom, Point positionTo) {
        int diffX = positionTo.getxCoordinate() - positionFrom.getxCoordinate();
        int diffY = positionTo.getyCoordinate() - positionFrom.getyCoordinate();
        if (abs(diffX) > MAX_MOVE || abs(diffY) > MAX_MOVE) {
            return Collections.emptyList();
        }
        return super.movePath(positionFrom, positionTo);
    }

    @Override
    public boolean isOnlyBeatMove(List<Point> move) {
        return !move.isEmpty() && move.size() == MAX_MOVE;
    }

    @Override
    public List<List<Point>> getPossibleMoves(Point position, int boardSize) {
        // TODO later.
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "M[" + super.toString() + "]";
    }
}
