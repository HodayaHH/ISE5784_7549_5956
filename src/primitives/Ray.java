package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;


/**
 * class representing a Ray
 */
public class Ray {
    // The starting point of the ray.
    final private Point head;
    // The direction vector of the ray.
    final private Vector direction;

    /**
     * Constructs a Ray with the specified head point and direction vector.
     *
     * @param head      the starting point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize(); //Normalizes the direction vector
    }

    //Override function toString
    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    //override equals function
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    /**
     * Returns the head point of the Ray.
     *
     * @return the head point of the Ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector of the Ray.
     *
     * @return the direction vector of the Ray
     */
    public Vector getDirection() {
        return direction;
    }


    /**
     * Calculates a point on the beam at a given distance from its origin.
     *
     * @param t the distance from the beam head. can be any real number.
     *          If t is zero, the beam head is returned.
     *          If t is positive, a point is returned in the direction of the beam.
     *          If t is negative, a point is returned in the opposite direction of the beam.
     * @return the point on the beam at a given distance t from the head.
     */
    public Point getPoint(double t) {
        if (Util.isZero(t))
            return head;
        return head.add(direction.scale(t));

    }

    /**
     * Finds the closest point to the head of the ray from a list of points.
     * Uses squared distances for efficiency.
     *
     * @param points The list of points to search.
     * @return The closest point to the head of the ray, or null if the list is empty or null.
     */

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {

        if (points == null || points.isEmpty()) {
            return null;
        }
        //Initialize with the first point in the list
        GeoPoint closestPoint = points.get(0);
        double closestDistanceSquared = closestPoint.point.distanceSquared(head);

       //Iterate through the rest of the points to find the closest one
        for (int i = 1; i < points.size(); i++) {
            GeoPoint point = points.get(i);
            double distanceSquared = point.point.distanceSquared(head);

            // Update closestPoint if we find a closer point
            if (distanceSquared < closestDistanceSquared) {
                closestPoint = point;
                closestDistanceSquared = distanceSquared;
            }
        }
        // Return the closest point found
        return closestPoint;
    }
}
