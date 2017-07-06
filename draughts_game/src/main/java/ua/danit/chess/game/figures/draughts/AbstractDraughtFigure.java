package ua.danit.chess.game.figures.draughts;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.signum;
import static ua.danit.chess.api.game.Point.create;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Point;
import ua.danit.chess.game.figures.AbstractColoredFigure;

/**
 * Describes abstract figure for playing draughts. In draughts figure can move differently in case
 * figure is beating or not. Also we need to know possible moves because due to playing rules
 * if beating is possible it must be made!
 *
 * @author Andrey Minov
 */
public abstract class AbstractDraughtFigure extends AbstractColoredFigure {
  AbstractDraughtFigure(Color color) {
    super(color);
  }

  @Override
  public List<Point> movePath(Point positionFrom, Point positionTo) {
    int diffX = positionTo.getXaCoordinate() - positionFrom.getXaCoordinate();
    int diffY = positionTo.getYaCoordinate() - positionFrom.getYaCoordinate();
    if (abs(diffX) != abs(diffY)) {
      return Collections.emptyList();
    }
    int signX = (int) signum(diffX);
    int signY = (int) signum(diffY);
    if (positionTo.getXaCoordinate() + diffX * signX < 0
        || positionTo.getYaCoordinate() + diffY * signY < 0) {
      return Collections.emptyList();
    }
    List<Point> move = new ArrayList<>();
    for (int i = 1; i <= abs(diffX); i++) {
      Point point = create(
          positionFrom.getXaCoordinate() + i * signX, positionFrom.getYaCoordinate() + i * signY);
      move.add(point);
    }
    return move;
  }

  /**
   * Gets possible moves for figure at position, according to playing board size.
   *
   * @param position  the position where figure is located
   * @param boardSize the board size
   * @return the list of possible moves of current figure.
   */
  public List<List<Point>> getPossibleMoves(Point position, int boardSize) {
    // Left up
    List<Point> moveLU = new ArrayList<>();
    // Left right
    List<Point> moveLD = new ArrayList<>();
    // Right up
    List<Point> moveRU = new ArrayList<>();
    // Right down
    List<Point> moveRD = new ArrayList<>();

    List<List<Point>> moves = new ArrayList<>();
    for (int i = 1; i <= min(boardSize, getMoveLenght()); i++) {
      Point p = checkAndCreate(position.getXaCoordinate() - i, position.getYaCoordinate() - i);
      if (p != null) {
        moveLU.add(p);
      }
      p = checkAndCreate(position.getXaCoordinate() - i, position.getYaCoordinate() + i);
      if (p != null && position.getYaCoordinate() < boardSize) {
        moveLD.add(p);
      }
      p = checkAndCreate(position.getXaCoordinate() + i, position.getYaCoordinate() - i);
      if (p != null && position.getXaCoordinate() < boardSize) {
        moveRU.add(p);
      }
      p = checkAndCreate(position.getXaCoordinate() + i, position.getYaCoordinate() + i);
      if (p != null && position.getXaCoordinate() < boardSize
          && position.getYaCoordinate() < boardSize) {
        moveRD.add(p);
      }
    }
    if (!moveLU.isEmpty()) {
      moves.add(moveLU);
    }
    if (!moveLD.isEmpty()) {
      moves.add(moveLD);
    }
    if (!moveRU.isEmpty()) {
      moves.add(moveRU);
    }
    if (!moveRD.isEmpty()) {
      moves.add(moveRD);
    }
    return moves;
  }


  /**
   * Check is this move must be made only in case when figure beat other figure.
   *
   * @param move the move
   * @return true only if this move can be make in case of beating other figure, false otherwise.
   */
  public abstract boolean isOnlyBeatMove(List<Point> move);

  /**
   * Get maximum lenght of points that figure can move during game.
   */
  protected int getMoveLenght() {
    return Integer.MAX_VALUE;
  }

  private Point checkAndCreate(int x, int y) {
    if (x < 0 || y < 0) {
      return null;
    }
    return create(x, y);
  }
}
