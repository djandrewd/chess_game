package ua.danit.chess;

import ua.danit.chess.figures.Figure;

import java.util.Collection;

/**
 * MoveResult.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2017.06
 */
public class MoveResult {
    private int errorCode;
    private Collection<Figure> beatItems;

    public MoveResult(int errorCode, Collection<Figure> beatItems) {
        this.errorCode = errorCode;
        this.beatItems = beatItems;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Collection<Figure> getBeatItems() {
        return beatItems;
    }
}
