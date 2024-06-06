package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============

        // positive distance
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point1 = ray1.getPoint(5);
        assertEquals(new Point(5, 0, 0), point1);

        //negative distance
        Ray ray2 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point2 = ray2.getPoint(-3);
        assertEquals(new Point(-3, 0, 0), point2);

        // =============== Boundary Values Tests ==================

        //Zero distance
        Ray ray3 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point3 = ray3.getPoint(0);
        assertNull(point3);
       // assertEquals(new Point(0, 0, 0), point3);
    }
}