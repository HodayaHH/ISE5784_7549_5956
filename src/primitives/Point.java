package primitives;

import java.util.Objects;

//class representing a point
public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    final protected Double3 xyz;

    //    //Parameter constructor
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    //Parameter constructor
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

    //?????????????????????????/
    //override equals function
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;

        return Objects.equals(xyz, point.xyz);
    }


    //function that subtracts a point from another point, meaning a new vector is created
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    //Adding a vector to a point - returns a new point
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    //function that calculates the distance between two points in a square
    public double distanceSquared(Point p1) {
        return Math.pow(this.xyz.d1 - p1.xyz.d1, 2) + Math.pow(this.xyz.d2 - p1.xyz.d2, 2) + Math.pow(this.xyz.d3 - p1.xyz.d3, 2);
    }

    //function that calculates the distance between two points
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }
}

