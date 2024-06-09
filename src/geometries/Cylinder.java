package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * class representing a Cylinder
 */
public class Cylinder extends Tube {
    final private double height;

    /**
     * Constructs a cylinder with a specified radius, central axis, and height.
     *
     * @param radius the radius of the base of the cylinder
     * @param ray    ray the central axis of the cylinder represented by a Ray object
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray ray, double height) {
        super(radius, ray);
        this.height = height;
    }

    /**
     * A function that returns normal
     *
     * @param p1 the point on the surface of the cylinder where the normal is to be computed
     * @return normal at the specified point, currently returns null
     */
    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
}
