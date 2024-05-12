package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final private Point myPint;
    final private Vector normal;

    public Plane(Point point1, Vector normal) {
        this.myPint = point1;
        if (normal.length() != 1)//Checking whether the length is not equal 1
        {
            this.normal = normal.normalize();//So normalize and save in the field
        } else {
            this.normal = normal;
        }

    }

    public Plane(Point P1, Point P2, Point P3)//Parameter constructor
    {
        this.myPint = P1;
        this.normal = null;
    }

    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }
}
