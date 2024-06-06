package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable{

    final List<Intersectable> geometries = new LinkedList<>();
    public Geometries() {
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    private void add(Intersectable... geometries) {

        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersectionPoints = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            List<Point> geometryIntersections = geometry.findIntersections(ray);
            if (geometryIntersections != null) {
                intersectionPoints.addAll(geometryIntersections);
            }
        }
        return intersectionPoints.isEmpty() ? null : intersectionPoints;

    }
}
