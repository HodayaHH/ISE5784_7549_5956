package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 */
class PlaneTest {
    /**
     * Test Constructor method for plan
     */
    @Test
    void testConstructorPlan() {
        // ============ Equivalence Partitions Tests ==============

        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 1);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, p2, p3);
        Vector v = new Vector(-1, 0, 0);

        //TC01:Checking if the normal calculation is correct
        assertEquals(v,
                plane.getNormal(),
                "ERROR: The calculation of the normal in the constructor does not work correctly");

        // =============== Boundary Values Tests ==================

        //TC10:checking whether two points converge
        Point p4 = new Point(0, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p1, p3, p4),
                "ERROR: (p1=p2) points converge does not throw an exception");

        // TC12:Check whether two points are on the same line
        Point point1 = new Point(3, 2, 1);
        Point point2 = new Point(6, 5, 4);
        Point point3 = new Point(9, 8, 7);

        //ensure there are no exceptions
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(point1, point2, point3),
                "ERROR: The points are on the same line does not throw an exception");

    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============

        //Ray is not orthogonal or parallel to the plane


        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, p2, p3);

        //TC01:One class for each ray that intersects the plane.
        Ray ray1 = new Ray(new Point(0, 1, 1), new Vector(0, 0, -1));
        final Point gp1 = new Point(0, 1, 0);
        final var exp1 = List.of(gp1);

        assertEquals(1,
                plane.findIntersections(ray1).size(),
                "Wrong number of points");

        assertEquals(exp1,
                plane.findIntersections(ray1),
                "Ray crosses plane");

        //TC02:Second class for each ray that does not cut.
        Ray ray3 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray3),
                "ray that does not cut -Error should return null");

        // =============== Boundary Values Tests ==================
//Group: A ray parallel to a plane

        //TC10: Ray on the plane
        Ray ray4 = new Ray(new Point(0, 0, 1), new Vector(1, -1, 0));
        assertNull(plane.findIntersections(ray4),
                "Ray on the plane- Error: should return null");

        //TC11: Ray not on the plane
        Ray ray5 = new Ray(new Point(0, 0, 2), new Vector(1, -1, 0));
        assertNull(plane.findIntersections(ray5),
                "Ray not on the plane - Error: should return null");
//Group: A ray orthogonal  to a plane
        //TC12: ray starts before the plane
        Ray ray7 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        final Point gp2 = new Point(0, 0, 0); //האם זה נקודת החיתוך לבדוקק
        final var exp2 = List.of(gp2);

        assertEquals(1,
                plane.findIntersections(ray7).size(),
                "Wrong number of points");

        assertEquals(exp2,
                plane.findIntersections(ray7),
                " Ray crosses plane");

        // TC13: ray starts on the plain
        Ray ray9 = new Ray(new Point(0, 0, 1), new Vector(1, 1, 1));
        assertNull(plane.findIntersections(ray9),
                "ray starts on the plain -Error: should return null");

        //TC14: ray starts after the plane
        Ray ray8 = new Ray(new Point(2, 2, 2), new Vector(1, 1, 1));
        assertNull(plane.findIntersections(ray8),
                "ray starts after the plane -Error: should return null");

//Group: ray is neither orthogonal nor parallel
        //  TC15:ray starts at the same point that the plane is represented by.
        Ray rayOnPlane = new Ray(new Point(1, 0, 0), new Vector(0, 0, -1));
        assertNull(plane.findIntersections(rayOnPlane),
                "ray starts at the same point that the plane is represented by -Error: should return null");

        // TC16:ray starts on the plane (the starting point is on the plane but the ray is not)
        Ray rayAbovePlane = new Ray(new Point(0, 0, 1), new Vector(1, 1, 1)); // Above the plane
        assertNull(plane.findIntersections(rayAbovePlane),
                "ray starts on the plane - Error: should return null ");
    }
}