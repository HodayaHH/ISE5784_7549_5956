package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testGetNormal( ) {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 1);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1,p2,p3);
        Vector v1= p2.subtract(p1);
        Vector v2= p3.subtract(p1);
        Vector normal = v1.crossProduct(v2).normalize();
        assertEquals(normal, plane.getNormal());
        // The normal vector of the plane should be the same as the given normal vector

    }


}