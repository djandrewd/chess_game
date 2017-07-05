package ua.danit.chess.api.game;

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
    ERROR_INCORRECT_COLOR(4),
    /**
     * Value indicating incorrect player turn.
     */
    ERROR_INCORRECT_PLAYER_TURN(5),
    /**
     * Value indicating incorrect start position of figure.
     */
    ERROR_INCORRECT_START_POSITION(6),
    /**
     * Value indicating incorrect end position of figure.
     */
    ERROR_INCORRECT_END_POSITION(7),
    /**
     * Value indicating move is not allowed by figure.
     */
    ERROR_MOVE_IS_NOT_ALLOWED_BY_FIGURE(8),
    /**
     * Value indicating when move must be only beat one.
     */
    ERROR_MOVE_MUST_BE_ONLY_BEAT_ONE(9),
    /**
     * Error incorrect general move error.
     */
    ERROR_INCORRECT_MOVE(10),
    /**
     * Value indicating that user not playing on this table.
     */
    ERROR_NOT_PLAYING(11),
    /**
     * Value indicating that color was already selected by another user.
     */
    ERROR_COLOR_ALREADY_SELECTED(12);

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
