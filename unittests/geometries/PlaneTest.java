package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /** Test Constructor method for plan*/
    @Test
    void testConstructorPlan( )
    {
        //One equivalence class
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 1);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1,p2,p3);
        assertEquals(new Vector(-1,0,0),plane.getNormal());
        // The normal vector of the plane should be the same as the given normal vector

        //Extreme case 1
        //checking whether two points converge
        Point P_2 = new Point(0, 0, 0);
        assertThrows(IllegalArgumentException.class, () ->
            new Plane(p1, P_2, p3),"ERROR: (p1=p2) points converge does not throw an exception");

        //Extreme case 2
        // Check whether two points are on the same line
        Point point1 = new Point(3, 2, 1);
        Point point2 = new Point(6, 5, 4);
        Point point3 = new Point(9, 8, 7);
        assertThrows(IllegalArgumentException.class, () ->
                new Plane(point1, point2, point3),"ERROR: The points are on the same line does not throw an exception");

    }



}