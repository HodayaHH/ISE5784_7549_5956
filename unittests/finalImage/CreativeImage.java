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
/**
 * This department is responsible for creating and processing a creative image
 * With a complex scene consisting of several triangles, lights and materials
 * Simulate a structure resembling reflections and fragility.
 */
public class CreativeImage {

    private Scene scene = new Scene("Final Creative Scene");


    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(0, 0, 200))
            .setDirection(new Vector(0, -1, 0), new Vector(0, 0, 1))
            .setViewPlaneDistance(200)
            .setViewPlaneSize(500, 500);


    /**
     * Creates and renders an image with a complex structure of triangles
     * and multiple light sources to achieve realistic reflections and refractions.
     */
    @Test
    public void createImage() {


        scene.geometries.add(

                new Triangle(new Point(0, 0, 170), new Point(100, 0, 300), new Point(86, 49, 300)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.8).setkT(0.4).setKs(1.0).setKd(0.1).setShininess(2000)),
                new Triangle(new Point(0, 0, 170), new Point(50, 87, 300), new Point(86, 49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(0, 0, 170), new Point(50, 87, 300), new Point(0, 100, 300)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.2).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 0, 170), new Point(-50, 87, 300), new Point(0, 100, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(0, 0, 170), new Point(-50, 87, 300), new Point(-86, 49, 300)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.2).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 0, 170), new Point(-100, 0, 300), new Point(-86, 49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(0, 0, 170), new Point(-50, -87, 300), new Point(-86, -49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 0, 170), new Point(50, -87, 300),  new Point(0, -100, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(0, 0, 170), new Point(-50, -87, 300),  new Point(0, -100, 300)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.2).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 0, 170), new Point(50, -87, 300),  new Point(0, -100, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(0, 0, 170), new Point(50, -87, 300), new Point(86, -49, 300)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.2).setkT(0.2).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 0, 170), new Point(100, 0, 300), new Point(86, -49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.8).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(100, 0, 300), new Point(50, 0, 350), new Point(86, 49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(50, 0, 350), new Point(86, 49, 300), new Point(25, 43, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(86, 49, 300), new Point(25, 43, 350), new Point(50, 87, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(50, 87, 300), new Point(25, 43, 350), new Point(0, 100, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(25, 43, 350), new Point(0, 100, 300), new Point(-25, 43, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(0, 100, 300), new Point(-25, 43, 350), new Point(-50, 87, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(-50, 87, 300), new Point(-25, 43, 350), new Point(-86, 49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(-25, 43, 350), new Point(-86, 49, 300),  new Point(-50, 0, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(-86, 49, 300),  new Point(-50, 0, 350), new Point(-100, 0, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(-100, 0, 300),  new Point(-50, 0, 350), new Point(-86, -49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle( new Point(-50, 0, 350), new Point(-86, -49, 300), new Point(-25, -43, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(-86, -49, 300), new Point(-25, -43, 350), new Point(-50, -87, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(-50, -87, 300), new Point(-25, -43, 350),  new Point(0, -100, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(-25, -43, 350),  new Point(0, -100, 300), new Point(25, -43, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle( new Point(0, -100, 300), new Point(25, -43, 350), new Point(50, -87, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),

                new Triangle(new Point(50, -87, 300), new Point(25, -43, 350), new Point(86, -49, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(25, -43, 350), new Point(86, -49, 300), new Point(50, 0, 350)).setEmission(new Color(PINK)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000)),
                new Triangle(new Point(86, -49, 300), new Point(50, 0, 350), new Point(100, 0, 300)).setEmission(new Color(BLUE)).setMaterial(new Material().setkR(0.4).setkT(0.6).setKs(0.8).setKd(0.2).setShininess(1000))
        );

        // Adding lights to the scene
        scene.lights.add(
                new SpotLight(new Color(600, 300, 300), new Point(80, 50, 0), new Vector(0, 0, -1))
                        .setKl(3E-5).setKq(1.5E-7)
        );

        scene.lights.add(
                new SpotLight(new Color(120, 180, 255), new Point(-150, -120, 250), new Vector(1, 1, -2))
                        .setKl(2E-5).setKq(1E-7)
        );

        // Adding point light with specific position and color
        scene.lights.add(
                new PointLight(new Color(100, 200, 100), new Point(-80, -350, 700)).setKq(0.000002)
        );

        // Adding ambient light
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2)));

        // Rendering the scene
        camera
                .setImageWriter(new ImageWriter("CreativeImage", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
