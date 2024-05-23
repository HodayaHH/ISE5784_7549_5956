package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(1,new Point(0, 0, 0));
        Point p1 = new Point(1, 0, 0);
        Vector normal = sphere.getNormal(p1);;
        assertEquals(new Vector(1,0,0), normal);
    }
}