package ua.danit.chess.game;

import ua.danit.chess.game.dao.GameState;
import ua.danit.chess.game.dao.GameStateDao;
import ua.danit.chess.game.figures.Figure;
import ua.danit.chess.game.rules.Board;
import ua.danit.chess.game.rules.GameRule;
import ua.danit.chess.players.Player;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.danit.chess.game.GameErrors.ERROR_ALREADY_PLAYING;
import static ua.danit.chess.game.GameErrors.ERROR_FINISHED;
import static ua.danit.chess.game.GameErrors.ERROR_INCORRECT_COLOR;
import static ua.danit.chess.game.GameErrors.ERROR_MOVE_NOT_ALLOWED;
import static ua.danit.chess.game.GameErrors.ERROR_NOT_PLAYING;
import static ua.danit.chess.game.GameErrors.OK;

/**
 * Describes game process and interactions.
 * Handler for commands from user and holder of players.
 *
 * @param <T> the type of figures played in this game.
 * @author Andrey Minov
 */
public class Game<T extends Figure> {
    // Be default we allow only two players with colors 0 and 1 playing on the table.
    private static final Set<Color> COLORS = EnumSet.of(Color.WHITE, Color.BLACK);

    private long id;
    private GameRule<T> gameRule;
    private Map<Long, Color> userSessions;
    private GameStateDao gameStateDao;

    /**
     * Instantiates a new Game.
     *
     * @param id           the unique identifier for current game.
     * @param gameRule     the rule for current game.
     * @param gameStateDao data access object for game.
     */
    public Game(long id, GameRule<T> gameRule, GameStateDao gameStateDao) {
        this.id = id;
        this.gameRule = gameRule;
        this.gameStateDao = gameStateDao;
        this.userSessions = new HashMap<>();
    }


    /**
     * Init current game.
     */
    public void init() {
        GameState gameState = gameStateDao.get(id);
        gameRule.init(Color.BLACK, Color.WHITE, gameState);
    }

    /**
     * Join game with player.
     *
     * @param color  the color of player
     * @param player the player to join the table.
     * @return the one of {@link GameErrors} as result of execution.
     */
    public GameErrors joinGame(Color color, Player player) {
        if (userSessions.containsKey(player.getId())) {
            return ERROR_ALREADY_PLAYING;
        }
        if (!COLORS.contains(color)) {
            return ERROR_INCORRECT_COLOR;
        }
        userSessions.put(player.getId(), color);
        return OK;
    }

    /**
     * Make move move result.
     *
     * @param player       the player which makes move.
     * @param positionFrom the position from
     * @param positionTo   the position to
     * @return the move result
     */
    public MoveResult makeMove(Player player, Point positionFrom, Point positionTo) {
        if (userSessions.size() < COLORS.size()) {
            return new MoveResult(ERROR_MOVE_NOT_ALLOWED);
        }
        if (gameRule.isFinished()) {
            return new MoveResult(ERROR_FINISHED);
        }
        Color color = userSessions.get(player.getId());
        if (color == null) {
            return new MoveResult(ERROR_NOT_PLAYING);
        }

        MoveResult move = gameRule.move(color, positionFrom, positionTo);
        saveState();
        return move;
    }


    /**
     * Gets unique identifier for current game.
     *
     * @return the iunique identifier for current game.
     */
    public long getId() {
        return id;
    }

    private void saveState() {
        Board<T> playingBoard = gameRule.getPlayingBoard();
        Map<Point, Figure> figures = playingBoard.positionsWithFiguresStream().collect(Collectors.toMap(k -> k,
                playingBoard::getFigure));
        GameState.BoardState boardState = new GameState.BoardState(gameRule.getWinnerColor(), gameRule.getCurrentTurn(), figures);
        gameStateDao.save(new GameState(id, userSessions, boardState));
    }
}
