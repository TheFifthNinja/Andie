package cosc202.andie;

import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class ImageResize implements ImageOperation, java.io.Serializable {
    private int percentage;

    /** @param percentage percentage from input by user to resize image */
    public ImageResize(int percentage) {
        this.percentage = percentage;
    }

    /* Default constructor */
    public ImageResize() {

    }

    /**
     * Gets dimensions from user input from JSPinner
     * 
     * @param input image to resize.
     * @return it returns the resized image.
     */
    public BufferedImage apply(BufferedImage input) {
        /* gets dimensions of target image */
        int width = input.getWidth();
        int height = input.getHeight();
        float scaleF = (float) percentage / 100;

        /* calculates new width from percentage given by user */
        int newWidth = (int) (width * scaleF);
        int newHeight = (int) (height * scaleF);

        double scaleFact = scaleF;

        /* create a new scaled image */
        BufferedImage scaledImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scaleFact, scaleFact);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        return scaleOp.filter(input, scaledImg);
    }
}