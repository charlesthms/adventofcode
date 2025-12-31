package adventofcode.cth.day5;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.*;

public class Day5 implements Solver<Long, Long> {
    private final String[] ranges;
    private final String[] ids;

    public Day5() {
        final String[] input = InputReader.readResourceToString("day5.txt").split("\n\n");
        ranges = input[0].split("\n");
        ids = input[1].split("\n");
    }

    private List<List<Long>> expandRanges() {
        final List<List<Long>> validRanges = new ArrayList<>();

        for (final String range : ranges) {
            final String[] parts = range.split("-");
            final long start = Long.parseLong(parts[0]);
            final long end = Long.parseLong(parts[1]);

            validRanges.add(List.of(start, end));
        }

        return validRanges;
    }

    private boolean isInRange(final long number, final List<List<Long>> ranges) {
        boolean isInRange = false;
        for (final List<Long> range : ranges) {
            if (number >= range.get(0) && number <= range.get(1)) {
                isInRange = true;
                break;
            }
        }
        return isInRange;
    }

    private long countValidIds(final List<List<Long>> ranges) {
        ranges.sort(Comparator.comparingLong(List::getFirst));

        long totalValidIds = 0;
        long currentStart = ranges.getFirst().getFirst();
        long currentEnd = ranges.getFirst().getLast();

        for (final List<Long> range : ranges.subList(1, ranges.size())) {
            long nextStart = range.get(0);
            long nextEnd = range.get(1);

            // avoid overlaps ranges
            if (nextStart <= currentEnd + 1 && nextEnd > currentEnd) {
                currentEnd = nextEnd;
            } else if (nextStart > currentEnd + 1) {
                totalValidIds += (currentEnd - currentStart + 1);
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }
        totalValidIds += (currentEnd - currentStart + 1);

        return totalValidIds;
    }

    @Override
    public Long solvePart1() {
        final List<List<Long>>freshes = expandRanges();
        long totalFreshes = 0;

        for (final String id : ids) {
            final long number = Long.parseLong(id);
            if (isInRange(number, freshes)) {
                totalFreshes++;
            }
        }

        return totalFreshes;
    }

    @Override
    public Long solvePart2() {
        final List<List<Long>> ranges = expandRanges();
        long totalValidIds = countValidIds(ranges);
        return totalValidIds;
    }
}
