package primitives;

import java.util.Objects;

/**
 * class representing a point
 */
public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    final protected Double3 xyz;

    /**
     * Parameter constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Parameter constructor
     *
     * @param xyz
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
     * function that subtracts a point from another point
     *
     * @param p1
     * @return a new vector
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * Adding a vector to a point
     *
     * @param v1
     * @return a new point
     */
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * function that calculates the distance between two points in a square
     *
     * @param p1
     * @return
     */
    public double distanceSquared(Point p1) {
        return (this.xyz.d1 - p1.xyz.d1) * (this.xyz.d1 - p1.xyz.d1) +
                (this.xyz.d2 - p1.xyz.d2) * (this.xyz.d2 - p1.xyz.d2) +
                (this.xyz.d3 - p1.xyz.d3) * (this.xyz.d3 - p1.xyz.d3);
    }

    /**
     * function that calculates the distance between two points
     *
     * @param p1
     * @return
     */
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }
}

