package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        // Fill the image with yellow color
        ImageWriter imageWriter = new ImageWriter("yellow", 800, 500);
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, new Color(YELLOW));
            }

        }

        //Draws a grid
        //Draws red grid lines
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j+=50) {
                    imageWriter.writePixel(i, j, new Color(RED));
                }
            }
        //Draws red grid columns
        imageWriter.writeToImage();
        for (int j = 0; j < imageWriter.getNy(); j++) {
            for (int i = 0; i < imageWriter.getNx(); i+=50) {
                imageWriter.writePixel(i, j, new Color(RED));
            }
        }
        // Write the image to a file
        imageWriter.writeToImage();
    }
}