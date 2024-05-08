package geometries;

public abstract class RadialGeometry implements  Geometry{

    final protected double radius;

    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
