package geometries;

import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 */
class SphereTest {
    /**
     * Test method for Sphere.
     */

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);


    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(new Point(0, 0, 0), 2);
        Point p1 = new Point(2, 0, 0);
        Vector v1 =new Vector(1, 0, 0);

        //TC01: Checking if the computer calculates the normal well
        assertEquals(new Vector(1, 0, 0),
                sphere.getNormal(p1),
                "The calculation of the normal does not work correctly");
    }

    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(p100,1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(0, 0.5, 1);
        final Point p03 = new Point(0, 0, 2);

        final Vector v02 = new Vector(0, 1, 1);
        final Vector v03 = new Vector(1, -1, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110))
                , "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01)))
                .toList();

        assertEquals(2,
                result1.size(),
                "Wrong number of points");

        assertEquals(exp,
                result1,
                "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        final var result2 = sphere.findIntersections(new Ray(p02, v02))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p02)))
                .toList();

        assertEquals(1,
                result2.size(),
                "Wrong number of points");
        //בדיקה אם הנקודת חיתוך מחושבת נכון


        // TC04: Ray starts after the sphere (0 points)
        Point point1 = new Point(3, 0, 0);
        Vector vec1 = new Vector(3, 0, 1);

        assertNull(sphere.findIntersections(new Ray(point1, vec1)),
                "Ray starts after the sphere");


        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)

        final var result3 = sphere.findIntersections(new Ray(p03, v03))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p03)))
                .toList();

        assertEquals(1,
                result3.size(),
                "Wrong number of points");


        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p03, v02)),
                "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Sphere sphere1 = new Sphere( new Point(0, 0, 0),1);//כל הבדיקות הקשורות לבדיקות של קרן שעובר במרכז משתמשות בספירה זו
        Ray ray = new Ray(new Point(-2, 0, 0), new Vector(1, 0, 0));
        List<Point> result4 = sphere.findIntersections(ray);

        assertEquals(2,
                result4.size(),
                "Wrong number of points");

        // TC14: Ray starts at sphere and goes inside (1 point)
        Ray ray2 = new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0));
        List<Point> result5= sphere1.findIntersections(ray2);

        assertEquals(1,
                result5.size(),
                "Wrong number of points");

        // TC15: Ray starts inside (1 point)
        Ray ray3 = new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0));
        List<Point> result6 = sphere.findIntersections(ray3);

        assertEquals(1,
                result6.size(),
                "Wrong number of points");

        // TC16: Ray starts at the center (1 point)
        Ray ray4 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        List<Point> result7 = sphere.findIntersections(ray4);

        assertEquals(1,
                result7.size(),
                "Wrong number of points");

        // TC17: Ray starts at sphere and goes outside (0 points)
        Ray ray5 = new Ray(new Point(1, 0, 0), new Vector(2, 0, 0));

        assertNull(sphere.findIntersections(ray5),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        Ray ray6 = new Ray(new Point(2, 0, 0), new Vector(3, 0, 0));

        assertNull(sphere.findIntersections(ray6),
                "Ray starts after sphere");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        Ray ray7 = new Ray(new Point(-1, 1, 0), new Vector(1, 0, 0));

        assertNull(sphere.findIntersections(ray7),
                "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        Ray ray8 = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));

        assertNull(sphere.findIntersections(ray8),
                "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        Ray ray9 = new Ray(new Point(1, 1, 0), new Vector(1, 0, 0));

        assertNull(sphere.findIntersections(ray9),
                "Ray starts after the tangent point");

        // **** Group: Special cases

        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        Ray ray10 = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));

        assertNull(sphere.findIntersections(ray10),
                " Ray's line is outside, ray is orthogonal to ray start to sphere's center line");


    }
}