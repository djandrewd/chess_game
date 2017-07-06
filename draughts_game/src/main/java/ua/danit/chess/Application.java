package ua.danit.chess;

import ua.danit.chess.api.Player;
import ua.danit.chess.api.game.Color;
import ua.danit.chess.api.game.Game;
import ua.danit.chess.api.game.Point;
import ua.danit.chess.game.dao.InMemoryGameStateDao;
import ua.danit.chess.game.figures.draughts.AbstractDraughtFigure;
import ua.danit.chess.game.rules.draughts.RussianDraughtsRules;

/**
 * Simple application starting the game.
 *
 * @author Andrey Minov
 */
public class Application {
  private static int[][] moves = {{1, 1, 2, 2, 3}, {2, 0, 5, 1, 4}, {1, 2, 3, 3, 4},
      {2, 2, 5, 4, 3}, {1, 3, 2, 2, 3}, {2, 1, 4, 3, 2}, {1, 2, 1, 1, 2}, {2, 1, 6, 0, 5},
      {1, 1, 0, 2, 1}, {2, 3, 2, 1, 0}, {1, 3, 0, 2, 1}, {2, 1, 0, 3, 2}, {1, 1, 2, 2, 3},
      {2, 3, 2, 1, 4}, {1, 0, 1, 1, 2}, {2, 4, 5, 3, 4}, {1, 1, 2, 2, 3}, {2, 1, 4, 3, 2}};


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Player player1 = new Player(0);
    Player player2 = new Player(1);

    RussianDraughtsRules rules = new RussianDraughtsRules();
    Game<AbstractDraughtFigure> game = new Game<>(1, rules, new InMemoryGameStateDao());
    game.init();

    System.out.println(game.joinGame(Color.BLACK, player1));
    System.out.println(game.joinGame(Color.WHITE, player2));

    for (int[] move : moves) {
      Player player = move[0] == 1 ? player1 : player2;
      Point from = Point.create(move[1], move[2]);
      Point to = Point.create(move[3], move[4]);
      System.out.println(game.makeMove(player, from, to));
      System.out.println(rules.getPlayingBoard());
    }
  }
}
