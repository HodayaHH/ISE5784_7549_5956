package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a directional light source
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructor that takes the light's color intensity and direction.
     * The direction vector is normalized to ensure consistency in calculations.
     *
     * @param intensity The color intensity of the light.
     * @param direction The direction vector of the light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();//++נרמלתי
    }

    /**
     * Returns the light intensity at a given point.
     * For directional light, the intensity is constant throughout the scene.
     *
     * @param p The point at which the intensity is calculated (not used for directional light).
     * @return The color intensity of the light.
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();// Return the constant intensity.
    }

    /**
     * Returns the direction of the light.
     * For directional light, this is the same regardless of the point in the scene.
     *
     * @param p The point at which the direction is calculated (not used for directional light).
     * @return The direction vector of the light.
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

//    @Override
//    public double getDistance(Point point) {
//        return Double.POSITIVE_INFINITY;
//    }
}
