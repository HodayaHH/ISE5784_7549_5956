package renderer;

import primitives.*;

import java.security.cert.CertPathBuilder;
import java.util.MissingResourceException;

public class Camera implements Cloneable{
private  Vector vUp;// Vector representing the up direction of the camera
private  Vector vTo;// Vector representing the forward direction of the camera
private  Vector vRight;// Vector representing the right direction of the camera
private  Point location;// Location of the camera

private double viewPlaneWidth = 0.0;// Width of the view plane
private double viewPlaneHeight = 0.0;// Height of the view plane
private double viewPlaneDistance = 0.0;// Distance between the camera and the view plane

    /**
     * Empty constructor
     */
    private Camera() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray from the camera through a specific pixel on the view plane.
     *
     * @param nX Number of pixels in the X direction.
     * @param nY Number of pixels in the Y direction.
     * @param j  The X coordinate of the pixel.
     * @param i  The Y coordinate of the pixel.
     * @return The constructed ray.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        // Calculate the image center
       Point pc = location.add(vTo.scale(viewPlaneDistance));

        // Calculate the ratios
        double Ry = viewPlaneHeight / nY;
        double Rx = viewPlaneWidth / nX;

        // Calculate the pixel center
        double xj = (j - (nX - 1) / 2.0) * Rx;
        double yi = -(i - (nY - 1) / 2.0) * Ry;


        Point pij = pc;
        if (xj != 0) pij = pij.add(vRight.scale(xj));
        if (yi != 0) pij = pij.add(vUp.scale(yi));

        Vector vij = pij.subtract(location).normalize();

        return new Ray(location, vij);
    }


    /**
     *  internal class
     */
    public static class Builder {
        private final Camera camera;

        public Builder() {
            this.camera = new Camera();
        }


        /**
         * Sets the location of the camera.
         *
         * @param location The location point.
         * @return The Builder instance.
         */
        public Builder setLocation(Point location) {
            if (location == null) {
                throw new IllegalArgumentException("Location cannot be null.");
            }
            camera.location = location;
            return this;
        }

        /**
         * Sets the direction of the camera.
         *
         * @param to The forward direction vector.
         * @param up The upward direction vector.
         * @return The Builder instance.
         */
        public Builder setDirection(Vector to, Vector up) {
            if (to == null || up == null || !Util.isZero(to.dotProduct(up)) ) {
                throw new IllegalArgumentException("Invalid direction vectors.");
            }
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width  The width of the view plane.
         * @param height The height of the view plane.
         * @return The Builder instance.
         */
        public Builder setViewPlaneSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be positive.");
            }
            camera.viewPlaneWidth = width;
            camera.viewPlaneHeight = height;
            return this;
        }


        /**
         * Sets the distance between the camera and the view plane.
         *
         * @param distance The distance to the view plane.
         * @return The Builder instance.
         */
        public Builder setViewPlaneDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be positive.");
            }
            camera.viewPlaneDistance = distance;
            return this;
        }

        /**
         * Builds and returns a Camera object.
         *
         * @return The built Camera object.
         */
        public Camera build() {
            if (camera.location == null || camera.vTo == null || camera.vUp == null ||
                    camera.viewPlaneWidth == 0 || camera.viewPlaneHeight == 0 || camera.viewPlaneDistance == 0) {
                throw new MissingResourceException("Missing rendering data", Camera.class.getName(), "required field");
            }
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return camera.clone();
        }
    }

    @Override
    protected Camera clone() {
        try {
            return (Camera) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }
}
