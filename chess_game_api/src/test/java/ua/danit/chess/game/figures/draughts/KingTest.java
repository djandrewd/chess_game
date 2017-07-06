package ua.danit.chess.game.figures.draughts;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.danit.chess.api.game.Figure;
import ua.danit.chess.api.game.Point;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.danit.chess.api.game.Point.create;

/**
 * Unit tests for king moves and actions.
 *
 * @author Andrey Minov
 */
@Ignore
public class KingTest {

  private Figure king;

  @Before
  public void init() {
    king = null;/*New king creation*/
  }

  @Test
  public void testMoveToSamePosition() {
    // test moving of king figure to same position
    assertTrue(king.movePath(create(1, 1), create(1, 1)).isEmpty());
  }

  @Test
  public void testMoveNevativePositions() {
    // test moving of king figure to negative number position
    assertTrue(king.movePath(create(-1, 1), create(1, 1)).isEmpty());
    assertTrue(king.movePath(create(1, -1), create(1, 1)).isEmpty());
    assertTrue(king.movePath(create(1, 1), create(-1, 1)).isEmpty());
    assertTrue(king.movePath(create(1, 1), create(1, -1)).isEmpty());

  }

  @Test
  public void testIncorrectMoves() {
    // test some king of incorrect moves.
    assertTrue(king.movePath(create(1, 1), create(1, 2)).isEmpty());
    assertTrue(king.movePath(create(3, 1), create(1, 2)).isEmpty());
    assertTrue(king.movePath(create(0, 4), create(0, 6)).isEmpty());
  }

  @Test
  public void testMovingRULD() {
    // test moving of king figure from right up corner to left down
    List<Point> move = king.movePath(create(2, 0), create(0, 2));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(0, 2)), move);

    move = king.movePath(create(1, 0), create(0, 1));
    assertFalse(move.isEmpty());
    assertEquals(singletonList(create(0, 1)), move);

    move = king.movePath(create(3, 1), create(0, 4));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 2),
        create(1, 3),
        create(0, 4)), move);
  }

  @Test
  public void testMovingRDLU() {
    // test moving of king figure from right down corner to left up
    List<Point> move = king.movePath(create(2, 2), create(0, 0));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(0, 0)), move);

    move = king.movePath(create(3, 4), create(0, 1));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 3),
        create(1, 2),
        create(0, 1)), move);
  }

  @Test
  public void testMovingLURD() {
    // test moving of king figure from left up corner to right down
    List<Point> move = king.movePath(create(0, 0), create(2, 2));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(2, 2)), move);

    move = king.movePath(create(0, 1), create(3, 4));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 2),
        create(2, 3),
        create(3, 4)), move);
  }

  @Test
  public void testMovingLDRU() {
    // test moving of king figure from left down corner to right up
    List<Point> move = king.movePath(create(0, 2), create(2, 0));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(2, 0)), move);

    move = king.movePath(create(0, 1), create(1, 0));
    assertFalse(move.isEmpty());
    assertEquals(singletonList(create(1, 0)), move);

    move = king.movePath(create(1, 3), create(4, 0));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 2),
        create(3, 1),
        create(4, 0)), move);
  }

}