package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 * ImageOperation to convert an image from colour to MedianFilter.
 * </p>
 * 
 * <p>
 * The images produced by this operation are blured based off the surrounding
 * pixels around it.
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
public class MedianFilter implements ImageOperation, java.io.Serializable {
    private int radius;

    /**
     * <p>
     * Stores the radius in radius.
     * </p>
     */
    MedianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Sets the defult radius as one.
     * </p>
     */
    MedianFilter() {
        this(1);
    }

    private static int clamp(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * <p>
     * Apply Median filter to an image.
     * </p>
     * 
     * <p>
     * Scans every pixel in the grid given. Collects the red, green, blue and alpha
     * and cordinates
     * of the pixels. Once the grid has been scanded averages all the colour values
     * and applles the
     * averages to the middle pixel. The grid then moves down one and repeats the
     * process. Once the
     * grid reaches the bottom it goes back to the top and then moves to the right
     * by one. This process
     * repeats until the whole image is blurred
     * </p>
     * 
     * @param input The image to be converted to MedianFilter
     * @return The resulting MedianFilter image.
     */

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ArrayList<Integer> argbArray = new ArrayList<Integer>();

                for (int innerX = x - radius; innerX <= x + radius; innerX++) {
                    for (int innerY = y - radius; innerY <= y + radius; innerY++) {
                        argbArray.add(input.getRGB(
                                clamp(innerX, 0, width - 1),
                                clamp(innerY, 0, height - 1)));
                    }
                }

                if (!argbArray.isEmpty()) {
                    ArrayList<Integer> aArray = new ArrayList<Integer>();
                    ArrayList<Integer> rArray = new ArrayList<Integer>();
                    ArrayList<Integer> gArray = new ArrayList<Integer>();
                    ArrayList<Integer> bArray = new ArrayList<Integer>();
                    for (int index = 0; index < argbArray.size() - 1; index++) {
                        aArray.add((argbArray.get(index) & 0xFF000000) >> 24);
                        rArray.add((argbArray.get(index) & 0x00FF0000) >> 16);
                        gArray.add((argbArray.get(index) & 0x0000FF00) >> 8);
                        bArray.add((argbArray.get(index) & 0x000000FF));
                    }
                    Collections.sort(aArray);
                    Collections.sort(rArray);
                    Collections.sort(gArray);
                    Collections.sort(bArray);

                    int avgA = aArray.get(aArray.size() / 2);
                    int avgR = rArray.get(aArray.size() / 2);
                    int avgG = gArray.get(aArray.size() / 2);
                    int avgB = bArray.get(aArray.size() / 2);

                    int argb = (avgA << 24) | (avgR << 16) | (avgG << 8) | avgB;

                    result.setRGB(x, y, argb);
                }
            }
        }
        return result;
    }
}
