package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * An interface that realizes all the geometrics
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

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
