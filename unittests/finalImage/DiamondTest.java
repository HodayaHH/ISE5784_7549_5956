package finalImage;

import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;

public class DiamondTest {
    /**
     * Scene of the test
     */
    private final Scene scene = new Scene("Diamond Ring Scene");

    /**
     * Camera builder of the test
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(0, 0, 200))
            .setDirection(new Vector(0, -1, 0), new Vector(0, 0, 1))
            .setViewPlaneDistance(200)
            .setViewPlaneSize(500, 500);

    /**
     * Produce a scene with a diamond ring model and render it into a png image
     */
    @Test
    public void renderDiamondRingTest() {
        // Adding triangles representing the diamond ring
        scene.geometries.add(
                // Top pyramid part
                new Triangle(new Point(50, 150, -100), new Point(100, 100, -100), new Point(0, 100, -100))
                        .setEmission(new Color(CYAN))
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setkR(0.4)
                                .setkT(0.2)
                                .setShininess(1000)
                        ),

                new Triangle(new Point(100, 100, -100), new Point(150, 150, -100), new Point(50, 150, -100))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(50, 150, -100), new Point(0, 100, -100), new Point(-50, 150, -100))
                        .setEmission(new Color(YELLOW)),

                new Triangle(new Point(0, 100, -100), new Point(-100, 100, -100), new Point(-50, 150, -100))
                        .setEmission(new Color(BLUE)),

                new Triangle(new Point(150, 150, -100), new Point(200, 100, -100), new Point(100, 100, -100))
                        .setEmission(new Color(WHITE)),

                new Triangle(new Point(-150, 150, -100), new Point(-200, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(WHITE)),

                new Triangle(new Point(-50, 150, -100), new Point(-100, 100, -100), new Point(-150, 150, -100))
                        .setEmission(new Color(GREEN)),

                // Bottom triangles
                new Triangle(new Point(0, -150, -100), new Point(50, 50, -100), new Point(0, 100, -100))
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setkR(0.4)
                                .setkT(0.2)
                                .setShininess(1000)
                        ),

                new Triangle(new Point(0, -150, -100), new Point(100, 100, -100), new Point(50, 50, -100))
                        .setEmission(new Color(BLUE)),

                new Triangle(new Point(0, -150, -100), new Point(-50, 50, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(CYAN)),

                // Additional triangles to complete the diamond
                new Triangle(new Point(0, -150, -100), new Point(150, 150, -100), new Point(50, 50, -100))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(0, -150, -100), new Point(200, 100, -100), new Point(150, 150, -100))
                        .setEmission(new Color(GREEN)),

                new Triangle(new Point(0, -150, -100), new Point(-150, 150, -100), new Point(-50, 50, -100))
                        .setEmission(new Color(YELLOW)),

                new Triangle(new Point(0, -150, -100), new Point(-200, 100, -100), new Point(-150, 150, -100))
                        .setEmission(new Color(BLUE)),

                new Triangle(new Point(0, -150, -100), new Point(-50, 50, -100), new Point(50, 50, -100))
                        .setEmission(new Color(PINK)),

                new Triangle(new Point(0, 150, -100), new Point(50, 50, -100), new Point(100, 100, -100))
                        .setEmission(new Color(BLUE))
        );


        // Adding lights to the scene
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7)
        );

        scene.lights.add(
                new SpotLight(new Color(83, 122, 195), new Point(-100, -100, 200), new Vector(1, 1, -3))
                        .setKl(1E-5).setKq(1.5E-7)
        );

        scene.lights.add(
                new PointLight(new Color(83, 122, 195), new Point(-100, -400, 800)).setKq(0.000001)
        );

        // Setting ambient light
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2)));

        // Rendering the scene
        camera
                .setImageWriter(new ImageWriter("Diamond", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage(); // Removed the printGrid function call to avoid the grid in the image
    }
}