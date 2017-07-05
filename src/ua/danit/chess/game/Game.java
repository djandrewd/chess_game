package ua.danit.chess.game;

import ua.danit.chess.game.rules.GameRule;
import ua.danit.chess.players.Player;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static ua.danit.chess.game.GameErrors.ERROR_ALREADY_PLAYING;
import static ua.danit.chess.game.GameErrors.ERROR_FINISHED;
import static ua.danit.chess.game.GameErrors.ERROR_INCORRECT_COLOR;
import static ua.danit.chess.game.GameErrors.ERROR_MOVE_NOT_ALLOWED;
import static ua.danit.chess.game.GameErrors.OK;

/**
 * Describes game process and interactions.
 * Handler for commands from user and holder of players.
 *
 * @author Andrey Minov
 */
public class Game {
    // Be default we allow only two players with colors 0 and 1 playing on the table.
    private static final List<Integer> COLORS = asList(0, 1);

    private GameRule gameRule;
    private Map<Long, Integer> userSessions;

    /**
     * Instantiates a new Game.
     *
     * @param gameRule the rule for current game.
     */
    public Game(GameRule gameRule) {
        this.gameRule = gameRule;
    }

    public void init() {
        gameRule.init(COLORS.get(0), COLORS.get(1));
    }

    public int joinGame(int color, Player player) {
        if (userSessions.containsKey(player.getId())) {
            return ERROR_ALREADY_PLAYING.getCode();
        }
        if (!COLORS.contains(color)) {
            return ERROR_INCORRECT_COLOR.getCode();
        }
        userSessions.put(player.getId(), color);
        return OK.getCode();
    }

    public MoveResult makeMove(int color, Point positionFrom, Point positionTo) {
        if (gameRule.isFinished()) {
            return new MoveResult(ERROR_FINISHED.getCode());
        }
        if (!gameRule.move(color, positionFrom, positionTo)) {
            return new MoveResult(ERROR_MOVE_NOT_ALLOWED.getCode());
        }
        return new MoveResult(OK.getCode(), gameRule.isFinished(), gameRule.getWinnerColor());
    }
}
