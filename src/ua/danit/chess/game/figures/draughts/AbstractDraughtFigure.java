package ua.danit.chess.game.figures.draughts;

import ua.danit.chess.game.Point;
import ua.danit.chess.game.figures.AbstractColoredFigure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static ua.danit.chess.game.Point.create;

/**
 * Describes abstract figure for playing draughts. In draughts figure can move differently in case
 * figure is beating or not. Also we need to know possible moves because due to playing rules
 * if beating is possible it must be made!
 *
 * @author Andrey Minov
 */
public abstract class AbstractDraughtFigure extends AbstractColoredFigure {
    AbstractDraughtFigure(int color) {
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

    /**
     * Check is this move must be made only in case when figure beat other figure.
     *
     * @param move the move
     * @return true only if this move can be make in case of beating other figure, false otherwise.
     */
    public abstract boolean isOnlyBeatMove(List<Point> move);

    /**
     * Gets possible moves for figure at position, according to playing board size.
     *
     * @param position  the position where figure is located
     * @param boardSize the board size
     * @return the list of possible moves of current figure.
     */
    public abstract List<List<Point>> getPossibleMoves(Point position, int boardSize);
}
