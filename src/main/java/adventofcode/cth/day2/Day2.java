package adventofcode.cth.day2;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.ArrayList;
import java.util.List;

public class Day2 implements Solver<Long> {

    private final List<List<Long>> expandedIds = new ArrayList<>();
    private final String[] input;

    public Day2() {
        input = InputReader.readResourceToString("day2.txt").split(",");
    }

    public boolean isIdValid(final long id) {
        String idStr = String.valueOf(id);
        final int n = idStr.length();

        // 565656 is invalid because 56 repeats more than 2 times
        // 22222 is invalid because 2 repeats more than 2 times
        // 2121212121 is invalid because 21 repeats more than 2 times

        for(int length = 1; length <= n / 2; length++){
            if(n % length != 0) continue;

            String pattern = idStr.substring(0, length);
            int repetitions = n / length;

            StringBuilder sb = new StringBuilder();
            sb.append(pattern.repeat(repetitions));

            if(sb.toString().equals(idStr)) {
                return false;
            }
        }


        return true;

    }

    private void expandIds() {
        long start, end;
        for (final String range : input) {
            List<Long> ids = new ArrayList<>();
            String[] bounds = range.split("-");
            start = Long.parseLong(bounds[0]);
            end = Long.parseLong(bounds[1]);

            for (long i = start; i <= end; i++) {
                ids.add(i);
            }
            expandedIds.add(ids);
        }
    }

    private void findInvalidIds(final List<Long> invalidIds) {
        for (final List<Long> ids : expandedIds) {
            for (final long id : ids) {
                if (!isIdValid(id)) {
                    invalidIds.add(id);
                }
            }
        }
    }

    @Override
    public Long solve() {
        final List<Long> invalidIds = new ArrayList<>();

        expandIds();
        findInvalidIds(invalidIds);
        System.out.println("Invalid IDs: " + invalidIds);

        final long answer = invalidIds.stream().mapToLong(Long::longValue).sum();

        return answer;
    }
}
