package ua.danit.chess.game.dao;

import ua.danit.chess.api.dao.GameState;
import ua.danit.chess.api.dao.GameStateDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In memory instance of game state.
 *
 * @author Andrey Minov
 */
public class InMemoryGameStateDao implements GameStateDao {

    private Map<Long, GameState> stateMap = new ConcurrentHashMap<>();

    @Override
    public void save(GameState gameState) {
        stateMap.put(gameState.getId(), gameState);
    }

    @Override
    public GameState get(long id) {
        return stateMap.get(id);
    }
}
