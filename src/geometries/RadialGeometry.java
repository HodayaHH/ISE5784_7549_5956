package geometries;

/**
 * An abstract class that represents geometric objects with a radius.
 */
public abstract class RadialGeometry extends Geometry {

    final protected double radius;

    /**
     * Constructs a RadialGeometry object with the specified radius.
     *
     * @param radius the radius of the geometric object
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
