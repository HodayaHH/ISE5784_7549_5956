package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * The AmbientLight class represents the ambient light in a scene.
 * Ambient light is a fixed-intensity and fixed-color light that affects all objects in the scene equally.
 */
public class AmbientLight extends Light {

    // The intensity of the ambient light.
   // final private Color intensity;

    // A constant representing no ambient light.
    static public AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);


    /**
     * Constructor that initializes the ambient light with a color and an attenuation factor.
     * @param IA the base color of the ambient light.
     * @param KA the attenuation factor as a Double3.
     */
    public AmbientLight(Color IA, Double3 KA) {
        super(IA);
        this.intensity = IA.scale(KA);
    }

    /**
     * Constructor that initializes the ambient light with a color and an attenuation factor.
     * @param IA the base color of the ambient light.
     * @param KA the attenuation factor as a double.
     */
    public AmbientLight(Color IA, double KA, Color intensity) {
        super(IA);
        this.intensity = IA.scale(KA);
    }


}

