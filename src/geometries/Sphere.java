package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * class representing a Sphere
 */
public class Sphere extends RadialGeometry {

    final private Point center;

    /**
     * Parameter constructor
     *
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * function getNormal
     *
     * @param p1
     * @return Normal vector of a sphere
     */
    @Override
    public Vector getNormal(Point p1) {
        return p1.subtract(center);

    }
}
