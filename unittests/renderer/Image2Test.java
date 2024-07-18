package renderer;

import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import geometries.*;

import static java.awt.Color.*;

public class Image2Test {
    /**
     * Scene of the test
     */
    private final Scene scene = new Scene("Diamond Ring Scene");

    /**
     * Camera builder of the test
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(0, 0, 200)) // Set camera location
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)) // Set camera direction
            .setViewPlaneDistance(100)
            .setViewPlaneSize(500, 500);

    /**
     * Produce a scene with a diamond ring model and render it into a png image
     */
    @Test
    public void renderDiamondRingTest() {
        // Add geometries to the scene
        scene.geometries.add(
                // Diamond model
                new Triangle(new Point(50, 150, -100), new Point(100, 100, -100), new Point(0, 100, -100)).setEmission(new Color(CYAN)),
                new Triangle(new Point(100, 100, -100), new Point(150, 150, -100), new Point(50, 150, -100)).setEmission(new Color(RED)),
                new Triangle(new Point(50, 150, -100), new Point(0, 100, -100), new Point(-50, 150, -100)).setEmission(new Color(YELLOW)),
                new Triangle(new Point(0, 100, -100), new Point(-100, 100, -100), new Point(-50, 150, -100)).setEmission(new Color(BLUE)),
                new Triangle(new Point(150, 150, -100), new Point(200, 100, -100), new Point(100, 100, -100)).setEmission(new Color(WHITE)),
                new Triangle(new Point(-150, 150, -100), new Point(-200, 100, -100), new Point(-100, 100, -100)).setEmission(new Color(WHITE)),
                new Triangle(new Point(-50, 150, -100), new Point(-100, 100, -100), new Point(-150, 150, -100)).setEmission(new Color(GREEN)),
                new Triangle(new Point(0, -150, -100), new Point(50, 50, -100), new Point(0, 100, -100)).setEmission(new Color(WHITE)),
                new Triangle(new Point(0, -150, -100), new Point(100, 100, -100), new Point(50, 50, -100)).setEmission(new Color(BLUE)),
                new Triangle(new Point(0, -150, -100), new Point(-50, 50, -100), new Point(-100, 100, -100)).setEmission(new Color(CYAN)),
                new Triangle(new Point(0, -150, -100), new Point(150, 150, -100), new Point(50, 50, -100)).setEmission(new Color(RED)),
                new Triangle(new Point(0, -150, -100), new Point(200, 100, -100), new Point(150, 150, -100)).setEmission(new Color(GREEN)),
                new Triangle(new Point(0, -150, -100), new Point(-150, 150, -100), new Point(-50, 50, -100)).setEmission(new Color(YELLOW)),
                new Triangle(new Point(0, -150, -100), new Point(-200, 100, -100), new Point(-150, 150, -100)).setEmission(new Color(BLUE)),
                new Triangle(new Point(0, -150, -100), new Point(-50, 50, -100), new Point(50, 50, -100)).setEmission(new Color(PINK))


        );

        // Add light sources to the scene
        scene.lights.add(
                new SpotLight(new Color(500, 300, 0), new Point(-150, 150, 0), new Vector(2, -2, -3)) // Example spot light
        );
        scene.lights.add(
                new PointLight(new Color(255, 0, 0), new Point(0, 200, 0)).setKl(0.00001).setKq(0.000001) // Example point light
        );

        // Set ambient light
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2)));

        // Build and render the image
        camera
                .setImageWriter(new ImageWriter("diamond_ring_render_test", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}