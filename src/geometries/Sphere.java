package geometries;


import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {

    final private Point center;

    /**
     * Constructs a Sphere with the specified center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Computes the normal vector to the surface of the sphere at a given point.
     *
     * @param p1 point on the surface of the sphere
     * @return the normal vector at the specified point
     */
    @Override
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();

    }

    /**
     * Finds the intersections of a given ray with the sphere.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Vector from the ray's origin to the center of the sphere
        Vector u = center.subtract(ray.getHead());

        // Projection of vector u onto the ray's direction vector
        double tm = ray.getDirection().dotProduct(u);

        // Squared distance from the sphere's center to the ray
        double dSquared = u.lengthSquared() - tm * tm;

        // Check if the ray misses the sphere
        if (Util.alignZero(dSquared - this.radius * this.radius) >= 0)
            return null;// No intersection points

        // Distance from the projection point to the intersection points
        double th = Util.alignZero(Math.sqrt(this.radius * this.radius - dSquared));

        // t1 and t2 are the distances from the ray's origin to the intersection points
        double t1 = tm + th;
        double t2 = tm - th;

        // Check if both intersection points are behind the ray's origin
        if (Util.alignZero(t1) <= 0 && Util.alignZero(t2) <= 0)
            return null;// No intersection points in the positive direction of the ray

        // List to store intersection points
        List<GeoPoint> intersections = new LinkedList<>();

        // Add the intersection points to the list if they are in front of the ray's origin
        if (Util.alignZero(t1) > 0)
            intersections.add(new GeoPoint(this,ray.getPoint(t1)));
        if (Util.alignZero(t2) > 0)
            intersections.add(new GeoPoint(this,ray.getPoint(t2)));

        return intersections;// Return the list of intersection points

    }
}
