package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    final private double height;

    public Cylinder(double radius, Ray myRay, double height) { //Parameter constructor
        super(radius, myRay);
        this.height = height;
    }

    //A function that returns normal
    @Override
    public Vector getNormal(Point p1)  {
        return null;
    }
}
