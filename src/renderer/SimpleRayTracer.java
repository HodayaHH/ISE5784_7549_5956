package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase{

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Abstract method to trace a ray in the scene and determine its color.
     *
     * @param ray The ray to be traced
     * @return The color of the intersection point of the ray in the scene
     */
    @Override
    public Color traceRay(Ray ray) {

//        var intersections = scene.geometries.findGeoIntersections(ray);
//        if (intersections == null) {
//            return scene.background;
//        }
//
//        Point closestPoint =ray.findClosestPoint(intersections);
//        return calcColor(closestPoint);

        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null
                ? scene.background
                : calcColor(ray.findClosestGeoPoint(intersections));
    }
    /**
     * Calculate the color of a point.
     * For now, this method only returns the ambient light color of the scene.
     *
     * @param geoPoint the point for which to calculate the color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint) {
       // return scene.ambientLight.getIntensity();

        return scene.ambientLight.getIntensity();
                //.add(gp.geometry.getEmission());
    }
}
