package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

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
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return scene.background;
        }

        Point closestPoint =ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }
    /**
     * Calculate the color of a point.
     * For now, this method only returns the ambient light color of the scene.
     *
     * @param geoPoint the point for which to calculate the color
     * @return the color of the point
     */
    private Color calcColor(Point geoPoint) {
        return scene.ambientLight.getIntensity();
    }
}
