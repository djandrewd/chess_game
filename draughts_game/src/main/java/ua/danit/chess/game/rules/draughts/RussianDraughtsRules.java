package ua.danit.chess.game.rules.draughts;

import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_END_POSITION;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_MOVE;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_PLAYER_TURN;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_START_POSITION;
import static ua.danit.chess.api.game.GameErrors.ERROR_MOVE_IS_NOT_ALLOWED_BY_FIGURE;
import static ua.danit.chess.api.game.GameErrors.ERROR_MOVE_MUST_BE_ONLY_BEAT_ONE;
import static ua.danit.chess.api.game.GameErrors.OK;
import static ua.danit.chess.api.game.Point.create;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ua.danit.chess.api.dao.GameState;
import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Figure;
import ua.danit.chess.api.game.GameErrors;
import ua.danit.chess.api.game.MoveResult;
import ua.danit.chess.api.game.Point;
import ua.danit.chess.api.rules.Board;
import ua.danit.chess.api.rules.GameRule;
import ua.danit.chess.game.figures.draughts.AbstractDraughtFigure;
import ua.danit.chess.game.figures.draughts.King;
import ua.danit.chess.game.figures.draughts.Man;

/**
 * Describe rules for draughts. Any actions that must be made for user to win the game.
 *
 * @author Andrey Minov
 */
public class RussianDraughtsRules implements GameRule<AbstractDraughtFigure> {

  private static final AtomicLong CNTR = new AtomicLong();
  private static final int BOARD_SIZE = 8;
  private static final int COLOR_SIZE = BOARD_SIZE / 2;
  private static final int FIGURES_SIZE = 12;
  private static final int MAX_CHECK_MOVE = 2;

  private Color winnerColor;
  private Color currentColor;
  private Color pendingColor;

  private EnumMap<Color, Integer> kingLines;
  private Board<AbstractDraughtFigure> board;

  @Override
  public void init(Color colorA, Color colorB, GameState gameState) {
    kingLines = new EnumMap<>(Color.class);
    kingLines.put(colorA, BOARD_SIZE - 1);
    kingLines.put(colorB, 0);
    board = new Board<>(BOARD_SIZE);

    if (gameState != null) {
      GameState.BoardState boardState = gameState.getBoardState();
      currentColor = boardState.getCurrentTurn();
      pendingColor = currentColor == colorA ? colorB : colorA;
      winnerColor = boardState.getWinner();
      boardState.getBoard()
                .forEach((k, v) -> board.placeFigure(k, (AbstractDraughtFigure) v));
    }
    else {
      currentColor = colorA;
      pendingColor = colorB;
      for (int i = 0; i < FIGURES_SIZE / COLOR_SIZE; i++) {
        for (int j = 0; j < COLOR_SIZE; j++) {
          Man man = new Man(colorA);
          board.placeFigure(create(j * 2 + (i + 1) % 2, i), man);

          man = new Man(colorB);
          board.placeFigure(create(BOARD_SIZE - 1 - j * 2 - (i + 1) % 2, BOARD_SIZE - 1 - i), man);
        }
      }
    }
  }

  @Override
  public MoveResult move(Color color, Point positionFrom, Point positionEnd) {
    if (color != currentColor) {
      return new MoveResult(ERROR_INCORRECT_PLAYER_TURN);
    }
    GameErrors error = canMakeMove(color, positionFrom, positionEnd);
    if (error != OK) {
      return new MoveResult(error);
    }

    AbstractDraughtFigure figure = board.getFigure(positionFrom);
    List<Point> move = figure.movePath(positionFrom, positionEnd);
    Map<Point, AbstractDraughtFigure> beatedFigures = getBeatedFigures(move);

    // Make actual move
    board.removeFigure(positionFrom);
    // change figure to king when entity move to current line.
    if (kingLines.get(currentColor) == positionEnd.getYaCoordinate()) {
      figure = new King(color);
    }
    board.placeFigure(positionEnd, figure);

    // remove beated figures from the boards
    beatedFigures.keySet()
                 .forEach(board::removeFigure);

    // Check if player win the game.
    if (tryFinishGame()) {
      winnerColor = color;
    }

    // Change move and give it to another player.
    changeMove();

    long moveId = CNTR.incrementAndGet();
    Map<Point, Figure> figures = new HashMap<>(beatedFigures);
    return new MoveResult(OK, moveId, figures, currentColor, winnerColor);
  }

