package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
   final  protected Ray myRay;

    public Tube(double radius, Ray myRay) {
        super(radius);
        this.myRay = myRay;
    }

    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
}
