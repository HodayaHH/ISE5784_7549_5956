package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final private Point myPint;
    final private Vector normal;

    /**
     * Parameter constructor
     * @param point1
     * @param normal
     */
    public Plane(Point point1, Vector normal) {
        this.myPint = point1;
        this.normal = normal.normalize();//So normalize and save in the field

    }

    /**
     * Parameter constructor
     * @param P1
     * @param P2
     * @param P3
     */
    public Plane(Point P1, Point P2, Point P3)
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
