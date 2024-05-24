package primitives;

/**
 * class representing a vector
 */
public class Vector extends Point {
    /**
     * Parameter constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("The zero vector is not possible"); // The zero vector is not possible
        }
    }

    /**
     * Parameter constructor
     * @param xyz
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))//Checking if the value is the zero vector
        {
            throw new IllegalArgumentException("The zero vector is not possible"); // The zero vector is not possible
        }

    }

    /**
     * adding of 2 vectors
     * @param v1
     * @return a new vector
     */
    public Vector add(Vector v1) {
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * Multiplies a vector by a number
     * @param rhs
     * @return a new vector
     */
    public Vector scale(double rhs) {
        return new Vector(xyz.scale(rhs));
    }

    /**
     * function that multiplies a vector by a vector the result given number
     * @param v1
     * @return
     */
    public double dotProduct(Vector v1) {
        return this.xyz.d1 * v1.xyz.d1 + this.xyz.d2 * v1.xyz.d2 + this.xyz.d3 * v1.xyz.d3;
    }

    /**
     * function that calculates a vector product - the result given vector
     * @param v1
     * @return
     */
    public Vector crossProduct(Vector v1) {
        return new Vector(
                this.xyz.d2 * v1.xyz.d3 - this.xyz.d3 * v1.xyz.d2,
                this.xyz.d3 * v1.xyz.d1 - this.xyz.d1 * v1.xyz.d3,
                this.xyz.d1 * v1.xyz.d2 - this.xyz.d2 * v1.xyz.d1
        );
    }

    /**
     * Calculation of the squared length of the vector - (|vector|^2) = vector^2
     * @return
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * Calculate the length of the vector
     * @return
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * A normalization method that returns a new normalized vector-
     * (a unit vector in the same direction as the original vector)
     * @return
     */
    public Vector normalize() {
        double length = length();
        return new Vector(xyz.d1 / length, xyz.d2 / length, xyz.d3 / length);
    }

    // override toString function
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other)
               && super.equals(other);
    }




}
