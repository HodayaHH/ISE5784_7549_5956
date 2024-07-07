package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a point light source.
 * A point light has a position in space and its intensity attenuates with distance.
 */
public class PointLight extends Light implements LightSource {

    protected Point position;
    private double Kc = 1.0; // Constant attenuation coefficient
    private double kl = 0.0;// Linear attenuation coefficient
    private double kq = 0.0;// Quadratic attenuation coefficient

    /**
     * Constructor that takes the light's color intensity and position.
     *
     * @param intensity The color intensity of the light.
     * @param position  The position of the light in space.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation coefficient.
     *
     * @param kc The constant attenuation coefficient.
     * @return The current instance of PointLight (for method chaining).
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    /**
     * Sets the linear attenuation coefficient.
     *
     * @param kl The linear attenuation coefficient.
     * @return The current instance of PointLight (for method chaining).
     */
    public PointLight setKl(double kl) {
        this.kl = kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient.
     *
     * @param kq The quadratic attenuation coefficient.
     * @return The current instance of PointLight (for method chaining).
     */
    public PointLight setKq(double kq) {
        this.kq = kq;
        return this;
    }

    /**
     * Gets the intensity of the light at a given point.
     * The intensity decreases with distance according to the attenuation coefficients.
     *
     * @param p The point at which to calculate the light intensity.
     * @return The color intensity of the light at the given point.
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double attenuation = Kc + kl * d + kq * d * d;
        // return getIntensity().reduce((int) attenuation);
        return getIntensity().scale(1 / attenuation);
    }

    /**
     * Gets the direction vector from the light source to a given point.
     *
     * @param p The point to which to calculate the direction from the light source.
     * @return The direction vector from the light source to the given point.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
