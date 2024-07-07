package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Point class
 */
class PointTest {

    /**
     * Test method for Subtract operation
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 4, 6);

        // ============ Equivalence Partitions Tests ==============
        //TC01:Checking if subtraction between two points is calculated correctly
        assertEquals(p1, p2.subtract(p1), "ERROR: (point2 - point1) does not work correctly");

        // =============== Boundary Values Tests ==================
        //TC10:ensure there are no exceptions
        assertThrows(IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "ERROR: (point - itself) does not throw an exception");

    }

    /**
     * Test method for Add operation
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 4, 6);
        Vector v1 = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);

        //============ Equivalence Partitions Tests ==============
        //TC01:Checking if the add operation is calculated correctly
        assertEquals(p2,
                p1.add(v1),
                "ERROR: (point + vector) = other point does not work correctly");

        //TC02:Checking if the addition operation is calculated correctly for vectors in opposite directions
        assertEquals(Point.ZERO,
                p1.add(v1Opposite),
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    /**
     * Test method for Distance Squared
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p3 = new Point(2, 4, 5);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(0,
                p1.distanceSquared(p1),
                0.0001,
                "ERROR: point squared distance to itself is not zero");

        assertEquals(9,
                p1.distanceSquared(p3),
                0.0001,
                "ERROR: squared distance between points is wrong");

        assertEquals(9,
                p3.distanceSquared(p1),
                0.0001,
                "ERROR: squared distance between points is wrong");

    }

    /**
     * Test method for Distance
     */
    @Test
    void testDistance() {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 4, 3);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(5,
                p1.distance(p2),
                0.0001,
                "ERROR: distance between points to itself is wrong");

        assertEquals(5,
                p2.distance(p1),
                0.0001,
                "ERROR: distance between points to itself is wrong");

        assertEquals(0,
                p2.distance(p2),
                "ERROR: point distance to itself is not zero");


    }
}