package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {

    final private Point center;

    /**
     * Parameter constructor
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
}
