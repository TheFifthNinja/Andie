package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * Interface for operations to be applied to shapes.
 * </p>
 * 
 * <p>
 * Classes implementing ShapedOperation represent operations that use the area selecter.
 * Each operation will wait until the mouse has be draged and clicked
 * </p>
 * 
 * <p>
 * It implements {@link ImageOperation} as each is still an operations which can be undone 
 * and be placed into the ops.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */

public abstract class ShapedOperation implements ImageOperation {
    /**
     *  This need to be here so when the mouse stop draging we can grab the current points
     * @param x point one x
     * @param y point one y
     * @param x2 point two x
     * @param y2 point two y
     */
    public void setPoints(int x,int y,int x2,int y2)
    {

    }

    /** Same as {@link ImageOperation} 
     * @param image The image to apply the operation to
     * @return The image resulting from the operation
     */
    public BufferedImage apply(BufferedImage image) {
        return image;
    }
}