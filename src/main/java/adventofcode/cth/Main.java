package adventofcode.cth;

import adventofcode.cth.day3.Day3;
import adventofcode.cth.utils.Solver;

public class Main {

    public static void main(String[] args) {
        Solver<Integer> day = new Day3();
        System.out.println("Today solution: " + day.solve());
    }
}
