package ua.danit.chess.figures;

/**
 * Presented figure that has color inside.
 *
 * @author Andrey Minov
 */
public abstract class AbstractColoredFigure implements Figure {

    /**
     * Color of concrete figure.
     */
    private int color;

    /**
     * Instantiates a new Abstract colored figure.
     *
     * @param color the color, might be 0 or 1 when using standart chess rules.
     */
    public AbstractColoredFigure(int color) {
        this.color = color;
    }

    /**
     * Gets color of current figure..
     *
     * @return the color of concrete figure
     */
    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractColoredFigure that = (AbstractColoredFigure) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return color;
    }

    @Override
    public String toString() {
        return "color=" + color;
    }
}
