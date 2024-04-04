package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to Line an area a color
 * </p>
 * 
 * <p>
 * This set pixels in a line to a set color
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */
public class Line extends ShapedOperation implements java.io.Serializable {
    /** The points of the oval */
    private int x, y, x2, y2;
    /** The Color of the oval */
    private int color;
    /** The Size of the line */
    private final int SIZE = 2;
    /**
     * <p>
     * Create a new Line() operation.
     * </p>
     */
    Line(int color) {

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
     * Sets an line to a color
     * </p>
     * 
     * 
     * @param input The image to be converted to Fill a line
     * @return The resulting change to the image.
     */
    public BufferedImage apply(BufferedImage input) {
        int p1x = x;
        int p1y = y;
        int p2x = x2;
        int p2y = y2;

        int xdiff = p2x - p1x;
        int ydiff = p2y - p1y;
        
        if(ydiff<=xdiff)
        {
            for (int xPoint = Math.min(p1x,p2x) + 1; xPoint < Math.max(p1x,p2x); ++xPoint) 
            {
                float yPoint = ((float) xPoint - p1x) / xdiff * ydiff + p1y;
                for(int xSize=-SIZE;xSize<SIZE;xSize++)
                {
                    for(int ySize=-SIZE;ySize<SIZE;ySize++)
                    {
                        input.setRGB(xPoint+xSize,((int)yPoint)+ySize, color);
                    }
                }
            }
        }
        else//if y is greate than x to avoid dots
        {
            for (int yPoint = p1y + 1; yPoint < p2y; ++yPoint) 
            {
                float xPoint = ((float) yPoint - p1y) / ydiff * xdiff + p1x;
                for(int xSize=-SIZE;xSize<SIZE;xSize++)
                {
                    for(int ySize=-SIZE;ySize<SIZE;ySize++)
                    {
                        input.setRGB(((int)xPoint)+xSize,yPoint+ySize, color);
                    }
                }
            }
        }


        return input;
        
    }
}
