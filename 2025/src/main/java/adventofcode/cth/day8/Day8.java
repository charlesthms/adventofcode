package adventofcode.cth.day8;


import adventofcode.cth.utils.InputReader;
import adventofcode.cth.utils.Solver;

import java.util.*;

public class Day8 implements Solver<Integer, Integer> {

    final String[] input;
    final List<Point3D> points = new ArrayList<>();

    public Day8() {
        input = InputReader.readResourceToString("day8.txt").split("\n");
        processInput();
    }

    private void processInput() {
        for (String line : input) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0].trim());
            int y = Integer.parseInt(coords[1].trim());
            int z = Integer.parseInt(coords[2].trim());
            points.add(new Point3D(x, y, z));
        }
    }

    /**
     * The idea is to create all pairs of points sort them by distance, and then use a DSU to connect points
     */
    private Queue<Connection> assembleConnections() {
        Queue<Connection> q = new PriorityQueue<>(Comparator.comparingDouble(Connection::getDistance));
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Connection conn = new Connection(i, j, points.get(i).distanceTo(points.get(j)));
                q.add(conn);
            }
        }
        return q;
    }


    @Override
    public Integer solvePart1() {
        final DSU dsu = new DSU(points.size());
        final Queue<Connection> q = assembleConnections();

        int count = 0;
        while (count < 1000 && !q.isEmpty()) {
            Connection conn = q.poll();
            if (!dsu.connected(conn.getA(), conn.getB())) {
                dsu.union(conn.getA(), conn.getB());
            }
            count++;
        }

       final List<List<Integer>> res = dsu.displayAndGetResult();

        return res.stream().mapToInt(List::size).reduce(1, Math::multiplyExact);
    }

    @Override
    public Integer solvePart2() {
        final DSU dsu = new DSU(points.size());
        final Queue<Connection> q = assembleConnections();

        int result = 0;

        while (!q.isEmpty()) {
            Connection conn = q.poll();
            if (!dsu.connected(conn.getA(), conn.getB())) {
                dsu.union(conn.getA(), conn.getB());
            }

            if (dsu.distinctSets() == 1) {
                //System.out.println(points.get(conn.getA()) + " " + points.get(conn.getB()));
                result = points.get(conn.getA()).getX() * points.get(conn.getB()).getX();
                break;
            }
        }

        return result;
    }
}
