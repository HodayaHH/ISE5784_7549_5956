package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

public class Geometries extends Intersectable {
    // List to store all the geometric objects
    final List<Intersectable> geometries = new LinkedList<>();

    // Default constructor
    public Geometries() {
    }

    // Constructor that accepts multiple geometric objects
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    // Private method to add multiple geometric objects to the list
    public void add(Intersectable... geometries) {

        Collections.addAll(this.geometries, geometries);// Add all geometries to the list
    }

    /**
     * Finds the intersection points of a ray with all the geometric objects.
     *
     * @param ray the ray for which we want to find intersection points with the geometries
     * @return a list of intersection points if they exist, otherwise null
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // List to store intersection points
        List<GeoPoint> intersectionPoints = new LinkedList<>();
        // Iterate through all geometries and find intersections with the ray
        for (Intersectable geometry : geometries) {
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersectionsHelper(ray);
            if (geometryIntersections != null) {
                intersectionPoints.addAll(geometryIntersections);
            }
        }
        // Return the list of intersection points if not empty, otherwise return null
        return intersectionPoints.isEmpty() ? null : intersectionPoints;

    }
}
