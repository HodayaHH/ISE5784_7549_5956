package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        // Create a collection of bodies with sphere, plane and triangle
        Geometries geometries = new Geometries(
                new Sphere(new Point(0, 0, 1), 1),
                new Plane(new Point(0, 0, 2), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 0), new Point(1, -1, 0), new Point(-1, 0, 0))
        );

        // empty body collection (BVA)
        Geometries emptyGeometries = new Geometries();
        Ray ray1 = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));

        assertNull(emptyGeometries.findIntersections(ray1),
                "BVA: Ray should not intersect with any shapes in empty geometries");

        // No shape cut (BVA)
        Ray ray2 = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));

        assertNull(geometries.findIntersections(ray2),
                "BVA: Ray should not intersect with any shapes");

        // Only one shape is cut (BVA)
        Ray ray3 = new Ray(new Point(1, 0, 4), new Vector(0, 0, -1)); // will cut the plane only
        assertEquals(1, geometries.findIntersections(ray3).size(), "BVA: Ray should intersect with exactly one shape");

        // some shapes (but not all) are cut (EP)
        Ray ray4 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)); // will intersect the sphere and the plane

        assertEquals(2,
                geometries.findIntersections(ray4).size(),
                "EP: Ray should intersect with exactly two shapes");

        // all shapes are cut (BVA)
        Ray ray5 = new Ray(new Point(0, -0.5, 0), new Vector(0, 0.5, 1)); // will intersect the sphere, the plane and the triangle

        assertEquals(3,
                geometries.findIntersections(ray5).size(),
                "BVA: Ray should intersect with all shapes");
    }
}