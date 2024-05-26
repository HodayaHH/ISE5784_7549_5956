package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * An interface that realizes all the geometrics
 */
public interface Geometry {
    /**
     * A function that returns normal
     *
     * @param p1
     * @return
     */
    public Vector getNormal(Point p1);

}
