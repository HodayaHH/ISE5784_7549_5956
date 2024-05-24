package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        //One equivalence class
        Ray ray= new Ray(new Point(0,0,0),new Vector(0,1,0));
        Tube tube =new Tube(1,ray);
        Point p = new Point(1, 1, 0); // A point on the area of the Tube
        Vector normal = tube.getNormal(p);
        assertEquals(new Vector(1,0,0),normal);

        //Extreme case 1
        //The head of the ray and the point p-p0 is perpendicular to v ray
        Point p1 = new Point(0, 0, 1); // Another point on the Tube area
        assertThrows(IllegalArgumentException.class,
                ()->tube.getNormal(p1),
                "An extreme case when p-p0 is orthogonal to V");
    }
}