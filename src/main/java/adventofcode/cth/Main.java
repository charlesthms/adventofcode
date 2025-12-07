package adventofcode.cth;

import adventofcode.cth.day1.Day1;
import adventofcode.cth.day2.Day2;
import adventofcode.cth.day3.Day3;
import adventofcode.cth.day4.Day4;
import adventofcode.cth.utils.Solver;

public class Main {

    public static void main(String[] args) {
        Solver<?, ?> solver;

        // Change the day number to run different day's solution
        int dayNumber = 4;

        switch (dayNumber) {
            case 1 -> solver = new Day1();
            case 2 -> solver = new Day2();
            case 3 -> solver = new Day3();
            case 4 -> solver = new Day4();
            default -> throw new IllegalArgumentException("Invalid day number: " + dayNumber);
        }

        // add execution time measurement
        long startTime = System.nanoTime();
        System.out.print("Part 1 Solution: \u001B[32m" + solver.solvePart1() + "\u001B[0m");
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(" in \u001b[38;5;201m" + duration / 1_000_000 + " ms\u001B[0m");

        startTime = System.nanoTime();
        System.out.print("Part 2 Solution: \u001B[32m" + solver.solvePart2() + "\u001B[0m");
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println(" in \u001b[38;5;201m" + duration / 1_000_000 + " ms\u001B[0m");

    }
}
