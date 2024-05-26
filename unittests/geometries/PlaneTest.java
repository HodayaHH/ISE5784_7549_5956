package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 */
class PlaneTest {
    /** Test Constructor method for plan*/
    @Test
    void testConstructorPlan( )
    {
        // ============ Equivalence Partitions Tests ==============

        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 1);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1,p2,p3);
        Vector v =new Vector(-1,0,0);
        //TC01:Checking if the normal calculation is correct
        assertEquals(v,
                plane.getNormal(),
                "ERROR: The calculation of the normal in the constructor does not work correctly");

        // =============== Boundary Values Tests ==================

        //TC10:checking whether two points converge
        Point p4= new Point(0, 0, 0);
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
        //Ray is not orthogonal or parallel to the plane

        //TC01:One class for each ray that intersects the plane.


        //TC02:Second class for each horn that does not cut.
    }
}