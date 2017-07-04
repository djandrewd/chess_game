package ua.danit.chess;

/**
 * FigureType.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2017.06
 */
public enum FigureType {
    NORMAL((a, b, c) -> null),
    QUEEN((a, b, c) -> null);

    private FigureMoveRule rule;

    FigureType(FigureMoveRule rule) {
        this.rule = rule;
    }

    public FigureMoveRule getRule() {
        return rule;
    }
}
