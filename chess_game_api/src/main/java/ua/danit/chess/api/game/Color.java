package ua.danit.chess.api.game;

/**
 * Describe possible playing colors in the game.
 *
 * @author Andrey Minov
 */
public enum Color {
  WHITE(0),
  BLACK(1);

  private int code;

  Color(int code) {
    this.code = code;
  }

  /**
   * Gets code of current color.
   *
   * @return the code of current color.
   */
  public int getCode() {
    return code;
  }
}
