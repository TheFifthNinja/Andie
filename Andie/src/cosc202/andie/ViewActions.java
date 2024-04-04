package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the View menu.
 * </p>
 * 
 * <p>
 * The View menu contains actions that affect how the image is displayed in the
 * application.
 * These actions do not affect the contents of the image itself, just the way it
 * is displayed.
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
public class ViewActions {

    /**
     * A list of actions for the View menu.
     */
    protected ArrayList<Action> actions;
    /** the ANDIE frame */
    protected JFrame frame;

    /**
     * <p>
     * Create a set of View menu actions.
     * </p>
     * 
     * 
     */
    public ViewActions() {
        actions = new ArrayList<Action>();
        Action zoomInAction = new ZoomInAction(Andie.bundle.getString("zoomIn"), null, Andie.bundle.getString("zoomIn"),
                Integer.valueOf(KeyEvent.VK_PLUS));
        zoomInAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK));
        actions.add(zoomInAction);
        Action zoomOutAction = new ZoomOutAction(Andie.bundle.getString("zoomOut"), null,
                Andie.bundle.getString("zoomOut"),
                Integer.valueOf(KeyEvent.VK_MINUS));
        zoomOutAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK));
        actions.add(zoomOutAction);

        actions.add(new ZoomFullAction(Andie.bundle.getString("zoomFull"), null, Andie.bundle.getString("zoomFull"),
                Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new ImageResizeAction(Andie.bundle.getString("resize"), null, Andie.bundle.getString("resizeDesc"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new ImageFlipAction(Andie.bundle.getString("flipVert"), null,
                Andie.bundle.getString("flipVertDesc"), Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new ImageFlipAction1(Andie.bundle.getString("flipHori"), null,
                Andie.bundle.getString("flipHoriDesc"), Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new ImageRotateAction(Andie.bundle.getString("rotateImage1"), null,
                Andie.bundle.getString("rotateImage1"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new ImageRotateAction1(Andie.bundle.getString("rotateImage2"), null,
                Andie.bundle.getString("rotateImage2"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new ImageCropAction(Andie.bundle.getString("cropimage"), null,
                Andie.bundle.getString("cropimage"), Integer.valueOf(KeyEvent.VK_O)));

        /**
         * Creating the icon for the zoom in function
         * Uses a free image that depicts the zoom in function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon zoomInIcon = new ImageIcon("src/Icons/zoomIn.png");
        zoomInIcon = new ImageIcon(zoomInIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton zoomInButton = new JButton(zoomInAction);
        zoomInButton.setIcon(zoomInIcon);
        zoomInButton.setText("");

        /**
         * Creating the icon for the zoom out function
         * Uses a free image that depicts the zoom out function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon zoomOutIcon = new ImageIcon("src/Icons/zoomOut.png");
        zoomOutIcon = new ImageIcon(zoomOutIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton zoomOutButton = new JButton(zoomOutAction);
        zoomOutButton.setIcon(zoomOutIcon);
        zoomOutButton.setText("");

        Andie.toolBar.add(zoomInButton);
        Andie.toolBar.add(zoomOutButton);

        this.frame = Andie.frame;
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu(Andie.bundle.getString("view"));

        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action));
        }

        return viewMenu;
    }

    /**
     * <p>
     * Action to zoom in on an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomInAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-in action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomInAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-in action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomInAction is triggered.
         * It increases the zoom level by 10%, to a maximum of 200%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() + 10);
            frame.setSize(new Dimension(
                    (int) (target.getImage().getCurrentImage().getWidth() * target.getZoom() / 100) + 20,
                    (int) (target.getImage().getCurrentImage().getHeight() * target.getZoom() / 100) + 110));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to zoom out of an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomOutAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-out action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomOutAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-iout action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomOutAction is triggered.
         * It decreases the zoom level by 10%, to a minimum of 50%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() - 10);
            frame.setSize(new Dimension(
                    (int) (target.getImage().getCurrentImage().getWidth() * target.getZoom() / 100) + 20,
                    (int) (target.getImage().getCurrentImage().getHeight() * target.getZoom() / 100) + 110));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to reset the zoom level to actual size.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its
     * actual contents.
     * </p>
     */
    public class ZoomFullAction extends ImageAction {

        /**
         * <p>
         * Create a new zoom-full action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ZoomFullAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-full action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomFullAction is triggered.
         * It resets the Zoom level to 100%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.setZoom(100);
            target.revalidate();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * This method is called whenever the ImageResizeAction is triggered.
     * It resizes the image by user choice of percentage using a JSPinner.
     * </p>
     */
    public class ImageResizeAction extends ImageAction {
        ImageResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int percentage = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel percentageModel = new SpinnerNumberModel(10, 10, 200, 50);
            JSpinner percentageSpinner = new JSpinner(percentageModel);
            int option = JOptionPane.showOptionDialog(null, percentageSpinner, Andie.bundle.getString("resizePercent"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                percentage = percentageModel.getNumber().intValue();
            }

            target.getImage().apply(new ImageResize(percentage));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * This method is called whenever the ImageFlipAction is triggered.
     * It flips the image vertically.
     * </p>
     */
    public class ImageFlipAction extends ImageAction {
        ImageFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageFlip(1));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * This method is called whenever the ImageFlipAction1 is triggered.
     * It flips the image horizontally.
     * </p>
     */
    public class ImageFlipAction1 extends ImageAction {
        ImageFlipAction1(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageFlip(2));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * This method is called whenever the ImageRotateAction is triggered.
     * It rotates the image right. Rotates the image 90 degrees to the right.
     * </p>
     */
    public class ImageRotateAction extends ImageAction {
        ImageRotateAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotate());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * This method is called whenever the ImageRotateAction1 is triggered.
     * It rotates the image left. Rotates the image 90 degrees to the left.
     * </p>
     */
    public class ImageRotateAction1 extends ImageAction {
        ImageRotateAction1(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotate(1));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * This method is called whenever the ImageCropAction is triggered. It activates
     * the rectangle draw to draw your desired crop size over the image to be
     * cropped.
     * </p>
     */

    public class ImageCropAction extends ImageAction {
        ImageCropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.setCreateShape(new ImageCrop());
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
