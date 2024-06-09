package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: positive distance
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point1 = ray1.getPoint(5);
        assertEquals(new Point(5, 0, 0),
                point1,
                "ERROR: Ray did not return the correct point for positive distance");

        //TC02: negative distance
        Ray ray2 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point2 = ray2.getPoint(-3);
        assertEquals(new Point(-3, 0, 0),
                point2,
                "ERROR: Ray did not return the correct point for negative distance");

        // =============== Boundary Values Tests ==================

        //TC10: Zero distance
        Ray ray3 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point3 = ray3.getPoint(0);
        assertEquals(new Point(0, 0, 0),
                point3,
                "ERROR: Ray did not return the correct point for zero distance");
    }
}