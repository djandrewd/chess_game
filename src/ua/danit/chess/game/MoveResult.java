package ua.danit.chess.game;

/**
 * This entity holds result information about move.
 *
 * @author Andrey Minov
 */
public class MoveResult {
    private int error;
    private boolean winMove;
    private int winner;

    /**
     * Instantiates a new Move result.
     *
     * @param error the error
     */
    public MoveResult(int error) {
        this.error = error;
        this.winner = -1;
    }

    /**
     * Instantiates a new Move result.
     *
     * @param error   the error result for this move. Can be 0 in case move is successful.
     * @param winMove true in case during this move winner is defined.
     * @param winner  current winner if winMove is true.
     */
    public MoveResult(int error, boolean winMove, int winner) {
        this.error = error;
        this.winMove = winMove;
        this.winner = winner;
    }

    /**
     * Gets the error result for this move. Can be 0 in case move is successful..
     *
     * @return the error result for this move. Can be 0 in case move is successful.
     */
    public int getError() {
        return error;
    }

    /**
     * Is this move is winning move.
     *
     * @return true in case during this move winner is defined.
     */
    public boolean isWinMove() {
        return winMove;
    }

    /**
     * Gets winner in case this move is winning one.
     *
     * @return the winner in case this move is winning one, -1 otherwise
     */
    public int getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "MoveResult{" +
                "error=" + error +
                ", winMove=" + winMove +
                ", winner=" + winner +
                '}';
    }
}
