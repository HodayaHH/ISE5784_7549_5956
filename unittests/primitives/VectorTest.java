package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void testAdd() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2         = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, ()->v1.add(v1Opposite),"ERROR: Vector + -itself does not throw an exception");
        assertEquals(v1Opposite,v1.add(v2),"ERROR: Vector + Vector does not work correctly");
        assertEquals(new Vector(3, 6, 9),v1.subtract(v2),"ERROR: Vector + Vector does not work correctly");
    }

    @Test
    void testScale() {

    }

    @Test
    void testDotProduct() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
        Vector v3         = new Vector(0, 3, -2);

        Vector vr = v1.crossProduct(v3);
        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(0,v1.dotProduct(v2) + 28,"ERROR: dotProduct() wrong value");
        assertEquals(0,vr.dotProduct(v1),"ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0,vr.dotProduct(v3),"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void testCrossProduct() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
//        Vector v = new Vector(1, 2, 3);
//        Vector u = v.normalize();
        assertThrows(Exception.class,
                    ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");


    }

    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v4 = new Vector(1, 2, 2);
        assertEquals(14,v1.lengthSquared(),0.00001,"ERROR: lengthSquared() wrong value");
        assertEquals(0,v4.lengthSquared()-9,"ERROR: lengthSquared() wrong value" );
        assertEquals(0,v4.length() - 3,"ERROR: length() wrong value");
    }

    @Test
    void testLength() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v3         = new Vector(0, 3, -2);
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        Vector vr = v1.crossProduct(v3);
        assertEquals(0,vr.length() - v1.length() * v3.length(),"ERROR: crossProduct() wrong result length");
        assertEquals(0,u.length() - 1,"ERROR: the normalized vector is not a unit vector");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(0,u.length() - 1,"ERROR: the normalized vector is not a unit vector");
    }
}