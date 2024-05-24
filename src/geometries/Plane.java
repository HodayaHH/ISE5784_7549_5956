package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * class representing a Plane
 */
public class Plane implements Geometry {
    final private Point myPint;
    final private Vector normal;

    /**
     * Parameter constructor
     * @param point1
     * @param normal
     */
    public Plane(Point point1, Vector normal) {
        this.myPint = point1;
        this.normal = normal.normalize();//So normalize and save in the field

    }

    /**
     * Parameter constructor
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3)
    {
        this.myPint = p1;
        //Checking whether two points converge
        if(p1.equals(p2)||p2.equals(p3)||p3.equals(p1))
        {
            throw new IllegalArgumentException("The extreme case  Two or more points are identical");
        }
        Vector v1= p2.subtract(p1);
        Vector v2= p3.subtract(p1);
        Vector normal = v1.crossProduct(v2);

        // Checking if the points are on the same line
        if (normal.length() == 0) {
            throw new IllegalArgumentException("TExtreme case the points are on the same straight line");
        }
        this.normal = normal.normalize();
    }

    /**
     * function getNormal
     * @param p1
     * @return the normal field
     */
    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }

    /**
     * function getNormal
     * @return the normal field
     */
    public Vector getNormal() {
        return normal;
    }

}
