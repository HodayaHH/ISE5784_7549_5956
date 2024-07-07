package primitives;

/**
 * The Material class represents the material properties of a geometry in the scene.
 * It includes the coefficients for specular reflection (Ks), diffuse reflection (Kd),
 * and the shininess factor for specular highlights (nShininess).
 */
public class Material {
    public Double3 Ks = Double3.ZERO;// Specular reflection coefficient
    public Double3 Kd = Double3.ZERO;// Diffuse reflection coefficient
    public int nShininess = 0;// Shininess factor for specular highlights

    /**
     * Sets the specular reflection coefficient (Ks).
     *
     * @param ks The specular reflection coefficient as a Double3.
     * @return The current Material object for chaining.
     */
    public Material setKs(Double3 ks) {
        this.Ks = ks;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (Kd).
     *
     * @param Kd The diffuse reflection coefficient as a Double3.
     * @return The current Material object for chaining.
     */
    public Material setKd(Double3 Kd) {
        this.Kd = Kd;
        return this;
    }

    /**
     * Sets the shininess factor for specular highlights (nShininess).
     *
     * @param nShininess The shininess factor.
     * @return The current Material object for chaining.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the specular reflection coefficient (Ks) using a double value.
     * Converts the double value to a Double3 object.
     *
     * @param Ks The specular reflection coefficient as a double.
     * @return The current Material object for chaining.
     */
    public Material setKs(double Ks) {
        this.Ks = new Double3(Ks);
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (Kd) using a double value.
     * Converts the double value to a Double3 object.
     *
     * @param Kd The diffuse reflection coefficient as a double.
     * @return The current Material object for chaining.
     */
    public Material setKd(double Kd) {
        this.Kd = new Double3(Kd);
        return this;
    }
}
