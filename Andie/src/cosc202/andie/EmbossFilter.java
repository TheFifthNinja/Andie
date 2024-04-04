package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.util.HashMap;

/**
 * <p>
 * ImageOperation to convert an image from colour to EmbossFilter.
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
 * @version 3.0
 */

public class EmbossFilter implements ImageOperation, java.io.Serializable {
    String direction;

    /**
     * <p>
     * Stores the direction that the user picks in direction.
     * </p>
     */
    EmbossFilter(String direction) {
        this.direction = direction;
    }

    /**
     * <p>
     * Apply Emboss Filter to an image.
     * </p>
     * 
     * <p>
     * Reads the direction the user decides and sends that kernel and the
     * bufferedImage to be convloved
     * </p>
     * 
     * @param input The image to be converted to EmbossFilter
     * @return The resulting EmbossFilter image.
     */

    public BufferedImage apply(BufferedImage input) {

        HashMap<String, float[]> embossDirections = new HashMap<String, float[]>();
        embossDirections.put(Andie.bundle.getString("rightEmboss"), new float[] { 0, 0, 0, 1, 0, -1, 0, 0, 0 });
        embossDirections.put(Andie.bundle.getString("topLeftEmboss"), new float[] { 1, 0, 0, 0, 0, 0, 0, 0, -1 });
        embossDirections.put(Andie.bundle.getString("topEmboss"), new float[] { 0, 1, 0, 0, 0, 0, 0, -1, 0 });
        embossDirections.put(Andie.bundle.getString("topRightEmboss"), new float[] { -1, 0, 0, 0, 0, 0, 0, 0, 1 });
        embossDirections.put(Andie.bundle.getString("leftEmboss"), new float[] { 0, 0, 0, -1, 0, 1, 0, 0, 0 });
        embossDirections.put(Andie.bundle.getString("bottomRightEmboss"), new float[] { -1, 0, 0, 0, 0, 0, 0, 0, 1 });
        embossDirections.put(Andie.bundle.getString("bottomEmboss"), new float[] { 0, -1, 0, 0, 0, 0, 0, 1, 0 });
        embossDirections.put(Andie.bundle.getString("bottomLeftEmboss"), new float[] { 0, 0, -1, 0, 0, 0, 1, 0, 0 });

        Kernel kernel = new Kernel(3, 3, embossDirections.get(direction));

        BufferedImage result = newConvolveOp.newConvolveOpMethod(kernel, input, true);

        return result;
    }

}
