package ua.danit.chess;

import ua.danit.chess.figures.Figure;

import java.util.function.Function;

/**
 * FugureAction.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2017.06
 */
public interface FigureMoveRule {
    MoveResult move(int positionA, int positionB, Function<Integer, Figure> figureProvider);
}
