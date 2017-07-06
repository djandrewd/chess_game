package ua.danit.chess.game.rules.draughts;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Figure;
import ua.danit.chess.api.game.MoveResult;
import ua.danit.chess.api.game.Point;
import ua.danit.chess.api.rules.GameRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_MOVE;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_PLAYER_TURN;
import static ua.danit.chess.api.game.GameErrors.ERROR_INCORRECT_START_POSITION;
import static ua.danit.chess.api.game.GameErrors.ERROR_MOVE_IS_NOT_ALLOWED_BY_FIGURE;
import static ua.danit.chess.api.game.GameErrors.ERROR_MOVE_MUST_BE_ONLY_BEAT_ONE;
import static ua.danit.chess.api.game.GameErrors.OK;

/**
 * Tests for russian draught rules.
 *
 * @author Andrey Minov
 */
@Ignore
public class DraughtsRulesTest {

  private GameRule<Figure> rules;

  @Before
  public void setUp() {
    rules = null; /* Create new rules instance. */
    rules.init(Color.BLACK, Color.WHITE, null);
  }

  @Test
  public void testIncorrectMoves() {
    // Test some of incorrect moves.
    MoveResult mr = rules.move(Color.WHITE, Point.create(5, 0), Point.create(4, 1));
    assertEquals(ERROR_INCORRECT_PLAYER_TURN, mr.getError());

    mr = rules.move(Color.BLACK, Point.create(0, 0), Point.create(1, 1));
    assertEquals(ERROR_INCORRECT_START_POSITION, mr.getError());

    mr = rules.move(Color.BLACK, Point.create(1, 2), Point.create(3, 3));
    assertEquals(ERROR_MOVE_IS_NOT_ALLOWED_BY_FIGURE, mr.getError());

    mr = rules.move(Color.BLACK, Point.create(0, 1), Point.create(2, 3));
    assertEquals(ERROR_INCORRECT_MOVE, mr.getError());

    mr = rules.move(Color.BLACK, Point.create(1, 2), Point.create(3, 4));
    assertEquals(ERROR_MOVE_MUST_BE_ONLY_BEAT_ONE, mr.getError());
  }

  @Test
  public void testSingleMove() {
    MoveResult mr = rules.move(Color.BLACK, Point.create(1, 2), Point.create(2, 3));
    assertEquals(OK, mr.getError());
    assertTrue(mr.getBeatedFigures().isEmpty());
    assertNull(rules.getPlayingBoard().getFigure(Point.create(1, 2)));
    assertNotNull(rules.getPlayingBoard().getFigure(Point.create(2, 3)));
    assertNull(mr.getWinner());
  }

}