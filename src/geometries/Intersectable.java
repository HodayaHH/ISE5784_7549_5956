package geometries;

import primitives.*;

import java.util.List;

/**
 * An interface for finding intersections with the geometric entities
 */
public interface Intersectable {
    /**
     * Finds the intersection points of a ray with a sphere
     * The method calculates the points where the given ray intersects the sphere.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return List of intersection points of a ray with a sphere
     */
    List<Point> findIntersections(Ray ray);
}
