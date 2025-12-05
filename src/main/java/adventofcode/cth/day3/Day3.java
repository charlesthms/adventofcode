package adventofcode.cth.day3;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.ArrayList;
import java.util.List;

public class Day3 implements Solver<Integer> {

    private final String[] input;

    public Day3() {
        input = InputReader.readResourceToString("day3.txt").split("\n");
    }

    private List<Integer> toList(String line) {
        final List<Integer> result = new ArrayList<>();
        for (final char c : line.toCharArray()) {
            result.add(c - '0');
        }
        return result;
    }

    @Override
    public Integer solve() {
        final List<Integer> results = new ArrayList<>();
        for (final String line : input) {
            List<Integer> lineLst = toList(line);
            int max = 0;
            // Find all the pairs
            for (int i = 0; i < lineLst.size() - 1; i++) {
                for (int j = i + 1; j < lineLst.size(); j++) {
                    int localMax = lineLst.get(i) * 10 + lineLst.get(j);
                    if (localMax > max) {
                        max = localMax;
                    }
                }
            }

            results.add(max);
        }
        return results.stream().reduce(0, Integer::sum);
    }
}
