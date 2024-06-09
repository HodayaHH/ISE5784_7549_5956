package primitives;

/**
 * A class representing a point in three-dimensional space.
 */
public class Point {

    // A constant representing the origin point (0, 0, 0).
    public static final Point ZERO = new Point(Double3.ZERO);
    final protected Double3 xyz;

    /**
     * Constructs a Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param z the z-coordinate of the point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a Point with the specified Double3 object.
     *
     * @param xyz the Double3 object representing the coordinates of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    // override toString function
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }


    //override equals function
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }


    /**
     * Subtracts a point from this point to create a vector.
     *
     * @param p1 the point to subtract from this point
     * @return a new vector representing the difference between the points
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * Adds a vector to this point to create a new point.
     *
     * @param v1 the vector to add to this point
     * @return a new point representing the sum of this point and the vector
     */
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param p1 the other point
     * @return the squared distance between the two points
     */
    public double distanceSquared(Point p1) {
        return (this.xyz.d1 - p1.xyz.d1) * (this.xyz.d1 - p1.xyz.d1) +
                (this.xyz.d2 - p1.xyz.d2) * (this.xyz.d2 - p1.xyz.d2) +
                (this.xyz.d3 - p1.xyz.d3) * (this.xyz.d3 - p1.xyz.d3);
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param p1 the other point
     * @return the distance between the two points
     */
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }
}

