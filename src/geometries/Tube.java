package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * class representing a Tube
 */
public class Tube extends RadialGeometry {
    final protected Ray myRay;

    /**
     * Parameter constructor
     * @param radius
     * @param myRay
     */
    public Tube(double radius, Ray myRay) {
        super(radius);
        this.myRay = myRay;
    }

    @Override
    public Vector getNormal(Point p1) {
        Vector vector = p1.subtract(myRay.getHead());  //p-p0
        double DirectionT = myRay.getDirection().dotProduct(vector); //vector*Direction

        //When connecting the point to the top of the beam
        //of the axis of the cylinder makes a right angle with the axis - the point "is in front of the head of the beam"
        if(DirectionT==0)
        {
            throw new IllegalArgumentException("An extreme case when p-p0 is orthogonal to V");
        }
        Point center=myRay.getHead().add(myRay.getDirection().scale(DirectionT));
        return p1.subtract(center).normalize();
    }
}
