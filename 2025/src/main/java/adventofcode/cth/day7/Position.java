package adventofcode.cth.day7;

import lombok.Data;

@Data
public class Position {
    private final int x;
    private final int y;

    public Position(final int newX, final int newY) {
        x = newX;
        y = newY;
    }

    public Position(final Position other) {
        x = other.x;
        y = other.y;
    }

    public Position down() {
        return new Position(x, y + 1);
    }
}
