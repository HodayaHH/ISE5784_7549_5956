package renderer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;
//import scene.Scene;

public class IntegrationTests {

    private Camera createTestCamera() {
        Point location = new Point(0, 0, 0);
        Vector to = new Vector(0, 0, -1);
        Vector up = new Vector(0, 1, 0);
        return Camera.getBuilder()
                .setLocation(location)
                .setDirection(to, up)
                .setViewPlaneSize(3, 3)
                .setViewPlaneDistance(1)
                .build();
    }

    private int countIntersections(Camera camera, Intersectable geometry, int nX, int nY) {
        int count = 0;
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Ray ray = camera.constructRay(nX, nY, j, i);
                if (geometry.findIntersections(ray) != null) {
                    count += geometry.findIntersections(ray).size();
                }
            }
        }
        return count;
    }

    @Test
    void testSphereIntersection() {
        Camera camera = createTestCamera();
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);

        int count = countIntersections(camera, sphere, 3, 3);
        assertEquals(2, count, "Expected 2 intersections with the sphere.");
    }

    @Test
    void testPlaneIntersection() {
        Camera camera = createTestCamera();
        Plane plane = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1));

        int count = countIntersections(camera, plane, 3, 3);
        assertEquals(9, count, "Expected 9 intersections with the plane.");
    }

    @Test
    void testTriangleIntersection() {
        Camera camera = createTestCamera();
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));

        int count = countIntersections(camera, triangle, 3, 3);
        assertEquals(1, count, "Expected 1 intersection with the triangle.");
    }
}

