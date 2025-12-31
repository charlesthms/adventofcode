package adventofcode.cth.day7;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.*;

public class Day7 implements Solver<Integer, Long> {

    private final String[] input;

    public Day7() {
        input = InputReader.readResourceToString("day7.txt").split("\n");
    }

    private int getEntryPoint(final String line) {
        return line.indexOf("S");
    }

    private Position moveDown(final Position start) {
        Position pos = new Position(start);
        while (pos.getY() < input.length - 1 && input[pos.getY()].charAt(pos.getX()) != '^') {
            pos = pos.down();
        }
        return pos;
    }

    private boolean isInBounds(final Position pos) {
        return pos.getX() >= 0 && pos.getX() < input[0].length() && pos.getY() >= 0 && pos.getY() < input.length;
    }

    /**
     * Performs a breadth-first search to count the number of beam splits.
     *
     * @return The number of beam splits
     */
    private int bfs() {
        final Set<Position> splits = new HashSet<>();
        final Queue<Position> beams = new LinkedList<>();

        beams.add(new Position(getEntryPoint(input[0]), 0));

        while (!beams.isEmpty()) {
            Position newPos = moveDown(beams.poll());

            if (input[newPos.getY()].charAt(newPos.getX()) != '^') {
                continue;
            }

            if (splits.add(newPos)) {
                // Split the beam if not already split and within bounds
                //System.out.println("Beam split at: " + newPos);
                if (newPos.getX() + 1 < input[0].length()) {
                    beams.add(new Position(newPos.getX() + 1, newPos.getY()));
                }
                if (newPos.getX() - 1 >= 0) {
                    beams.add(new Position(newPos.getX() - 1, newPos.getY()));
                }
            }
        }
        return splits.size();
    }

    /**
     * Performs a depth-first search to count the number of possible paths
     *
     * @return The number of beam splits using depth-first search
     */
    private long dfs(final Position current, final Map<Position, Long> computed) {

        Position down = moveDown(current);
        if (computed.containsKey(down)) {
            return computed.get(down);
        }

        if (down.getY() >= input.length || input[down.getY()].charAt(down.getX()) != '^') {
            return 1L;
        }

        long total = 0;
        Position left = new Position(down.getX() - 1, down.getY());
        Position right = new Position(down.getX() + 1, down.getY());

        if (isInBounds(left)) {
            total += dfs(left, computed);
        }
        if (isInBounds(right)) {
            total += dfs(right, computed);
        }

        computed.put(down, total);
        return total;
    }


    @Override
    public Integer solvePart1() {
        return bfs();
    }

    @Override
    public Long solvePart2() {
        final Position start = new Position(getEntryPoint(input[0]), 0);
        return dfs(start, new HashMap<>());
    }
}
