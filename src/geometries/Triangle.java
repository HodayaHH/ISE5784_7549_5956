package geometries;

import primitives.Point;
import primitives.Vector;

/**
 *  A class representing a triangle in three-dimensional space.
 */
public class Triangle extends Polygon {

    /**
     *
     *
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */
    public Triangle(Point p1,Point p2,Point p3) {
        super(p1,p2,p3);
    }

}
