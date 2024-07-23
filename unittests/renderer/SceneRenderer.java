package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import geometries.*;
import lighting.*;
import renderer.*;

import static java.awt.Color.*;

public class SceneRenderer {
    Scene scene = new Scene("Creative Reflection and Transparency Scene");

    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(0, 0, 200)) // Camera location
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)) // Camera direction
            .setViewPlaneDistance(200)
            .setViewPlaneSize(200, 200);

    @Test
    public void renderCreativeImage() {
        // Create geometries with different materials for reflection and transparency
        scene.geometries.add(
                // Large sphere with transparency and reflection
                new Sphere(new Point(0, 0, -100), 60)
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.5).setkR(0.5)), // Transparency and Reflection

                // Smaller sphere with transparency and reflection positioned above the large sphere
                new Sphere(new Point(0, 70, -80), 30)
                        .setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.5).setkR(0.5)), // Transparency and Reflection

                // Large triangle with reflection positioned below the spheres
                new Triangle(new Point(-100, -100, -150), new Point(0, -50, -200), new Point(100, -100, -150))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkR(0.5)), // Reflection

                // Another triangle with reflection
                new Triangle(new Point(-70, -150, -100), new Point(70, -150, -100), new Point(0, -200, -150))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkR(0.5)), // Reflection

                // Plane with reflection
                new Plane(new Point(0, 0, -150), new Vector(0, 0, -1))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkR(0.5)) // Reflection
        );

        // Set lighting and background
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), new Double3(0.1, 0.1, 0.1)))
                .setBackground(new Color(0, 0, 0)); // Black background
        scene.lights.add(new DirectionalLight(new Color(150, 150, 150), new Vector(-1, -1, -1)));

        // Build camera and render image
        camera
                .setImageWriter(new ImageWriter("CreativeRender", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}