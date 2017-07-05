package ua.danit.chess.game.rules;

import ua.danit.chess.game.Point;
import ua.danit.chess.game.figures.Figure;

/**
 * GameRule.
 * <p/>
 * Common rules for all two players game
 *
 * @param <T> the type parameter
 * @author Andrey Minov
 * @since 2017.06
 */
public interface GameRule<T extends Figure> {

    /**
     * Init game for two with colors describing each members. Game must be started with colorA!
     *
     * @param colorA the color of figures for first player.
     * @param colorB the color of figures for second player.
     */
    void init(int colorA, int colorB);

    /**
     * Try to check if current game is finished.
     *
     * @return true if game is finished, false otherwise.
     */
    boolean isFinished();

    /**
     * Gets current winner color if game is finished, -1 otherwise.
     *
     * @return the winner color, of -1 if game is not yer finished.
     */
    int getWinnerColor();

    /**
     * Move figure of color from position A to position B using.
     *
     * @param color        the color of figure to move.
     * @param positionFrom the position where figure is located.
     * @param positionEnd  the position where figure must be moved.
     * @return true in case move is made successfully.
     */
    boolean move(int color, Point positionFrom, Point positionEnd);

    /**
     * Gets current playing board.
     *
     * @return the playing board for figures playing.
     */
    Board<T> getPlayingBoard();

}
