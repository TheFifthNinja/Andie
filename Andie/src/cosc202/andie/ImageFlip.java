package cosc202.andie;

import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class ImageFlip implements ImageOperation, java.io.Serializable {
    private int number;

    /**
     * Constructor to set data field to flip image vertically
     * 
     */
    public ImageFlip(int number) {
        this.number = number;
    }

    /**
     * Constructor to set data field to flip image horizontally
     * 
     */
    public ImageFlip() {
    }

    /**
     * <p>
     * Flips the image either veritacally or horizontally.
     * </p>
     * 
     * <p>
     * Image Flip works with Affine Transform.
     * </p>
     * 
     * @param input The image to flip.
     * @return The resulting flip image.
     */
    public BufferedImage apply(BufferedImage image) {
        if (number == 1) {
            // Flip the image vertically
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -image.getHeight(null));
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image, null);
            return image;
        } else {
            // Flip the image horizontally
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image, null);
            return image;
        }
    }
}