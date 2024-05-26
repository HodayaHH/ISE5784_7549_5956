package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 */
class SphereTest {
    /**
     * Test method for Sphere.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(1, new Point(0, 0, 0));
        Point p1 = new Point(1, 0, 0);
        Vector normal = sphere.getNormal(p1);
        //TC01: Checking if the computer calculates the normal well
        assertEquals(new Vector(1, 0, 0),
                normal,
                "The calculation of the normal does not work correctly");
    }
}