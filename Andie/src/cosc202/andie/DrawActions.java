package cosc202.andie;

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Draw menu.
 * </p>
 * 
 * <p>
 * The Draw menu contains actions that change the pixel in a area
 * without reference to the rest of the image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */

public class DrawActions {
    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;
    /** The Panel that show which colour is selected */
    private JPanel colorPanel;

    /**
     * <p>
     * Create a set of Draw menu actions.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new FillAction(Andie.bundle.getString("fill"), null, Andie.bundle.getString("fillDesc"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new OvalAction(Andie.bundle.getString("oval"), null, Andie.bundle.getString("ovalDesc"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new LineAction(Andie.bundle.getString("line"), null, Andie.bundle.getString("lineDesc"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new SaveClickedColor(Andie.bundle.getString("clickedColour"), null, Andie.bundle.getString("clickedDesc"), 
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new colourPalaute(Andie.bundle.getString("colourPalatte"), null, Andie.bundle.getString("colourPalatteDesc"), 
                Integer.valueOf(KeyEvent.VK_C)));

        colorPanel = new JPanel();
        colorPanel.setMaximumSize(new Dimension(35, 35));
        colorPanel.setBackground(new Color(0, 0, 0));
        Andie.toolBar.add(colorPanel);

    }

    /**
     * <p>
     * Create a menu contianing the list of Draw actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(Andie.bundle.getString("draw"));

        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));
        }

        return drawMenu;
    }


    public class colourPalaute extends ImageAction {
        /**
         * <p>
         * Prompts a color menu to be set.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        colourPalaute(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(null, Andie.bundle.getString("colourPalatte"), Color.BLACK);
            colorPanel.setBackground(color);
            ImagePanel.setCurrentColor(color.getRGB());

        }

    }
    
    /**
     * <p>
     * The FillAction
     * </p>
     * 
     * @see Fill
     */
    public class FillAction extends ImageAction {

        /**
         * <p>
         * Create a new Fill action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FillAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Fill action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FillAction is triggered.
         * Action to change a rec to the current color
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.setCreateShape(new Fill(
                    Andie.imagePanel.getCurrentColor()));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change a oval to the current color
     * </p>
     * 
     * @see Oval
     */

    public class OvalAction extends ImageAction {
        /**
         * <p>
         * Create a new Oval action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        OvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Oval action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the OvalAction is triggered.
         * Action to change a oval to the current color
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.setCreateShape(new Oval(
                    Andie.imagePanel.getCurrentColor()));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change a Line to the current color
     * </p>
     * 
     * @see Line
     */
    public class LineAction extends ImageAction {
        /**
         * <p>
         * Create a new Line action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Line action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LineAction is triggered.
         * Action to change a line to the current color
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.setCreateShape(new Line(
                    Andie.imagePanel.getCurrentColor()));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * /**
     * <p>
     * Action to save the last click color.
     * </p>
     * 
     */
    public class SaveClickedColor extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SaveClickedColor(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Andie.imagePanel.getImage().hasImage()) {
                Andie.imagePanel.saveColor(colorPanel);
            }
        }
    }
}
