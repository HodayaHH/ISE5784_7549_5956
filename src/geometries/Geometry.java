package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * An interface that realizes all the geometrics
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK; // The emission color of the geometry

    private Material material = new Material(); // The material properties of the geometry

    /**
     * Gets the material properties of the geometry.
     *
     * @return The material of the geometry.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material properties of the geometry.
     *
     * @param material The material to set.
     * @return The current geometry instance (for method chaining).
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the emission color of the geometry.
     *
     * @return The emission color of the geometry.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The emission color to set.
     * @return The current geometry instance (for method chaining).
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * computer normal
     *
     * @param p1 the point on the surface of the geometry where the normal is to be computed
     * @return the normal vector at the specified point
     */
    public abstract Vector getNormal(Point p1);

}
