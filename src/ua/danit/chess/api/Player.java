package ua.danit.chess.api;

/**
 * Holds information about player, attributes and actions.
 *
 * @author Andrey Minov
 */
public class Player {
    /**
     * Unique identified for player.
     */
    private long id;

    /**
     * Instantiates a new Player.
     *
     * @param id the unique identified for player.
     */
    public Player(long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }
}
