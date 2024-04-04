package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to brigton the image
 * </p>
 * 
 * <p>
 * The images produced by this have there total rgb increased by an amount
 * to make the image look brighter
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */
public class BrightnessFilter implements ImageOperation, java.io.Serializable {

    private int brightness;

    /**
     * <p>
     * Create a new BrightnessFilter operation.
     * </p>
     */
    BrightnessFilter() {
        this.brightness = 20;
    }

    BrightnessFilter(int brightness) {
        this.brightness = brightness;
    }

    /**
     * <p>
     * Apply BrightnessFilter conversion to an image.
     * </p>
     * 
     * <p>
     * The images produced by this have there total rgb increased by an amount
     * to make the image look brighter
     * </p>
     * 
     * @param input The image to be converted to BrightnessFilter
     * @return The resulting BrightnessFilter image.
     */
    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                r = brightonPixel(r);
                g = brightonPixel(g);
                b = brightonPixel(b);

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }

        return input;
    }

    /**
     * <p>
     * Apply BrightnessFilter conversion to an pixel.
     * and returns 255 if over 255
     * </p>
     * 
     * @param pixel The pixel to be converted to BrightnessFilter
     * @return The resulting pixel.
     */
    public int brightonPixel(int pixel) {
        int newPixel = (int) ((float) pixel - 127.5f + 127.5f * (1f + brightness / 100f));// int newPixel =
                                                                                          // (int)((1f+(content/100f))*((float)pixel-127.5f)+
                                                                                          // 127.5f*(1f+brightness/100f));
        if (newPixel > 255) {
            return 255;
        }

        if (newPixel < 0) {
            return 0;
        }

        return newPixel;
    }
}
