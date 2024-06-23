package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: positive distance
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point1 = ray1.getPoint(5);
        assertEquals(new Point(5, 0, 0),
                point1,
                "ERROR: Ray did not return the correct point for positive distance");

        //TC02: negative distance
        Ray ray2 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point2 = ray2.getPoint(-3);
        assertEquals(new Point(-3, 0, 0),
                point2,
                "ERROR: Ray did not return the correct point for negative distance");

        // =============== Boundary Values Tests ==================

        //TC10: Zero distance
        Ray ray3 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Point point3 = ray3.getPoint(0);
        assertEquals(new Point(0, 0, 0),
                point3,
                "ERROR: Ray did not return the correct point for zero distance");
    }


    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(-1, 2, 0), new Vector(0, 1, 0));
        Point a = new Point(-3, 5, 0);
        Point b = new Point(0, 7, 0);
        Point c = new Point(2, 10, 0);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test with a balanced list - middle point should be closest to ray head

        List<Point> balancedList =new LinkedList<>();
        balancedList.add(b);
        balancedList.add(a);
        balancedList.add(c);

        assertEquals(a, ray.findClosestPoint(balancedList),"middle point should be closest to ray head-Wrong point");

        // =============== Boundary Values Tests ==================
        //TC10: Test with an empty list - should return null
        List<Point> emptyList = null;
        assertNull(ray.findClosestPoint(emptyList));

        //TC11: Test with list where first point is closest to ray head
        List<Point> firstClosestList =  new LinkedList<>();
        firstClosestList.add(a);
        firstClosestList.add(b);
        firstClosestList.add(c);
        assertEquals(a, ray.findClosestPoint(firstClosestList),"first point is closest to ray head-Wrong point");

        //TC12: Test with list where last point is closest to ray head
        List<Point> lastClosestList = new LinkedList<>();
        lastClosestList.add(c);
        lastClosestList.add(b);
        lastClosestList.add(a);
        assertEquals(a, ray.findClosestPoint(lastClosestList),"last point is closest to ray head-Wrong point");
    }

}