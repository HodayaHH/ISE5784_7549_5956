package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    final private double height;

    public Cylinder(double radius, Ray myRay, double height) {
        super(radius, myRay);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
}
