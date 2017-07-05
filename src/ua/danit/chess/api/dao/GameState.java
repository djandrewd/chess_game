package ua.danit.chess.api.dao;

import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Point;
import ua.danit.chess.api.game.Figure;

import java.util.Map;

/**
 * State of current game to save it into database.
 *
 * @author Andrey Minov
 */
public class GameState {

    private long id;
    private Map<Long, Color> userSessions;
    private BoardState boardState;

    /**
     * Instantiates a new Game state.
     *
     * @param id           the unique identifier for current game.
     * @param userSessions the user sessions
     * @param boardState   the board state
     */
    public GameState(long id, Map<Long, Color> userSessions, BoardState boardState) {
        this.id = id;
        this.userSessions = userSessions;
        this.boardState = boardState;
    }

    /**
     * Gets user sessions.
     *
     * @return the user sessions
     */
    public Map<Long, Color> getUserSessions() {
        return userSessions;
    }

    /**
     * Sets user sessions.
     *
     * @param userSessions the user sessions
     */
    public void setUserSessions(Map<Long, Color> userSessions) {
        this.userSessions = userSessions;
    }

    /**
     * Gets board state.
     *
     * @return the board state
     */
    public BoardState getBoardState() {
        return boardState;
    }

    /**
     * Gets the unique identifier for current game.
     *
     * @return the the unique identifier for current game.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets board state.
     *
     * @param boardState the board state
     */
    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "userSessions=" + userSessions +
                ", boardState=" + boardState +
                '}';
    }

    /**
     * The type Board state.
     */
    public static class BoardState {
        private Color winner;
        private Color currentTurn;
        private Map<Point, Figure> board;

        /**
         * Instantiates a new Board state.
         *
         * @param winner      the winner
         * @param currentTurn the current turn
         * @param board       the board
         */
        public BoardState(Color winner, Color currentTurn, Map<Point, Figure> board) {
            this.winner = winner;
            this.currentTurn = currentTurn;
            this.board = board;
        }

        /**
         * Gets winner.
         *
         * @return the winner
         */
        public Color getWinner() {
            return winner;
        }

        /**
         * Gets current turn.
         *
         * @return the current turn
         */
        public Color getCurrentTurn() {
            return currentTurn;
        }

        /**
         * Gets board.
         *
         * @return the board
         */
        public Map<Point, Figure> getBoard() {
            return board;
        }

        @Override
        public String toString() {
            return "BoardState{" +
                    "winner=" + winner +
                    ", currentTurn=" + currentTurn +
                    ", board=" + board +
                    '}';
        }
    }
}
