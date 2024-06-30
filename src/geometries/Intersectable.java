package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * An abstract class for geometric entities that can be intersected by rays.
 * This class provides methods to find intersections with rays, both returning points and geo-points.
 */
public abstract class Intersectable {
    /**
     * Finds the intersection points of a ray with the geometry.
     *
     * @param ray The ray for which we want to find intersection points.
     * @return A list of intersection points, or null if there are no intersections.
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList =findGeoIntersections(ray);
        return geoList==null?null
                :geoList.stream().map(geoPoint -> geoPoint.point).toList();
    }
    /**
     * Finds the intersection geo-points of a ray with the geometry.
     *
     * @param ray The ray for which we want to find intersection geo-points.
     * @return A list of intersection geo-points, or null if there are no intersections.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Abstract method to find the intersection geo-points of a ray with the geometry.
     * This method should be implemented by subclasses to provide the specific intersection logic.
     *
     * @param ray The ray for which we want to find intersection geo-points.
     * @return A list of intersection geo-points, or null if there are no intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * An internal class that represents an intersection point between a ray and a geometric entity.
     * This class contains both the geometry and the intersection point.
     */
    public static class GeoPoint {
        public Geometry geometry;// The geometry that was intersected
        public Point point;// The point of intersection
        /**
         * Constructor for GeoPoint.
         *
         * @param geometry The geometry that was intersected.
         * @param point The point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof GeoPoint other)) return false;
            if (this.geometry != other.geometry) return false; // בדיקת השוויון של הגופים
            return Objects.equals(this.point, other.point); // בדיקת השוויון של הנקודות
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

}
