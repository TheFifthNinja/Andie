package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.util.HashMap;

/**
 * <p>
 * ImageOperation to convert an image from colour to SobelFilter.
 * </p>
 * 
 * <p>
 * The images produced by this operation are blured based off the kernel picked.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Maka Ponia
 * @version 1.0
 */

public class SobelFilter implements ImageOperation, java.io.Serializable {
    String direction;

    /**
     * <p>
     * Stores the direction that the user picks in direction.
     * </p>
     */
    SobelFilter(String direction) {
        this.direction = direction;
    }

    /**
     * <p>
     * Apply Sobel Filter to an image.
     * </p>
     * 
     * <p>
     * Reads the direction the user decides and sends that kernel and the
     * bufferedImage to be convloved
     * </p>
     * 
     * @param input The image to be converted to SobelFilter
     * @return The resulting SobelFilter image.
     */

    public BufferedImage apply(BufferedImage input) {
        HashMap<String, float[]> sobelDirections = new HashMap<String, float[]>();
        sobelDirections.put(Andie.bundle.getString("rightSobel"),
                new float[] { -1 / 2.0f, 0, 1 / 2.0f, -1, 0, 1, -1 / 2.0f, 0, 1 / 2.0f });
        sobelDirections.put(Andie.bundle.getString("leftSobel"),
                new float[] { -1 / 2.0f, -1, -1 / 2.0f, 0, 0, 0, 1 / 2.0f, 1, 1 / 2.0f });
        Kernel kernel = new Kernel(3, 3, sobelDirections.get(direction));
        BufferedImage result = newConvolveOp.newConvolveOpMethod(kernel, input, true);
        return result;
    }

}
