package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

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
        return null;
    }
}
