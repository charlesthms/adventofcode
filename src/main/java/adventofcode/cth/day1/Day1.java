package adventofcode.cth.day1;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;
import lombok.Data;

/**
 * Solver for Day 1 challenge.
 * <a href="https://adventofcode.com/2024/day/1">Read</a>
 */
public class Day1 implements Solver<Integer, Integer> {

    private final String[] input;

    public Day1() {
        input = InputReader.readResourceToString("day1.txt").split("\n");
    }

    private void rightTurn(final TurnResult result, final int rotationCount) {
        final int oldPos = result.position;
        final int normalized = rotationCount % 100;
        final int newPos = (oldPos + normalized) % 100;

        // Check if we passed position 0
        int zeroCrossings = rotationCount / 100;

        // Also check final partial rotation:
        // If oldPos + normalized >= 100 we crossed zero
        if (oldPos + normalized >= 100 && newPos != 0) {
            zeroCrossings++;
        }

        if (zeroCrossings > 0) {
            result.setPassword(result.getPassword() + zeroCrossings);
        }

        result.setPosition(newPos);
    }

    private void leftTurn(final TurnResult result, final int rotationCount) {
        final int oldPos = result.position;
        final int normalized = rotationCount % 100;
        final int newPos = (oldPos + (100 - normalized)) % 100;

        // Check if we passed position 0
        int zeroCrossings = rotationCount / 100;
        if (oldPos < normalized && oldPos != 0) {
            zeroCrossings++;
        }

        if (zeroCrossings > 0) {
            result.setPassword(result.getPassword() + zeroCrossings);
        }

        result.position = newPos;
    }


    @Override
    public Integer solvePart1() {
        return 0;
    }

    @Override
    public Integer solvePart2() {
        final TurnResult result = new TurnResult(50, 0);
        int step;

        for (final String elem : input) {
            step = Integer.parseInt(elem.substring(1));

            if (elem.charAt(0) == 'R') {
                rightTurn(result, step);
            } else if (elem.charAt(0) == 'L') {
                leftTurn(result, step);
            } else {
                System.out.println("Unknown direction " + elem.charAt(0));
                continue;
            }

            if (result.position == 0) {
                result.setPassword(result.getPassword() + 1);
            }
        }

        return result.getPassword();
    }

    @Data
    private static class TurnResult {
        private int position;
        private int password;

        public TurnResult(final int newPosition, final int newPassword) {
            position = newPosition;
            password = newPassword;
        }
    }
}
