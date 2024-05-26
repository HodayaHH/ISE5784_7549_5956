package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/**
 * Unit tests for primitives.Vector class
 */
class VectorTest {
    @Test
    void testConstructorVector() {
        //The zero vector is not possible
        //ensure there are no exceptions
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception");
    }

    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(2, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        //TC01:Addition of opposite vectors
        assertEquals(v1Opposite, v1.add(v2), "ERROR: Vector + Vector does not work correctly");
        //TC02:Adding non-opposite vectors
        assertEquals(new Vector(3, 5, 7), v1.add(v3), "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        //TC10:for connecting vectors of two opposite directions but the length is equal
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(v1Opposite),
                "ERROR: Vector + -itself does not throw an exception");
    }

    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        //TC01:Multiply by a positive number
        assertEquals(new Vector(2, 4, 6), v.scale(2), "ERROR: scale() by 2 does not work correctly");
        //TC02:Multiply by a negative number
        assertEquals(new Vector(-1, -2, -3), v.scale(-1), "ERROR: scale() by -1 does not work correctly");
    }


    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // ============ Equivalence Partitions Tests ==============
        //TC01:Checking if a scalar multiplication calculator is correct
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");

        //TC02:The vectors are orthogonal to the normal (v1xv3).
        Vector vr = v1.crossProduct(v3);
        assertEquals(0, vr.dotProduct(v1), "ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0, vr.dotProduct(v3), "ERROR: crossProduct() result is not orthogonal to its operands");

        // =============== Boundary Values Tests ==================

        //TC10:two orthogonal vectors
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");


        //TC12:One of the vectors is of unit size
        assertTrue(v.dotProduct(u) >= 0, "ERROR: the normalized vector is opposite to the original one");

    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        Vector v4 = new Vector(2, 4, 6);

        // ============ Equivalence Partitions Tests ==============
        //TC01:check if the calculation is true
        Vector vr = v1.crossProduct(v3);
        assertEquals(new Vector(-13, 2, 3), vr, "ERROR: crossProduct() wrong value");

        // =============== Boundary Values Tests ==================
        //TC10:Vectors in different directions and the lengths are unknown and different
        assertThrows(Exception.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");


        //TC12:two vectors at the same directions
        assertThrows(Exception.class,
                () -> v1.crossProduct(v4),
                "ERROR: crossProduct() two vectors at the same directions not throw an exception");

        //TC13:two vectors at the same directions and equal lengths
        assertThrows(Exception.class,
                () -> v1.crossProduct(v1),
                "ERROR: Vector crossProduct itself does not throw an exception");


        //TC14:two vectors different directions and equal lengths
        assertThrows(Exception.class,
                () -> v1.crossProduct(v4),
                "ERROR: two vectors different directions and equal lengths does not throw an exception");

    }

    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(-1, -2, -3);
        Vector v4 = new Vector(1, 2, 2);

        // ============ Equivalence Partitions Tests ==============
        // TC01:Check for positive numbers
        assertEquals(9, v4.lengthSquared(), 0.0001, "ERROR: lengthSquared() wrong value");
        //TC02:Test for negative numbers
        assertEquals(14, v1.lengthSquared(), 0.00001, "ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector v4 = new Vector(1, 2, 2);

        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        Vector vr = v1.crossProduct(v3);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(3, v4.length(), 0.0001, "ERROR: length() wrong value");
        double expectedCrossProductLength = Math.sqrt(182);
        assertEquals(expectedCrossProductLength, 0.0001, vr.length(), "ERROR: crossProduct() wrong result length");
        assertEquals(0, u.length() - 1, "ERROR: the normalized vector is not a unit vector");
    }

    @Test
    void testNormalize() {

        // ============ Equivalence Partitions Tests ==============

        //TC01:Checking the function for positive numbers
        Vector v1 = new Vector(1, 2, 3);
        Vector u = v1.normalize();
        assertEquals(1, u.length(), "ERROR: the normalized vector is not a unit vector");
        Vector normalizeV1 = new Vector(1 / v1.length(), 2 / v1.length(), 3 / v1.length());
        // Checking that the normalized vectors maintain the correct direction
        assertEquals(normalizeV1, u, "ERROR: normalize() does not produce the correct unit vector");

        //TC02:Checking the function for negative numbers בדיקה אם צריך לעשות את אותו בדיקה רק לוקטור שלילי כיוון שונה
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector u1 = v1Opposite.normalize();
        assertEquals(1, u1.length(), "ERROR: the normalized vector is not a unit vector");
        Vector normalizeV1Opp = new Vector(-1 / v1Opposite.length(), -2 / v1Opposite.length(), -3 / v1Opposite.length());
        // Checking that the normalized vectors maintain the correct direction
        assertEquals(normalizeV1Opp, u1, "ERROR: normalize() does not produce the correct unit vector");
    }

    /**
     * Test method for Subtract operation
     */

    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        //TC01:Checking if the subtraction operation calculates well
        assertEquals(new Vector(3, 6, 9), v1.subtract(v2), "ERROR: (Vector - Vector) does not work correctly");

        // =============== Boundary Values Tests ==================
        //TC10:Vectors in the same direction and equal lengths
        assertThrows(IllegalArgumentException.class,
                () -> v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");

    }
}