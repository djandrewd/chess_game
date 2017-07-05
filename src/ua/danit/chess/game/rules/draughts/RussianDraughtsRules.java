package ua.danit.chess.game.rules.draughts;

import ua.danit.chess.game.Point;
import ua.danit.chess.game.figures.draughts.AbstractDraughtFigure;
import ua.danit.chess.game.figures.draughts.Man;
import ua.danit.chess.game.rules.Board;
import ua.danit.chess.game.rules.GameRule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.danit.chess.game.Point.create;

/**
 * Describe rules for draughts. Any actions that must be made for user to win the game.
 *
 * @author Andrey Minov
 */
public class RussianDraughtsRules implements GameRule<AbstractDraughtFigure> {

    private static final int BOARD_SIZE = 8;
    private static final int COLOR_SIZE = BOARD_SIZE / 2;
    private static final int FIGURES_SIZE = 12;

    private int winnerColor;
    private int currentColor;

    private boolean isFinished;
    private Board<AbstractDraughtFigure> board;

    @Override
    public void init(int colorA, int colorB) {
        board = new Board<>(BOARD_SIZE);
        currentColor = colorA;
        for (int i = 0; i < FIGURES_SIZE / COLOR_SIZE; i++) {
            for (int j = 0; j < COLOR_SIZE; j++) {
                Man man = new Man(colorB);
                board.placeFigure(man, create(j * 2 + (i + 1) % 2, i));

                man = new Man(colorA);
                board.placeFigure(man, create(BOARD_SIZE - 1 - j * 2 - (i + 1) % 2, BOARD_SIZE - 1 - i));
            }
        }
    }

    @Override
    public boolean move(int color, Point positionFrom, Point positionEnd) {
        if (color != currentColor) {
            return false;
        }

        // Check figure found
        AbstractDraughtFigure figure = board.getFigure(positionFrom);
        if (figure == null) {
            return false;
        }
        // Do not allow to place figure where some other figure is located.
        if (board.getFigure(positionEnd) != null) {
            return false;
        }

        // TODO Check if possible beat moves on this figure not corresponds one needed!

        List<Point> move = figure.movePath(positionFrom, positionEnd);
        if (move.isEmpty()) {
            return false;
        }
        // If we pass figures of our type.
        if (move.stream().map(board::getFigure).allMatch(v -> v != null && v.getColor() == color)) {
            return false;
        }

        Map<Point, AbstractDraughtFigure> beatedFigures = move
                .stream()
                .filter(v -> board.getFigure(v) != null && board.getFigure(v).getColor() != color)
                .collect(Collectors.toMap(k -> k, k -> board.getFigure(k)));

        boolean onlyBeatMove = figure.isOnlyBeatMove(move);
        // check that we beat something during this move, because this move was made as beat.
        if (onlyBeatMove && beatedFigures.isEmpty()) {
            return false;
        }
        // TODO: return beated entries.
        // TODO: check that beat must be made by current players.
        return true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public int getWinnerColor() {
        return winnerColor;
    }

    @Override
    public Board<AbstractDraughtFigure> getPlayingBoard() {
        return null;
    }
}
