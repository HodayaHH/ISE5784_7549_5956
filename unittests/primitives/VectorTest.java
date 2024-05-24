package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    @Test
    void testConstructorVector()
    {
        //The zero vector is not possible

        assertThrows(IllegalArgumentException.class,
                ()-> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");
        assertThrows(IllegalArgumentException.class,
                ()->new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception");
    }
    @Test
    void testAdd() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2         = new Vector(-2, -4, -6);
        Vector v3         = new Vector(2, 3, 4);

        //Checking if throws an exception when the add operation returns zero point
        assertThrows(IllegalArgumentException.class,
                ()->v1.add(v1Opposite),
                "ERROR: Vector + -itself does not throw an exception");
        //Addition of opposite vectors
        assertEquals(v1Opposite,v1.add(v2),"ERROR: Vector + Vector does not work correctly");
        //Adding non-opposite vectors
        assertEquals(new Vector(3, 5, 7), v1.add(v3), "ERROR: Vector + Vector does not work correctly");

    }

    @Test
    void testScale() {
        Vector v = new Vector(1, 2, 3);
        //Multiply by a positive number
        assertEquals(new Vector(2, 4, 6), v.scale(2), "ERROR: scale() by 2 does not work correctly");
        //Multiply by a negative number
        assertEquals(new Vector(-1, -2, -3), v.scale(-1), "ERROR: scale() by -1 does not work correctly");
    }


    @Test
    void testDotProduct() {//לבדוק
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        Vector vr = v1.crossProduct(v3);
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(-28,v1.dotProduct(v2) ,"ERROR: dotProduct() wrong value");
        assertTrue(v.dotProduct(u) >= 0,"ERROR: the normalized vector is opposite to the original one");

        assertEquals(0,vr.dotProduct(v1),"ERROR: crossProduct() result is not orthogonal to its operands");//-
        assertEquals(0,vr.dotProduct(v3),"ERROR: crossProduct() result is not orthogonal to its operands");//-

    }

    @Test
    void testCrossProduct() {//לבדוקק
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        //
        assertThrows(Exception.class,
                    ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");

        //---------------chat
        Vector vr = v1.crossProduct(v3);
        assertEquals(new Vector(-13, 2, 3), vr, "ERROR: crossProduct() wrong value");
        assertEquals(0, vr.dotProduct(v1), "ERROR: crossProduct() result is not orthogonal to the first operand");
        assertEquals(0, vr.dotProduct(v3), "ERROR: crossProduct() result is not orthogonal to the second operand");

    }

    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(-1, -2, -3);
        Vector v4 = new Vector(1, 2, 2);

        assertEquals(9,v4.lengthSquared(),0.0001,"ERROR: lengthSquared() wrong value" );

        assertEquals(14,v1.lengthSquared(),0.00001,"ERROR: lengthSquared() wrong value");//לוקטור שלילי אם צריך


    }

    @Test
    void testLength() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v4 = new Vector(1, 2, 2);

        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        Vector vr = v1.crossProduct(v3);
         assertEquals(3,v4.length(), 0.0001,"ERROR: length() wrong value");
        assertEquals(0,vr.length() - v1.length() * v3.length(),"ERROR: crossProduct() wrong result length");
        assertEquals(0,u.length() - 1,"ERROR: the normalized vector is not a unit vector");
    }

    @Test
    void testNormalize() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector u = v1.normalize();
        Vector u1= v1Opposite.normalize();
        assertEquals(1,u.length() ,"ERROR: the normalized vector is not a unit vector");
        assertEquals(1,u.length() ,"ERROR: the normalized vector is not a unit vector");
        Vector normalizeV1  =new Vector(1 /v1.length() , 2 /v1.length(), 3 / v1.length());
        Vector normalizeV1Opp=new Vector(-1 / v1Opposite.length(), -2 / v1Opposite.length(), -3 / v1Opposite.length());

        // Checking that the normalized vectors maintain the correct direction
        assertEquals(normalizeV1, u, "ERROR: normalize() does not produce the correct unit vector");
        assertEquals(normalizeV1Opp, u1, "ERROR: normalize() does not produce the correct unit vector");
    }

    /**
     *  Test method for Subtract operation
     */
    //בדיקה אם צריך לעשות למרות שיורש את הפונקציה (נראלי צריך)
    @Test
    void testSubtract() {
        Vector v1  = new Vector(1, 2, 3);
        Vector v2  = new Vector(-2, -4, -6);
        assertEquals(new Vector(3, 6, 9),v1.subtract(v2),"ERROR: (Vector - Vector) does not work correctly");
        assertThrows(IllegalArgumentException.class,
                ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");

    }
}