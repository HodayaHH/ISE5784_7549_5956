package primitives;

/**
 * class representing a vector
 */
public class Vector extends Point {

    /**
     * Constructs a Vector with the specified coordinates.
     *
     * @param x the x-coordinate of the vector
     * @param y the y-coordinate of the vector
     * @param z the z-coordinate of the vector
     * @throws IllegalArgumentException if the zero vector is provided
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("The zero vector is not possible");
        }
    }

    /**
     * Constructs a Vector with the specified Double3 object.
     *
     * @param xyz the Double3 object representing the coordinates of the vector
     * @throws IllegalArgumentException if the zero vector is provided
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))//Checking if the value is the zero vector
        {
            throw new IllegalArgumentException("The zero vector is not possible");
        }

    }

    /**
     * Adds another vector to this vector.
     *
     * @param v1 the vector to add
     * @return a new vector representing the sum of this vector and the given vector
     */
    public Vector add(Vector v1) {
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * Scales this vector by a scalar value.
     *
     * @param rhs the scalar value to scale by
     * @return a new vector representing the scaled vector
     */
    public Vector scale(double rhs) {
        return new Vector(xyz.scale(rhs));
    }

    /**
     * Calculates the dot product of this vector and another vector.
     *
     * @param v1 the other vector
     * @return the dot product of the two vectors
     */
    public double dotProduct(Vector v1) {
        return this.xyz.d1 * v1.xyz.d1 + this.xyz.d2 * v1.xyz.d2 + this.xyz.d3 * v1.xyz.d3;
    }
    /**
     * Calculates the cross product of this vector and another vector.
     *
     * @param v1 the other vector
     * @return a new vector representing the cross product of the two vectors
     */
    public Vector crossProduct(Vector v1) {
        return new Vector(
                this.xyz.d2 * v1.xyz.d3 - this.xyz.d3 * v1.xyz.d2,
                this.xyz.d3 * v1.xyz.d1 - this.xyz.d1 * v1.xyz.d3,
                this.xyz.d1 * v1.xyz.d2 - this.xyz.d2 * v1.xyz.d1
        );
    }

    /**
     * Calculates the squared length of this vector.
     *
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * Calculates the length of this vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector.
     *
     * @return a new normalized vector (a unit vector in the same direction as this vector)
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
