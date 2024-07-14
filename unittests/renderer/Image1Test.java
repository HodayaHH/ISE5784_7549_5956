package renderer;

import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import geometries.*;

import static java.awt.Color.*;


public class Image1Test {
    /**
     * Scene of the test
     */
    private final Scene scene = new Scene("Diamond Ring Scene");
    /**
     * Camera builder of the test
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneDistance(100)
            .setViewPlaneSize(500, 500);

    /**
     * Produce a scene with a diamond ring model and render it into a png image
     */
    @Test
    public void renderDiamondRingTest() {

        scene.geometries.add(
                // First triangle
                new Triangle(new Point(0, 100, -100), new Point(50, 50, -100), new Point(-50, 50, -100)).setEmission(new Color(CYAN)),

                // Second triangle
                new Triangle(new Point(50, 50, -100), new Point(100, 100, -100), new Point(0, 100, -100)).setEmission(new Color(CYAN)),

                // Third triangle (inverted)
                new Triangle(new Point(0, 100, -100), new Point(-50, 50, -100), new Point(-100, 100, -100)).setEmission(new Color(CYAN)),

                // Fourth triangle (inverted)
                new Triangle(new Point(-50, 50, -100), new Point(-150, 50, -100), new Point(-100, 100, -100)).setEmission(new Color(CYAN)),

                // Fifth triangle (inverted)
                new Triangle(new Point(-100, 100, -100), new Point(-150, 50, -100), new Point(-200, 100, -100)).setEmission(new Color(CYAN))
        );

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2)));
        camera
                .setImageWriter(new ImageWriter("diamond ring render test", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage(); // Removed the printGrid function call to avoid the grid in the image
    }
}