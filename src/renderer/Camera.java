package renderer;

import primitives.*;

import java.util.MissingResourceException;

public class Camera implements Cloneable {
    private Vector vUp;// Vector representing the up direction of the camera
    private Vector vTo;// Vector representing the forward direction of the camera
    private Vector vRight;// Vector representing the right direction of the camera
    private Point location;// Location of the camera

    private double viewPlaneWidth = 0.0;// Width of the view plane
    private double viewPlaneHeight = 0.0;// Height of the view plane
    private double viewPlaneDistance = 0.0;// Distance between the camera and the view plane

    private ImageWriter imageWriter;// Object responsible for writing images
    private RayTracerBase rayTracer;

    private int numOfSamples = 1; //Number of samples for antialiasing to improve the smoothing of curves.
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

        // Calculate size of the pixels
        double Ry = viewPlaneHeight / nY;
        double Rx = viewPlaneWidth / nX;

        // Calculate the pixel center
        double xj = (j - (nX - 1) / 2.0) * Rx;
        double yi = -(i - (nY - 1) / 2.0) * Ry;


        Point pij = pc;
        //at first, we will not create the 0 vector with the help of a test
        if (!Util.isZero(xj)) pij = pij.add(vRight.scale(xj));
        if (!Util.isZero(yi)) pij = pij.add(vUp.scale(yi));

        Vector vij = pij.subtract(location).normalize();

        return new Ray(location, vij);
    }

    /**
     * Casts a ray through a specific pixel and writes the resulting color to the image.
     *
     * @param nX Number of pixels in the X direction.
     * @param nY Number of pixels in the Y direction.
     * @param j  The X coordinate of the pixel.
     * @param i  The Y coordinate of the pixel.
     */
    private void castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * Renders the image by tracing rays through each pixel.
     *
     * @return The Camera instance for method chaining.
     * @throws MissingResourceException if ImageWriter or RayTracer is not set.
     */
    public Camera renderImage() {
        if (imageWriter == null || rayTracer == null) {
            throw new MissingResourceException("ImageWriter or RayTracer not set", Camera.class.getName(), "required field");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRays(nX, nY, j, i);
            }
        }
        return this;
    }

    /**
     * Prints a grid on the image with a specific color.
     *
     * @param interval The interval between grid lines.
     * @param color    The color of the grid lines.
     * @return The Camera instance for method chaining.
     * @throws MissingResourceException if ImageWriter is not set.
     */
    public Camera printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("ImageWriter not set", Camera.class.getName(), "required field");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Writes the image to a file.
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("ImageWriter not set", Camera.class.getName(), "required field");
        }

        imageWriter.writeToImage();
    }

    @Override
    protected Camera clone() {
        try {
            return (Camera) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    /**
     * internal class
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
            if (to == null || up == null || !Util.isZero(to.dotProduct(up))) {
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
         * Sets the ImageWriter for the camera.
         *
         * @param baseRender The ImageWriter to set.
         * @return The Builder instance.
         */
        public Builder setImageWriter(ImageWriter baseRender) {
            camera.imageWriter = baseRender;
            return this;
        }

        /**
         * Sets the RayTracer for the camera.
         *
         * @param simpleRayTracer The RayTracer to set.
         * @return The Builder instance.
         */
        public Builder setRayTracer(SimpleRayTracer simpleRayTracer) {
            camera.rayTracer = simpleRayTracer;
            return this;
        }
        /**
         * Sets the number of samples per pixel for antialiasing.
         * This parameter determines how many rays will be cast per pixel
         * to improve image quality by averaging multiple samples.
         *
         * @param numOfSamples The number of samples to be used per pixel.
         *                     Must be a positive integer greater than zero.
         * @return The Builder instance for method chaining.
         * @throws IllegalArgumentException if numOfSamples is less than or equal to zero.
         */
        public Builder setNumOfSamples(int numOfSamples) {
            if (numOfSamples <= 0) {
                throw new IllegalArgumentException("Number of samples must be positive.");
            }
            camera.numOfSamples = numOfSamples;
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
            // Ensure ImageWriter and RayTracer are set
            if (camera.imageWriter == null || camera.rayTracer == null) {
                throw new MissingResourceException("ImageWriter or RayTracer not set", Camera.Builder.class.getName(), "required field");
            }

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return camera.clone();
        }
    }


    /**
     * Casts multiple rays per pixel to achieve antialiasing by averaging the colors of the samples.
     *
     * @param nX     Number of columns of pixels in the image.
     * @param nY     Number of rows of pixels in the image.
     * @param pixelX X coordinate of the current pixel.
     * @param pixelY Y coordinate of the current pixel.
     */
    private void castRays(int nX, int nY, int pixelX, int pixelY) {
        if (numOfSamples == 1)
        {
            castRay(nX,nY,pixelX,pixelY);
        }

        else {
            Color accumulatedColor = Color.BLACK; // Initialize the accumulated color for the pixel.
            int totalSamples = numOfSamples * numOfSamples; // Total number of samples for the pixel.

            double sampleStep = 1.0 / numOfSamples; // Step size between sub-pixels.

            // Loop through each sub-pixel sample in the grid.
            for (int sampleColumn = 0; sampleColumn < numOfSamples; sampleColumn++) {
                for (int sampleRow = 0; sampleRow < numOfSamples; sampleRow++) {
                    // Calculate the offset required for each sub-pixel.
                    double subPixelOffsetX = (sampleColumn + 0.5) * sampleStep;
                    double subPixelOffsetY = (sampleRow + 0.5) * sampleStep;


                    // Convert sub-pixel offsets to integers.
                    int intOffsetX = (int) Math.round(subPixelOffsetX);
                    int intOffsetY = (int) Math.round(subPixelOffsetY);

                    // Construct the ray for the current sub-pixel.
                    Ray ray = constructRay(nX, nY, pixelX + intOffsetX, pixelY + intOffsetY);

                    // Trace the ray and get the color at the intersection point.
                    Color tracedColor = rayTracer.traceRay(ray);

                    // Add the traced color to the accumulated color.
                    accumulatedColor = accumulatedColor.add(tracedColor);
                }
            }

            // Average the accumulated color by the total number of samples.
            accumulatedColor = accumulatedColor.scale(1.0 / totalSamples);

            // Write the final averaged color to the image.
            imageWriter.writePixel(pixelX, pixelY, accumulatedColor);

        }

    }
}
