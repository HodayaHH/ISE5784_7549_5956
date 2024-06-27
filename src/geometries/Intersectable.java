package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * An interface for finding intersections with the geometric entities
 */
public abstract class Intersectable {

    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList =findGeoIntersections(ray);
        return geoList==null?null
                :geoList.stream().map(geoPoint -> geoPoint.point).toList();
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Finds the intersection points of a ray with a sphere
     * The method calculates the points where the given ray intersects the sphere.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return List of intersection points of a ray with a sphere
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

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
