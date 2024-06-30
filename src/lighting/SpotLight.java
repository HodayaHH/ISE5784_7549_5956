package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a spotlight source.
 * A spotlight has a specific direction and its intensity can be adjusted by a narrow beam effect.
 */
public class SpotLight extends PointLight {
    // The direction in which the spotlight is pointed
    private Vector direction;

    // The narrow beam angle effect, default is 1 (no effect)
    private double narrowBeamAngle = 1;

    /**
     * Sets the narrow beam angle in degrees.
     *
     * @param angleDegrees The angle of the narrow beam in degrees.
     * @return The current instance of SpotLight (for method chaining).
     */
    public SpotLight setNarrowBeam(double angleDegrees) {
        this.narrowBeamAngle = angleDegrees;
        return this;
    }

    /**
     * Constructor that takes the light's color intensity, position, and direction.
     *
     * @param intensity The color intensity of the light.
     * @param position  The position of the light in space.
     * @param direction The direction of the spotlight.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();// Normalize the direction vector
    }

    /**
     * Sets the constant attenuation coefficient.
     *
     * @param Kc The constant attenuation coefficient.
     * @return The current instance of SpotLight (for method chaining).
     */
    @Override
    public SpotLight setKc(double Kc) {
        super.setKc(Kc);
        return this;
    }

    /**
     * Sets the linear attenuation coefficient.
     *
     * @param Kl The linear attenuation coefficient.
     * @return The current instance of SpotLight (for method chaining).
     */
    @Override
    public SpotLight setKl(double Kl) {
        super.setKl(Kl);
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient.
     *
     * @param Kq The quadratic attenuation coefficient.
     * @return The current instance of SpotLight (for method chaining).
     */
    @Override
    public SpotLight setKq(double Kq) {
        super.setKq(Kq);
        return this;
    }

    /**
     * Gets the intensity of the light at a given point.
     * The intensity decreases with distance according to the attenuation coefficients.
     * The intensity is also adjusted by the narrow beam effect.
     *
     * @param p The point at which to calculate the light intensity.
     * @return The color intensity of the light at the given point.
     */
    @Override
    public Color getIntensity(Point p) {
        // Get the intensity from the point light (base class)
        Color pointLightIntensity = super.getIntensity(p);

        // Calculate the dot product of the spotlight direction and the direction to the point
        double factor = Math.max(0, direction.dotProduct(getL(p)));

        // Apply the narrow beam effect
        if (narrowBeamAngle != 1) {
            factor = Math.pow(factor, narrowBeamAngle);
        }
        // Scale the intensity by the factor
        return pointLightIntensity.scale(factor);

    }

}
