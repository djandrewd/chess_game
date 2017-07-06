package ua.danit.chess.api.dao;

import java.util.Map;

import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Figure;
import ua.danit.chess.api.game.Point;

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

  public Map<Long, Color> getUserSessions() {
    return userSessions;
  }

  public void setUserSessions(Map<Long, Color> userSessions) {
    this.userSessions = userSessions;
  }

  public BoardState getBoardState() {
    return boardState;
  }

  public void setBoardState(BoardState boardState) {
    this.boardState = boardState;
  }

  /**
   * Gets the unique identifier for current game.
   *
   * @return the the unique identifier for current game.
   */
  public long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "GameState{" + "userSessions=" + userSessions + ", boardState=" + boardState + '}';
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

    public Color getWinner() {
      return winner;
    }

    public Color getCurrentTurn() {
      return currentTurn;
    }

    public Map<Point, Figure> getBoard() {
      return board;
    }

    @Override
    public String toString() {
      return "BoardState{" + "winner=" + winner + ", currentTurn=" + currentTurn + ", board="
             + board + '}';
    }
  }
}
