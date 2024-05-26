package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class representing a Plane
 */
public class Plane implements Geometry {
    final private Point point;
    final private Vector normal;

    /**
     * Constructs a plane given a point on the plane and a normal vector to the plane.
     *
     * @param point1  a point on the plane
     * @param normal the normal vector to the plane will be normalized and stored
     */
    public Plane(Point point1, Vector normal) {
        this.point = point1;
        this.normal = normal.normalize();//So normalize and save in the field

    }

    /**
     * Constructs a plane given three non-collinear points on the plane.
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.point = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *For a plane, the normal vector is constant everywhere, so the input point is not used
     *in the computation. The method simply returns the precomputed normal vector of the plane.
     *
     * @param p1 a point on the plane (not used in the computation)
     * @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }

    /**
     *Returns the normal vector of the plane.
     *This method simply returns the precomputed normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     *Finds the intersections of a given ray with the plane.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return a list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
