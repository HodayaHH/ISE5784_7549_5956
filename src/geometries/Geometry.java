package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * An interface that realizes all the geometrics
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    private Material material=new Material();

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Color getEmission() {
        return emission;
    }

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
