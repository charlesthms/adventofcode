package adventofcode.cth.day3;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.*;

public class Day3 implements Solver<Long, Long> {

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

    public long kCombinations(final List<Integer> arr, final int k) {
        int n = arr.size();
        int toRemove = n - k;
        final Deque<Integer> stack = new ArrayDeque<>();

        for (int digit : arr) {
            while (!stack.isEmpty() && toRemove > 0 && stack.peekLast() < digit) {
                stack.removeLast();
                toRemove--;
            }
            stack.addLast(digit);
        }

        while (stack.size() > k) {
            stack.removeLast();
        }

        long number = 0;
        for (int d : stack) {
            number = number * 10 + d;
        }
        return number;
    }



    @Override
    public Long solvePart1() {
        final List<Long> results = new ArrayList<>();
        for (final String line : input) {
            List<Integer> lineLst = toList(line);
            results.add(kCombinations(lineLst, 2));
        }
        return results.stream().reduce((long) 0, Long::sum);
    }

    @Override
    public Long solvePart2() {
        final List<Long> results = new ArrayList<>();
        for (final String line : input) {
            List<Integer> lineLst = toList(line);
            results.add(kCombinations(lineLst, 12));
        }
        return results.stream().reduce((long) 0, Long::sum);
    }
}
