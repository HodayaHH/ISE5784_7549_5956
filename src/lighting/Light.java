package lighting;

import primitives.Color;

/**
 * Abstract class representing a generic light source.
 * This class serves as a base class for different types of light sources.
 */
public abstract class Light {

    protected Color intensity;

    /**
     * Constructor that initializes the light with a given color intensity.
     *
     * @param intensity The color intensity of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the color intensity of the light.
     *
     * @return The color intensity.
     */
    public Color getIntensity() {
        return intensity;
    }
}
