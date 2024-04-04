package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

public class ImageRotate implements ImageOperation, java.io.Serializable {
    private int count = 2;

    /* Default constructor to rotate the image left when data field is not set */
    public ImageRotate() {
    }

    /*
     * Default constructor to rotate the image right
     * 
     * @param cc int 2 to set data field to rotate right
     */
    public ImageRotate(int cc) {
        this.count = cc;
    }

    /**
     * <p>
     * Rotates an image.
     * </p>
     * 
     * <p>
     * The Image Rotation is done with Affine Transform
     * </p>
     * 
     * @param input The image to rotate.
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage image) {
        /* Rotates Image right */
        if (count == 2) {
            double rotationAngle = 90.0;
            int cx = image.getWidth() / 2;
            int cy = image.getHeight() / 2;

            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(rotationAngle), cx, cy);

            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            Rectangle2D bounds = op.getBounds2D(image);
            int w = (int) bounds.getWidth();
            int h = (int) bounds.getHeight();
            int x = (w - image.getWidth()) / 2;
            int y = (h - image.getHeight()) / 2;
            BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());

            Graphics2D g2d = rotatedImage.createGraphics();
            g2d.drawImage(image, op, x, y);
            g2d.dispose();

            return rotatedImage;

        }
        /* Rotates image left */
        else {
            double rotationAngle = 360.0 - 90.0;
            int cx = image.getWidth() / 2;
            int cy = image.getHeight() / 2;

            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(rotationAngle), cx, cy);

            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            Rectangle2D bounds = op.getBounds2D(image);
            int w = (int) bounds.getWidth();
            int h = (int) bounds.getHeight();
            int x = (w - image.getWidth()) / 2;
            int y = (h - image.getHeight()) / 2;
            BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());

            Graphics2D g2d = rotatedImage.createGraphics();
            g2d.drawImage(image, op, x, y);
            g2d.dispose();

            return rotatedImage;

        }

    }
}