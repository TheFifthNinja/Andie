package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter to an image
 * </p>
 * 
 * <p>
 * A Sharpen filter sharpens an image by increasing edge contrast, and can be
 * implemented by a convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Jayden Smith
 * @version 2.0
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    SharpenFilter() {

    }

    /**
     * <p>
     * Apply a Sharpen filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Sharpen filter is implemented via convolution.
     * </p>
     * 
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        // The values for the kernel as a 9-element array
        float[] array = { 0, -1 / 2.0f, 0,
                -1 / 2.0f, 3.0f, -1 / 2.0f,
                0, -1 / 2.0f, 0 };
        // Make a 3x3 kernel from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage newImage = new BufferedImage(width + 2, height + 2, input.getType());// create a new image that is
                                                                                           // double the size
        Graphics g = newImage.getGraphics();// make new image a graphic which is now twice as big
        g.drawImage(input, 1, 1, null); // draws the input image to the larger new Image area
        g.dispose();

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(newImage, output);

        return output;
    }
}
