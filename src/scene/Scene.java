package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    // Name of the scene
    public String sceneName;

    // Background color of the scene, defaults to black
    public Color background = Color.BLACK;

    // Ambient light in the scene, defaults to no light
    public AmbientLight ambientLight = AmbientLight.NONE;

    // Collection of geometries in the scene
    public Geometries geometries= new Geometries() ;


    /**
     * Constructor to create a scene with a given name.
     *
     * @param sceneName The name of the scene.
     */
    public Scene(String sceneName) {
        this.sceneName = sceneName;
    }


    /**
     * Sets the background color of the scene.
     *
     * @param background The background color to set.
     * @return The current Scene object (for method chaining).
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the name of the scene.
     *
     * @param sceneName The new name of the scene.
     * @return The current Scene object (for method chaining).
     */
    public Scene setSceneName(String sceneName) {
        this.sceneName = sceneName;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light to set.
     * @return The current Scene object (for method chaining).
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries in the scene.
     *
     * @param geometries The geometries to set.
     * @return The current Scene object (for method chaining).
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
