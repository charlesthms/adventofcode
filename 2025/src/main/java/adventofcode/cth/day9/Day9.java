package adventofcode.cth.day9;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.Arrays;
import java.util.List;

public class Day9 implements Solver<Long, Integer> {
    private final List<int[]> points;

    public Day9() {
        String[] input = InputReader.readResourceToString("day9.txt").split("\n");

        points = Arrays.stream(input).map(line -> Arrays.stream(line.split(","))
                .mapToInt(Integer::parseInt)
                .toArray()).toList();
    }

    @Override
    public Long solvePart1() {
        long greatestArea = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int[] pointA = points.get(i);
                int[] pointB = points.get(j);

                int width = Math.abs(pointA[0] - pointB[0]) + 1;
                int height = Math.abs(pointA[1] - pointB[1]) + 1;

                long area = (long) width * height;
                if (area > greatestArea) {
                    greatestArea = area;
                }
            }
        }
        return greatestArea;
    }

    @Override
    public Integer solvePart2() {
        return 0;
    }
}
