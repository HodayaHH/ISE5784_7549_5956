package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TriangleTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle triangle = new Triangle(p1, p2, p3);
        Vector normal = new Vector(0, 0, 1);
        assertEquals(normal, triangle.getNormal(p1), "The normal vector is incorrect");
        assertEquals(1, triangle.getNormal(p1).length(), "The normal vector is not normalized");
    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Triangle triangle = new Triangle(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        );
        // TC01: Ray inside
        Ray rayInside = new Ray(new Point(0.25, 0.25, -1), new Vector(0, 0, 1));

        assertEquals(1,
                triangle.findIntersections(rayInside).size(),
                "There should be exactly one intersection point");

        assertEquals(List.of(new Point(0.25, 0.25, 0)),
                triangle.findIntersections(rayInside),
                "Intersection point is incorrect");

        // TC02: Ray outside in front of the side
        Ray rayOutsideSide = new Ray(new Point(1, 1, -1), new Vector(0, 0, 1));

        assertNull(triangle.findIntersections(rayOutsideSide),
                "Ray should not intersect the triangle");

        // TC03: Ray outside in front of the apex
        Ray rayOutsideApex = new Ray(new Point(0.5, 0.5, -1), new Vector(1, 1, 1));

        assertNull(triangle.findIntersections(rayOutsideApex),
                "Ray should not intersect the triangle");


        // =============== Boundary Values Tests ==================

        // TC10: ray hits a side of the triangle
        Ray rayHitsSide = new Ray(new Point(0.5, 0, -1), new Vector(0, 1, 1));

        assertNull(triangle.findIntersections(rayHitsSide),
                "Ray hitting a side should not be considered an intersection");

        // TC11: ray hits the apex of the triangle
        Ray rayHitsApex = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));

        assertNull(triangle.findIntersections(rayHitsApex),
                "Ray hitting the apex should not be considered an intersection");

        // TC12: ray hits the continuation of a rib
        Ray rayHitsRibContinuation = new Ray(new Point(-1, 0.5, -1), new Vector(1, 0, 1));

        assertNull(triangle.findIntersections(rayHitsRibContinuation),
                "Ray hitting the continuation of a rib should not be considered an intersection");
    }
}