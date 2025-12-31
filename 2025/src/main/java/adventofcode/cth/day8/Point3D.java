package adventofcode.cth.day8;

import lombok.Data;

import java.util.Objects;

/**
 * A class representing a point in 3D space.
 */
@Data
public class Point3D {
    private final int x;
    private final int y;
    private final int z;

    /**
     * Calculates the Euclidean distance
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public long distanceTo(final Point3D other) {
        long dx = this.x - other.x;
        long dy = this.y - other.y;
        long dz = this.z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D other)) return false;
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
