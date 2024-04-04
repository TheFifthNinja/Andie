package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to Contrast the image
 * </p>
 * 
 * <p>
 * The images produced by this have there total rgb increased by an amount
 * to make the image Contrasted
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */
public class ContrastFilter implements ImageOperation, java.io.Serializable {

    private int contrast;
    /**
     * <p>
     * Create a new ContrastFilter() {
 operation.
     * </p>
     */
    ContrastFilter() {
        this.contrast = 20;
    }

    ContrastFilter(int contrast) {
        this.contrast = contrast;
    }

    /**
     * <p>
     * Apply ContrastFilter conversion to an image.
     * </p>
     * 
     * <p>
     * The images produced by this have there total rgb increased by an amount
     * to make the image Contrasted
     * </p>
     * 
     * @param input The image to be converted to ContrastFilter
     * @return The resulting ContrastFilter image.
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                
               int argb = input.getRGB(x, y);
               int a = (argb & 0xFF000000) >> 24;
               int r = (argb & 0x00FF0000) >> 16;
               int g = (argb & 0x0000FF00) >> 8;
               int b = (argb & 0x000000FF);

                r = contrastPixel(r);
                g = contrastPixel(g);
                b = contrastPixel(b);
                

               argb = (a << 24) | (r << 16) | (g << 8) | b;
               input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    /** 
    * <p>
    * Apply ContrastFilter conversion to an pixel.
    * and returns 255 if over 255
    * and 0 if under 0
    * </p>
    * @param pixel The pixel to be converted to ContrastFilter
    * @return The resulting pixel.
    */
    public int contrastPixel(int pixel)
    {
        int newPixel = (int)((1f+(contrast/100f))*((float)pixel-127.5f)+ 127.5f);
        if (newPixel > 255)
        {
            return 255;
        }

        if (newPixel < 0)
        {
            return 0;
        }

        return newPixel;
    }
}
