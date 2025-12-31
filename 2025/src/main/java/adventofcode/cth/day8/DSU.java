package adventofcode.cth.day8;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Disjoint Set Union (DSU) implementation.
 */
class DSU {
    private final int[] parent, size;

    public DSU(final int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Find the root of the element a with path compression.
     *
     * @param a element to find the root of
     * @return the root of the element a
     */
    public int find(final int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }


    /**
     * Union the sets containing elements a and b.
     * Used union by size heuristic
     *
     * @param a First element
     * @param b Second element
     */
    public void union(final int a, final int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    public int distinctSets() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }

    public boolean connected(final int a, final int b) {
        return find(a) == find(b);
    }

    public List<List<Integer>> displayAndGetResult() {

        // generate the lists of connected
        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            components.putIfAbsent(root, new ArrayList<>());
            components.get(root).add(i);
        }

        // display the first 3 components ordered by size
        final List<List<Integer>> l = components.values().stream()
                .sorted((a, b) -> Integer.compare(b.size(), a.size()))
                .limit(3).toList();

        l.forEach(component -> System.out.println("Component (size " + component.size() + "): " + component));

        return l;

    }
}
