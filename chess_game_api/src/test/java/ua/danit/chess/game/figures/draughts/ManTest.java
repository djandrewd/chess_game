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
 * Unit tests for man moves and actions.
 *
 * @author Andrey Minov
 */
@Ignore
public class ManTest {

  private Figure man;

  @Before
  public void init() {
    man = null;/*New man creation.*/
  }

  @Test
  public void testMoveToSamePosition() {
    // test moving of man figure to same position
    assertTrue(man.movePath(create(1, 1), create(1, 1)).isEmpty());
  }

  @Test
  public void testMoveNevativePositions() {
    // test moving of man figure to negative number position
    assertTrue(man.movePath(create(-1, 1), create(1, 1)).isEmpty());
    assertTrue(man.movePath(create(1, -1), create(1, 1)).isEmpty());
    assertTrue(man.movePath(create(1, 1), create(-1, 1)).isEmpty());
    assertTrue(man.movePath(create(1, 1), create(1, -1)).isEmpty());

  }

  @Test
  public void testIncorrectMoves() {
    // test some man of incorrect moves.
    assertTrue(man.movePath(create(1, 1), create(1, 2)).isEmpty());
    assertTrue(man.movePath(create(3, 1), create(1, 2)).isEmpty());
    assertTrue(man.movePath(create(0, 4), create(0, 6)).isEmpty());
    // more then allowed
    assertTrue(man.movePath(create(1, 1), create(4, 4)).isEmpty());
    assertTrue(man.movePath(create(1, 2), create(4, 5)).isEmpty());
  }

  @Test
  public void testMovingRULD() {
    // test moving of man figure from right up corner to left down
    List<Point> move = man.movePath(create(2, 0), create(0, 2));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(0, 2)), move);

    move = man.movePath(create(1, 0), create(0, 1));
    assertFalse(move.isEmpty());
    assertEquals(singletonList(create(0, 1)), move);

    move = man.movePath(create(3, 1), create(1, 3));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 2),
        create(1, 3)), move);
  }

  @Test
  public void testMovingRDLU() {
    // test moving of man figure from right down corner to left up
    List<Point> move = man.movePath(create(2, 2), create(0, 0));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(0, 0)), move);

    move = man.movePath(create(3, 4), create(1, 2));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 3),
        create(1, 2)), move);
  }

  @Test
  public void testMovingLURD() {
    // test moving of man figure from left up corner to right down
    List<Point> move = man.movePath(create(0, 0), create(2, 2));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(2, 2)), move);

    move = man.movePath(create(0, 1), create(2, 3));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 2),
        create(2, 3)), move);
  }

  @Test
  public void testMovingLDRU() {
    // test moving of man figure from left down corner to right up
    List<Point> move = man.movePath(create(0, 2), create(2, 0));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(1, 1), create(2, 0)), move);

    move = man.movePath(create(0, 1), create(1, 0));
    assertFalse(move.isEmpty());
    assertEquals(singletonList(create(1, 0)), move);

    move = man.movePath(create(1, 3), create(3, 1));
    assertFalse(move.isEmpty());
    assertEquals(asList(create(2, 2),
        create(3, 1)), move);
  }

}