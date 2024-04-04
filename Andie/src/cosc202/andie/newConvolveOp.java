package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.util.ArrayList;

public class newConvolveOp {

    /**
     * <p>
     * Clamps the pixels given if the number is negative or above the expected
     * hieght and height.
     * </p
     */
    private static int clamp(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * <p>
     * Apply a kerenel to an image.
     * </p>
     * 
     * <p>
     * Scans every pixel in a 3 by 3 grid. Collects the red, green, blue and alpha
     * of the pixels. Once the pixels colour values have been collected apply each
     * respective parts of the kerenel to each respective pixel.
     * Add all the colour values to there respective colours. If the wanted filter
     * is negative then add 127 to the colour values. clamp the colour
     * values and convert to a argb value. apply the argb value to the image. repeat
     * for the entire image.
     * </p>
     * 
     * @param input The image to be convoled into a filter
     * @return The resulting image is convleved.
     */

    public static BufferedImage newConvolveOpMethod(Kernel kernel, BufferedImage input, boolean isNegative) {
        int width = input.getWidth();
        int height = input.getHeight();

        float[] kernelValues = kernel.getKernelData(null);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ArrayList<Integer> argbArray = new ArrayList<Integer>();

                for (int innerX = x - 1; innerX <= x + 1; innerX++) {
                    for (int innerY = y - 1; innerY <= y + 1; innerY++) {
                        argbArray.add(input.getRGB(
                                clamp(innerX, 0, width - 1),
                                clamp(innerY, 0, height - 1)));
                    }
                }
                if (!argbArray.isEmpty()) {
                    int transformedARGB[] = { 0, 0, 0, 0 };
                    int index = 0;
                    for (int argbKernel : argbArray) {
                        int a = (int) kernelValues[index] * ((argbKernel & 0xFF000000) >> 24);
                        int r = (int) kernelValues[index] * ((argbKernel & 0x00FF0000) >> 16);
                        int g = (int) kernelValues[index] * ((argbKernel & 0x0000FF00) >> 8);
                        int b = (int) kernelValues[index] * ((argbKernel & 0x000000FF));

                        transformedARGB[0] += a;
                        transformedARGB[1] += r;
                        transformedARGB[2] += g;
                        transformedARGB[3] += b;
                        index++;
                        if (index == 9) {
                            index = 0;
                        }
                    }

                    if (isNegative) {
                        transformedARGB[0] += 127;
                        transformedARGB[1] += 127;
                        transformedARGB[2] += 127;
                        transformedARGB[3] += 127;
                    }

                    transformedARGB[0] = clamp(transformedARGB[0], 0, 255);
                    transformedARGB[1] = clamp(transformedARGB[1], 0, 255);
                    transformedARGB[2] = clamp(transformedARGB[2], 0, 255);
                    transformedARGB[3] = clamp(transformedARGB[3], 0, 255);

                    int argb = (transformedARGB[0] << 24) | (transformedARGB[1] << 16) | (transformedARGB[2] << 8)
                            | transformedARGB[3];
                    result.setRGB(x, y, argb);
                } else {
                    System.out.println("argbArray is empty");
                }
            }
        }
        return result;

    }

}