  @Override
  public MoveResult undoMove(long moveId) {
    throw new UnsupportedOperationException("Not supported yet!");
  }

  @Override
  public MoveResult redoMove(long moveId) {
    throw new UnsupportedOperationException("Not supported yet!");
  }

  @Override
  public boolean isFinished() {
    return winnerColor != null;
  }

  @Override
  public Color getWinnerColor() {
    return winnerColor;
  }

  @Override
  public Color getCurrentTurn() {
    return currentColor;
  }

  @Override
  public Board<AbstractDraughtFigure> getPlayingBoard() {
    return board;
  }

  private GameErrors canMakeMove(Color color, Point positionFrom, Point positionEnd) {
    // Check figure found
    AbstractDraughtFigure figure = board.getFigure(positionFrom);
    if (figure == null || figure.getColor() != color) {
      return ERROR_INCORRECT_START_POSITION;
    }
    // Do not allow to place figure where some other figure is located.
    if (board.getFigure(positionEnd) != null) {
      return ERROR_INCORRECT_END_POSITION;
    }
    List<Point> move = figure.movePath(positionFrom, positionEnd);
    // When move is not allowed by figure rules.
    if (move.isEmpty()) {
      return ERROR_MOVE_IS_NOT_ALLOWED_BY_FIGURE;
    }
    // If we pass figures of our type.
    if (move.stream()
            .map(board::getFigure)
            .anyMatch(v -> v != null && v.getColor() == color)) {
      return ERROR_INCORRECT_MOVE;
    }
    Map<Point, AbstractDraughtFigure> beatedFigures = getBeatedFigures(move);
    boolean onlyBeatMove = figure.isOnlyBeatMove(move);
    // check that we beat something during this move, because this move was made as beat.
    if (onlyBeatMove && beatedFigures.isEmpty()) {
      return ERROR_MOVE_MUST_BE_ONLY_BEAT_ONE;
    }
    return OK;
  }

  private Map<Point, AbstractDraughtFigure> getBeatedFigures(List<Point> move) {
    return move.stream()
               .filter(v -> board.getFigure(v) != null && board.getFigure(v)
                                                               .getColor() != currentColor)
               .collect(Collectors.toMap(k -> k, k -> board.getFigure(k)));
  }

  private void changeMove() {
    Color tmp = currentColor;
    currentColor = pendingColor;
    pendingColor = tmp;
  }

  private boolean tryFinishGame() {
    // When one player beats all other figures.
    if (board.figuresStream()
             .allMatch(v -> v.getColor() == currentColor)) {
      return true;
    }

    // When other player has no place to put its figure.
    return board.positionsWithFiguresStream()
                .noneMatch(v -> {
                  AbstractDraughtFigure figure = board.getFigure(v);
                  if (figure.getColor() == currentColor) {
                    return true;
                  }
                  List<List<Point>> moves = figure.getPossibleMoves(v, BOARD_SIZE);
                  if (moves.isEmpty()) {
                    return true;
                  }
                  outer:
                  for (List<Point> move : moves) {
                    for (int i = 0; i < MAX_CHECK_MOVE; i++) {
                      if (i >= move.size()) {
                        continue outer;
                      }
                      if (canMakeMove(figure.getColor(), v, move.get(i)) == OK) {
                        return true;
                      }
                    }
                  }
                  return false;
                });
  }
}
