package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
/**
 * Abstract base class for ray tracing algorithms.
 * Provides the framework for tracing rays in a given scene and returning the resulting color.
 */
public abstract class RayTracerBase {
    /** The scene in which the ray tracing takes place */
    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with a specified scene.
     *
     * @param scene The scene in which the ray tracing occurs
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract method to trace a ray in the scene and determine its color.
     *
     * @param ray The ray to be traced
     * @return The color of the intersection point of the ray in the scene
     */
    public abstract Color traceRay(Ray ray);
}
