package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel {

    /** the current point of the mouse */
    private int x, y, x2, y2;
    /** The min size before the mouse showes */
    private final int minSizeMouse = 5;
    public static ArrayList<Integer> colors = new ArrayList<>();
    private Boolean isSavingColor = false;
    /** checking if the create shape is on */
    private Boolean createShape = false;
    /** The current Operation which will run if mouse is uncliked */
    private ShapedOperation currentShape;
    /** The panel where it show the current color */
    private JPanel colorPanel;

    public MyMouseListener listener;

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        x = y = x2 = y2 = 0;
        image = new EditableImage();
        scale = 1.0;

        // Listens to mouse click and drag
        listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }
        g.setColor(Color.BLUE);
        if (createShape) {
            drawPerfectRect(g, x, y, x2, y2);
        }
    }

    /** Draws rectangle with mouse click and drag over image */
    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x, x2);
        int py = Math.min(y, y2);
        int pw = Math.abs(x - x2);
        int ph = Math.abs(y - y2);
        g.drawRect(px, py, pw, ph);
    }

    public void setStartPoint(int x, int y) {
        this.x = x;//(int)(scale*Double.valueOf(x));
        this.y = y;//(int)(scale*Double.valueOf(y));
    }

    public void setEndPoint(int x, int y) {
        x2 = x;//(int)(scale*Double.valueOf(x));
        y2 = y;//(int)(scale*Double.valueOf(y));
    }

    class MyMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
            if (isSavingColor) {
                int argb = image.getCurrentImage().getRGB(e.getX(), e.getY());
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);
                colorPanel.setBackground(new Color(r, g, b));

                colors.add(argb);
                isSavingColor = false;
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (createShape) {
                if (e.getX() + e.getY() - x - y >= minSizeMouse || -e.getX() - e.getY() + x + y >= minSizeMouse) {
                    setEndPoint(e.getX(), e.getY());
                    repaint();
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (createShape) {
                if (e.getX() + e.getY() - x - y >= minSizeMouse || -e.getX() - e.getY() + x + y >= minSizeMouse) {
                    setEndPoint(e.getX(), e.getY());
                    
                    //set current points to the currentShape then apply it to the image
                    runCurrentOps();
                    
                    clearOps();
                    repaint();
                    getParent().revalidate();
                }
            }
        }

        /**
         * Run when mouse Moved
         * 
         * If isSavingColor is on is will change the colorpanel to the color which the
         * mouse is on
         * 
         * @param e the mouseEvent info
         */
        public void mouseMoved(MouseEvent e) {
            if (isSavingColor) {
                int argb = image.getCurrentImage().getRGB(e.getX(), e.getY());
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);
                colorPanel.setBackground(new Color(r, g, b));
            }
        }
    }
    private void runCurrentOps()
    {
        currentShape.setPoints(
            (int)(Double.valueOf(x)/scale),
            (int)(Double.valueOf(y)/scale),
            (int)(Double.valueOf(x2)/scale),
            (int)(Double.valueOf(y2)/scale));
        getImage().apply(currentShape);
    }

    /**
     * When we want to save a color from the image
     * 
     * @param colorPanel to know where we can show the color
     */
    public void saveColor(JPanel colorPanel) {
        isSavingColor = true;
        this.colorPanel = colorPanel;
    }

    /**
     * When we want grab an area for a shape
     * Then we can apply the ops into the image
     * 
     * @param ops
     */
    public void setCreateShape(ShapedOperation ops) {
        currentShape = ops;
        createShape = true;
        x = 0;
        y = 0;
        x2 = 0;
        y2 = 0;
    }

    /**
     * Get currnet color in int
     * 
     * @return the color in int form
     */
    public int getCurrentColor() {
        if (colors.isEmpty()) {
            return 0;
        }
        return colors.get(colors.size() - 1);
    }

    /**
     * Get currnet color in int
     * @param c the int color to be set
     */
    public static void setCurrentColor(int c) {
        if (c != 0) {
            colors.clear();
            colors.add(c);
        }

    }

    /** Clear the get area in imagePlanel */
    public void clearOps() {
        currentShape = null;
        createShape = false;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getx2() {
        return x2;
    }

    public int gety2() {
        return y2;
    }

}
