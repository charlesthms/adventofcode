package adventofcode.cth.day8;

import lombok.Data;

@Data
public class Connection {
    private int a, b;
    private final double distance;

    public Connection(final int newA, final int newB, final double newDistance) {
        a = newA;
        b = newB;
        distance = newDistance;
    }
}
