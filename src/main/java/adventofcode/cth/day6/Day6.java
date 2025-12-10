package adventofcode.cth.day6;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 implements Solver<Long, Long> {

    private List<Problem<Long>> problems;
    private List<String> operationsPart2;
    private List<List<String>> problemsStr;

    public Day6() {
        final String[] input = InputReader.readResourceToString("day6.txt").split("\n");

        part1PreProcess(input);
        part2PreProcess(input);
    }

    private void part2PreProcess(final String[] input) {
        final List<String> operations = new ArrayList<>();
        final Pattern pattern = Pattern.compile("([+*])\\s+");
        final Matcher matcher = pattern.matcher(input[input.length - 1]);

        while (matcher.find()) {
            String match = matcher.group();
            operations.add(match.substring(0, match.length() - 1));
        }

        operations.set(operations.size() - 1, operations.getLast() + " ");


        final List<List<String>> problemsStr = new ArrayList<>();

        int offset = 0;
        for (final String operation : operations) {
            int length = operation.length();
            problemsStr.add(getColumn(input, offset, length));
            offset += length + 1; // +1 for the space
        }

        this.problemsStr = problemsStr;

        operations.forEach(String::trim);
        this.operationsPart2 = operations;

    }

    private List<String> getColumn(final String[] input, final int offset, final int length) {
        final List<String> column = new ArrayList<>();
        for (int i = 0; i < input.length - 1; i++) {
            String line = input[i];
            String colValue = line.substring(offset, offset + length);
            column.add(colValue);
        }
        return column;
    }

    private void part1PreProcess(final String[] input) {
        final List<String> operations = Arrays.stream(input[input.length - 1].split(" +")).toList();

        final List<List<String>> grid = Arrays.stream(input, 0, input.length - 1)
                .map(line -> Arrays.stream(line.trim().split(" +")).toList())
                .toList();

        final List<List<Long>> problemsRaw = new ArrayList<>();

        // Extract each problems (each col)
        for (int i = 0; i < grid.getFirst().size(); i++) {
            List<Long> problem = new ArrayList<>();
            for (final List<String> strings : grid) {
                problem.add(Long.parseLong(strings.get(i)));
            }
            problemsRaw.add(problem);
        }

        problems = new ArrayList<>();

        for (int i = 0; i < problemsRaw.size(); i++) {
            problems.add(new Problem<>(
                    switch (operations.get(i)) {
                        case "+" -> Long::sum;
                        case "*" -> (Long a, Long b) -> a * b;
                        default -> throw new IllegalArgumentException("Unknown operation: " + operations.get(i));
                    },
                    problemsRaw.get(i)
            ));
        }
    }

    @Override
    public Long solvePart1() {
        long total = 0L;
        for (final Problem<Long> problem : problems) {
            total += problem.solve();
        }
        return total;
    }

    @Override
    public Long solvePart2() {

        problems.clear();

        for (int k = 0; k < problemsStr.size(); k++) {
            final List<Long> elements = getLongs(k);
            Problem<Long> p = new Problem<>(
                    switch (operationsPart2.get(k).trim()) {
                        case "+" -> Long::sum;
                        case "*" -> (Long a, Long b) -> a * b;
                        default -> throw new IllegalArgumentException("Unknown operation: " + operationsPart2.get(k));
                    },
                    elements
            );

            problems.add(p);
        }

        long total = 0L;
        for (final Problem<Long> p : problems) {
            total += p.solve();
        }


        return total;
    }

    private List<Long> getLongs(int k) {
        final StringBuilder local = new StringBuilder();
        final List<Long> elements = new ArrayList<>();
        final List<String> problem = problemsStr.get(k);

        for (int i = 0; i < problem.getFirst().length(); i++) {
            for (String s : problem) {
                String currentChar = s.charAt(i) + "";
                if (!currentChar.equals(" ")) {
                    local.append(currentChar);
                }
            }

            if (!local.isEmpty()) {
                elements.add(Long.parseLong(local.toString()));
                local.setLength(0);
            }

        }
        return elements;
    }
}
