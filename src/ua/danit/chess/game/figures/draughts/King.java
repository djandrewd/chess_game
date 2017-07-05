package ua.danit.chess.game.figures.draughts;

import ua.danit.chess.game.Color;
import ua.danit.chess.game.Point;

import java.util.Collections;
import java.util.List;

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
    public List<List<Point>> getPossibleMoves(Point position, int boardSize) {
        // TODO.
        return Collections.emptyList();
    }
}
