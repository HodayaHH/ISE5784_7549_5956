package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {

        // Empty collection (BVA)
        Geometries emptyCollection = new Geometries();
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));

        assertNull(emptyCollection.findIntersections(ray),
                "Empty collection should return null");

        // No intersection shapes (BVA)
        Geometries noIntersectionShapes = new Geometries(
                new Sphere(new Point(0, 0, 1), 1),
                new Plane(new Point(0, 0, 2), new Vector(0, 0, 1))
        );
        Ray ray1 = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));

        assertNull(noIntersectionShapes.findIntersections(ray1),
                "No intersection shapes should return null");

        // Only one shape intersects (BVA)
        Geometries oneIntersectingShape = new Geometries(
                new Sphere(new Point(0, 0, -1), 1)
        );
//        assertEquals(List.of(new Point(0, 0, 0)),
//                oneIntersectingShape.findIntersections(ray),
//                "One intersecting shape should return the intersection point");

        // All intersecting shapes (BVA)
        Geometries allIntersectingShapes = new Geometries(
                new Sphere(new Point(0, 0, -1), 1),
                new Plane(new Point(0, 0, -2), new Vector(0, 0, 1))
        );
//        assertEquals(List.of(new Point(0, 0, 0)),
//                allIntersectingShapes.findIntersections(ray),
//                "All intersecting shapes should return the intersection point");
    }

}