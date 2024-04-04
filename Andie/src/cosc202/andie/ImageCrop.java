package cosc202.andie;

import java.awt.image.BufferedImage;

public class ImageCrop extends ShapedOperation {
    private int x1, x2, y1, y2 = 0;

    public ImageCrop() {
    }

    /**
     * Sets the points x,y,x2,y2
     * 
     * @param x,y,x2,y2 the points for the class
     */
    public void setPoints(int x, int y, int x2, int y2) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Crops image with dimensions from square drawn over image
     * 
     * @param image Image to crop.
     * @return the resulting cropped image.
     */
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage croppedImage = image.getSubimage(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
                Math.abs(y1 - y2));
        return croppedImage;
    }
}