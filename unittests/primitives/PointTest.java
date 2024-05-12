package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testSubtract() {
        Point  p1 = new Point(1, 2, 3);
        Point  p2 = new Point(2, 4, 6);
        //assertEquals(p1,p2.subtract(p1),0.00001,"ERROR: (point2 - point1) does not work correctly");
    }

    @Test
    void testAdd() {
    }

    @Test
    void testDistanceSquared() {
    }

    @Test
    void testDistance() {
    }
}