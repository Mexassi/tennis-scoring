package io.github.mexassi.achievable.point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    private Point point;

    @Test
    @DisplayName("It should create a point with an accessible point type")
    void createPoint() {
        point = new Point(PointType.ADVANTAGE);

        assertNotNull(point);
        assertEquals(PointType.ADVANTAGE, point.getType());
    }
}