package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class representing a Tube
 */
public class Tube extends RadialGeometry {
    final protected Ray ray;

    /**
     * Parameter constructor
     *
     * @param radius the radius of the tube
     * @param ray    the ray that defines the tube
     */
    public Tube(double radius, Ray ray) {
        super(radius);
        this.ray = ray;
    }

    /**
     * Computes the normal vector to the surface of the tube at a given
     *
     * @param p1 the point on the surface of the geometry where the normal is to be computed
     * @return the normal vector at the specified point
     */
    @Override
    public Vector getNormal(Point p1) {
        Vector vector = p1.subtract(ray.getHead());  //p-p0
        double DirectionT = ray.getDirection().dotProduct(vector); //vector*Direction

        //When connecting the point to the top of the beam
        //of the axis of the cylinder makes a right angle with the axis - the point "is in front of the head of the beam"
        if (DirectionT == 0) {
            throw new IllegalArgumentException("An extreme case when p-p0 is orthogonal to V");
        }
        // Point center = ray.getHead().add(ray.getDirection().scale(DirectionT)); עשינו ריפרקטורינג
        Point center = ray.getPoint(DirectionT);
        return p1.subtract(center).normalize();
    }

    /**
     * Finds the intersections of a given ray with the tube.
     *
     * @param ray the ray for which we want to find intersection points with the sphere
     * @return a list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
