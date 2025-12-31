package adventofcode.cth.day4;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.ArrayList;
import java.util.List;

public class Day4 implements Solver<Integer, Integer> {
    private final String[] input;

    public Day4() {
        input = InputReader.readResourceToString("day4.txt").split("\n");
    }

    private List<List<Integer>> parseInput() {
        final List<List<Integer>> processedInput = new ArrayList<>();
        for (final String line : input) {
            char[] chars = line.toCharArray();
            List<Integer> lineList = new ArrayList<>();
            for (final char aChar : chars) {
                if (aChar == '@') {
                    lineList.add(1);
                } else {
                    lineList.add(0);
                }
            }
            processedInput.add(lineList);
        }
        return processedInput;
    }

    private boolean isAccessible(final List<List<Integer>> input, final int row, final int col) {
        int neighbors = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if ((i != row || j != col) && i >= 0 && j >= 0 && i < input.size() && j < input.getFirst().size()) {
                    if (input.get(i).get(j) == 1) {
                        neighbors++;
                    }
                }
            }
        }
        return neighbors < 4;
    }

    private int removeRolls(final List<List<Integer>> input) {
        int accessibleCount = 0;

        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.getFirst().size(); col++) {
                if (input.get(row).get(col) == 1 && isAccessible(input, row, col)) {
                    accessibleCount++;
                    input.get(row).set(col, 0); // roll removed
                }
            }
        }

        return accessibleCount;
    }

    @Override
    public Integer solvePart1() {
        final List<List<Integer>> parsedInput = parseInput();
        int accessibleCount = 0;

        for (int row = 0; row < parsedInput.size(); row++) {
            for (int col = 0; col < parsedInput.getFirst().size(); col++) {
                if (parsedInput.get(row).get(col) == 1 && isAccessible(parsedInput, row, col)) {
                    accessibleCount++;
                }
            }
        }

        return accessibleCount;
    }

    @Override
    public Integer solvePart2() {
        final List<List<Integer>> parsedInput = parseInput();
        int removedRolls;
        int totalAccessibleCount = 0;

        while ((removedRolls = removeRolls(parsedInput)) > 0) {
            totalAccessibleCount += removedRolls;
        }

        return totalAccessibleCount;
    }
}
