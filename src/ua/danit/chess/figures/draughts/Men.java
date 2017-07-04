package ua.danit.chess.figures.draughts;

import ua.danit.chess.Point;
import ua.danit.chess.figures.AbstractColoredFigure;

import java.util.List;

/**
 * This is ordinal type of figure in draughts.
 * It can move one step in every diagonal location.
 *
 * @author Andrey Minov
 */
public class Men extends AbstractColoredFigure {

    /**
     * Instantiates a new Men figure.
     *
     * @param color the color, might be 0 or 1 when using standart chess rules.
     */
    public Men(int color) {
        super(color);
    }


    @Override
    public List<Point> movePath(Point positionFrom, Point positionTo) {
        return null;
    }
}
