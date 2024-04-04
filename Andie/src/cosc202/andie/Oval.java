package cosc202.andie;

import java.awt.geom.Ellipse2D;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to Fill an area a color
 * </p>
 * 
 * <p>
 * This set pixels in a oval to a set color
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */
public class Oval extends ShapedOperation implements java.io.Serializable {
    /** The points of the oval */
    private int x, y, x2, y2;
    /** The Color of the oval */
    private int color;
    /**
     * <p>
     * Create a new Oval() operation.
     * </p>
     */
    Oval(int color) {
        this.color = color;
    }
    /**
     * <p>
     * Sets x,y,x2,y2 
     * </p>
     * @param x,y,x2,y2 the points for the class
     * 
     */
    public void setPoints(int x,int y,int x2,int y2)
    {
        this.x=x;
        this.y=y;
        this.x2=x2;
        this.y2=y2;
    }

    /**
     * <p>
     * Sets an Oval arae to a color
     * </p>
     * 
     * 
     * @param input The image to be converted to Fill a oval
     * @return The resulting Oval image.
     */
    public BufferedImage apply(BufferedImage input) {

        Ellipse2D e = new Ellipse2D.Float(Math.min(x, x2),Math.min(y, y2),Math.abs(x-x2),Math.abs(y-y2));
        
        for (int xPoint = Math.min(x, x2); xPoint < Math.max(x, x2); xPoint++) {
            for (int yPoint = Math.min(y, y2); yPoint < Math.max(y, y2); yPoint++) {
                if(e.contains(xPoint, yPoint))
                {
                    input.setRGB(xPoint, yPoint, color);
                }
            }
        }

        return input;
    }
}
