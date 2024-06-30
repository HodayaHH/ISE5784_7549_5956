package lighting;

import primitives.Color;
import primitives.*;

/**
 * Interface representing a light source in the scene.
 * Provides methods to get the intensity and direction of the light at a specific point.
 */
public interface LightSource {
    /**
     * Gets the intensity of the light at a given point.
     *
     * @param p The point at which to calculate the light intensity.
     * @return The color intensity of the light at the given point.
     */
    public Color getIntensity(Point p);

    /**
     * Gets the direction vector from the light source to a given point.
     *
     * @param p The point to which to calculate the direction from the light source.
     * @return The direction vector from the light source to the given point.
     */
    public Vector getL(Point p);

    // double getDistance(Point point);
}
