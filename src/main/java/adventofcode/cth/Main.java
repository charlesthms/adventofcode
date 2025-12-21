package adventofcode.cth;

import adventofcode.cth.day1.Day1;
import adventofcode.cth.day2.Day2;
import adventofcode.cth.day3.Day3;
import adventofcode.cth.day4.Day4;
import adventofcode.cth.day5.Day5;
import adventofcode.cth.day6.Day6;
import adventofcode.cth.day7.Day7;
import adventofcode.cth.day8.Day8;
import adventofcode.cth.utils.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static void printHeader(final int dayNumber) {
        final String asciiBanner = """
                    _          ____   ____   ___ ____  ____ \s
                   / \\   ___  / ___| |___ \\ / _ \\___ \\| ___|\s
                  / _ \\ / _ \\| |       __) | | | |__) |___ \\\s
                 / ___ \\ (_) | |___   / __/| |_| / __/ ___) |
                /_/   \\_\\___/ \\____| |_____|\\___/_____|____/\s
                """;
        logger.info("\n\u001B[34m{}\u001B[0m", asciiBanner);
    }

    private static void printSolution(final int part, final Solver<?, ?> solver) {
        long startTime = System.nanoTime();
        Object solution;

        switch (part) {
            case 1 -> solution = solver.solvePart1();
            case 2 -> solution = solver.solvePart2();
            default -> throw new IllegalArgumentException("Invalid part number: " + part);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Part {} Solution: \u001B[32m{}\u001B[0m in \u001b[38;5;201m{} ms\u001B[0m", part, solution, duration / 1_000_000);
    }

    public static void main(String[] args) {
        final Solver<?, ?> solver;
        final int dayNumber = 8;

        switch (dayNumber) {
            case 1 -> solver = new Day1();
            case 2 -> solver = new Day2();
            case 3 -> solver = new Day3();
            case 4 -> solver = new Day4();
            case 5 -> solver = new Day5();
            case 6 -> solver = new Day6();
            case 7 -> solver = new Day7();
            case 8 -> solver = new Day8();
            default -> throw new IllegalArgumentException("Invalid day number: " + dayNumber);
        }

        printHeader(dayNumber);
        printSolution(1, solver);
        printSolution(2, solver);
    }
}
