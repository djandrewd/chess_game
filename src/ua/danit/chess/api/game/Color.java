package ua.danit.chess.api.game;

/**
 * Describe possible playing colors in the game.
 *
 * @author Andrey Minov
 */
public enum Color {
    /**
     * White color.
     */
    WHITE(0), /**
     * Black color.
     */
    BLACK(1);

    private int code;

    Color(int code) {
        this.code = code;
    }

    /**
     * Gets code of current color..
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }
}
