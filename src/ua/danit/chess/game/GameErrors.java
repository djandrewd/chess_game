package ua.danit.chess.game;

/**
 * Enumeration for error occurred during game play.
 *
 * @author Andrey Minov
 */
public enum GameErrors {
    /**
     * Value indicating success call without any errors.
     */
    OK(0),
    /**
     * Value indicating that this move is not allowed.
     */
    ERROR_MOVE_NOT_ALLOWED(1),
    /**
     * Value indicating that this move is not allowed because current game is finished and winner is defined.
     */
    ERROR_FINISHED(2),
    /**
     * Value indicating that user already playing on this game.
     */
    ERROR_ALREADY_PLAYING(3),
    /**
     * Value indicating  incorrect color of player selected.
     */
    ERROR_INCORRECT_COLOR(4);

    private int code;

    GameErrors(int code) {
        this.code = code;
    }

    /**
     * Returns numeric code value for current error.
     *
     * @return numeric code value for current error.
     */
    public int getCode() {
        return code;
    }
}
