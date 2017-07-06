package ua.danit.chess.api.dao;

/**
 * Data access object for storing and restoring state of current game.
 *
 * @author Andrey Minov
 */
public interface GameStateDao {
  /**
   * Save current game state.
   *
   * @param gameState the game state
   */
  void save(GameState gameState);

  /**
   * Get saved game state.
   *
   * @param id the unique game identifier
   * @return the game state saved or null if nothing found.
   */
  GameState get(long id);
}
