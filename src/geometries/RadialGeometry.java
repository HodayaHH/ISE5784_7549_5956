package geometries;

/**
 * An abstract class that contains a radius field
 */
public abstract class RadialGeometry implements Geometry {

    final protected double radius;

    /**
     * Parameter constructor
     *
     * @param radius
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
