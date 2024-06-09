package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * An interface that realizes all the geometrics
 */
public interface Geometry extends Intersectable {
    /**
     * computer normal
     *
     * @param p1 the point on the surface of the geometry where the normal is to be computed
     * @return the normal vector at the specified point
     */
    public Vector getNormal(Point p1);

}
