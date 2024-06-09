package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 */
class TubeTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));
        Tube tube = new Tube(1, ray);
        Point p = new Point(1, 1, 0); // A point on the area of the Tube
        Vector normal = tube.getNormal(p);
        Vector v1 = new Vector(1, 0, 0);
        //TC01:Checking if the computer calculates the normal well
        assertEquals(v1,
                normal,
                "The calculation of the normal does not work correctly");

        // =============== Boundary Values Tests ==================

        //TC10:The head of the ray and the point p-p0 is perpendicular to v ray - ensure there are no exceptions
        Point p1 = new Point(0, 0, 1); // Another point on the Tube area
        assertThrows(IllegalArgumentException.class,
                () -> tube.getNormal(p1),
                "An extreme case when p-p0 is orthogonal to V");
    }

    @Test
    void testFindIntersections() {
    }
}