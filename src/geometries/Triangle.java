package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a triangle in three-dimensional space.
 */
public class Triangle extends Polygon {

    /**
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Finds the intersection points of a ray with the triangle.
     *
     * @param ray the ray for which we want to find intersection points with the triangle
     * @return a list of intersection points if they exist, otherwise null
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Find the intersection points of the ray with the plane of the triangle
        List<GeoPoint> planeIntersections = plane.findGeoIntersectionsHelper(ray);
        if (planeIntersections == null) {
            return null;// No intersection points with the plane, hence no intersection with the triangle
        }
        // Intersection point with the plane
        GeoPoint p = planeIntersections.get(0);

        Point p0 = ray.getHead();
        Vector v = ray.getDirection();

        // Create vectors from the ray origin to each vertex of the triangle
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        // Compute normal vectors for each edge
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Check if all normal vectors point in the same direction
        double d1 = Util.alignZero(v.dotProduct(n1));
        double d2 = Util.alignZero(v.dotProduct(n2));
        double d3 = Util.alignZero(v.dotProduct(n3));

        // If not all the scalar products have the same sign (all positive or all negative),
        // the intersection point is inside the triangle
        if (!(d1 > 0 && d2 > 0 && d3 > 0) && !(d1 < 0 && d2 < 0 && d3 < 0)) {
            return null;
        }

        List<GeoPoint> geoPointsTriangle = new LinkedList<>();
        geoPointsTriangle.add(new GeoPoint(this, planeIntersections.get(0).point));
        return geoPointsTriangle;
    }
}
