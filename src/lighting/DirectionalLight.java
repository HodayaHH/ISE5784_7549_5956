package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a directional light source
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     *  The direction of the light.
     */
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
        this.direction = direction.normalize();
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

    /**
     * Gets the distance from the light source to a given point.
     * This implementation returns positive infinity, indicating that the light source is considered to be at an infinite distance.
     *
     * @param point The point to which to calculate the distance from the light source.
     * @return Positive infinity, indicating the light source is at an infinite distance.
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
