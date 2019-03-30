package io.github.mexassi.achievable.point;

import java.util.Objects;

public class Point {

    private final PointType type;

    public Point(PointType type) {
        this.type = type;
    }

    public PointType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return type == point.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Point{" +
                "type=" + type +
                '}';
    }
}
