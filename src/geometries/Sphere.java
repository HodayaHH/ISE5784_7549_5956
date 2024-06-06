package geometries;


import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {

    final private Point center;

    /**
     * Constructs a Sphere with the specified center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Computes the normal vector to the surface of the sphere at a given point.
     *
     * @param p1 point on the surface of the sphere
     * @return  the normal vector at the specified point
     */
    @Override
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();

    }

    /**
     * Finds the intersections of a given ray with the sphere.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return a list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector u=center.subtract(ray.getHead());
        double tm=ray.getDirection().dotProduct(u);
        double dSquared =u.lengthSquared() - tm * tm;
        if (Util.alignZero(dSquared - this.radius * this.radius) >= 0)
            return null;

        double th =Util.alignZero(Math.sqrt(this.radius * this.radius - dSquared));
        double t1 = tm + th;
        double t2 = tm - th;

        if (Util.alignZero(t1) <= 0 && Util.alignZero(t2) <= 0)
            return null;
        List<Point> intersections = new LinkedList<>();

        if(Util.alignZero(t1) > 0 )
            intersections.add(ray.getHead().add(ray.getDirection().scale(t1)));
        if(Util.alignZero(t2) > 0 )
            intersections.add(ray.getHead().add(ray.getDirection().scale(t2)));

        return intersections;

    }
}
