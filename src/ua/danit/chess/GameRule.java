package ua.danit.chess;

/**
 * GameRule.
 * <p/>
 * Common rules for all two players game
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2017.06
 */
public interface GameRule {

    /**
     * Init game for two with colors describing each members.
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
     */
    void move(int color, int positionFrom, int positionEnd);
}
