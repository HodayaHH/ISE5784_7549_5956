package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * class representing a Plane
 */
public class Plane extends Geometry {
    final private Point point;
    final private Vector normal;

    /**
     * Constructs a plane given a point on the plane and a normal vector to the plane.
     *
     * @param point1 a point on the plane
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
     * For a plane, the normal vector is constant everywhere, so the input point is not used
     * in the computation. The method simply returns the precomputed normal vector of the plane.
     *
     * @param p1 a point on the plane (not used in the computation)
     * @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }

    /**
     * Returns the normal vector of the plane.
     * This method simply returns the precomputed normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Finds the intersections of a given ray with the plane.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        // Calculate numerator: n * (Q - P0)
        double numerator = normal.dotProduct(point.subtract(ray.getHead()));

        // Calculate denominator: n * v
        double denominator = normal.dotProduct(ray.getDirection());

        // Check if the denominator is close to zero
        if (Util.isZero(denominator)) { // Util.isZero() is a method that checks if a value is very close to zero
            return null; // The ray and the plane are parallel, no intersection
        }

        // Calculate t
        //double t = numerator / denominator;
        double t = Util.alignZero(numerator / denominator);

        // Check if t is positive
        if (t > 0) {
            List<GeoPoint> intersections = new LinkedList<>();

            // Add intersection point to the list
            intersections.add(new GeoPoint(this,ray.getPoint(t)));
            return intersections;
        } else
            return null;
    }
}
