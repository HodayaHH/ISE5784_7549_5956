package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
   final private Point myPint;
   final  private Vector normal;

    public Plane(Point point1, Vector normal) {
        this.myPint = point1;
        if (normal.length()!=1)
        {
            this.normal = normal.normalize();//בדיקה שהנורמל מנורמל אחרת לנרמל
        }
        else
        {
            this.normal= normal;
        }

    }
    public Plane(Point P1, Point P2, Point P3)
    {
        this.myPint = P1;
        this.normal=null;
    }

    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }
}
