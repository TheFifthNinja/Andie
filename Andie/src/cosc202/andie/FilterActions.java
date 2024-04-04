package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
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
public class FilterActions {

    /** A list of actions, sub_actions and sub_actions2 for the Filter menu. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> emboss_actions;
    protected ArrayList<Action> sobel_actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(Andie.bundle.getString("blur"), null, Andie.bundle.getString("blurDesc"),
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MedianFilterAction(Andie.bundle.getString("median"), null, Andie.bundle.getString("medianDesc"),
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new SoftBlurAction(Andie.bundle.getString("softBlur"), null, Andie.bundle.getString("softBlurDesc"),
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SharpenFilterAction(Andie.bundle.getString("sharpen"), null,
                Andie.bundle.getString("sharpenDesc"), Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new GaussianFilterAction(Andie.bundle.getString("gaussian"), null,
                Andie.bundle.getString("gaussianDesc"), Integer.valueOf(KeyEvent.VK_B)));

        emboss_actions = new ArrayList<Action>();
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("topEmboss"), null,
                Andie.bundle.getString("topEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("topRightEmboss"), null,
                Andie.bundle.getString("topRightEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("rightEmboss"), null,
                Andie.bundle.getString("rightEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("bottomRightEmboss"), null,
                Andie.bundle.getString("bottomRightEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("bottomEmboss"), null,
                Andie.bundle.getString("bottomEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("bottomLeftEmboss"), null,
                Andie.bundle.getString("bottomLeftEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("leftEmboss"), null,
                Andie.bundle.getString("leftEmboss"), 0));
        emboss_actions.add(new EmbossFilterAction(Andie.bundle.getString("topLeftEmboss"), null,
                Andie.bundle.getString("topLeftEmboss"), 0));

        sobel_actions = new ArrayList<Action>();
        sobel_actions.add(new SobelFilterAction(Andie.bundle.getString("rightSobel"), null,
                Andie.bundle.getString("rightSobel"), 0));
        sobel_actions.add(new SobelFilterAction(Andie.bundle.getString("leftSobel"), null,
                Andie.bundle.getString("leftSobel"), 0));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions and two sub menus
     * contianing there own Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("filter"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        // Sub Menu for Emboss
        JMenu subMenu = new JMenu(Andie.bundle.getString("emboss"));

        // Add each emboss filter to sub menu
        for (Action action : emboss_actions) {
            subMenu.add(new JMenuItem(action));
        }

        // Add subMenu to fileMenu
        fileMenu.add(subMenu);

        JMenu subMenu2 = new JMenu(Andie.bundle.getString("sobel"));

        // Add each Sobel filter to sub menu2
        for (Action action : sobel_actions) {
            subMenu2.add(new JMenuItem(action));
        }

        // Add subMenu2 to fileMenu
        fileMenu.add(subMenu2);

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a Sobel Filter.
     * </p>
     * 
     * @see SobelFilter
     */
    public class SobelFilterAction extends ImageAction {
        String direct;

        /**
         * <p>
         * Create a new Sobel-Filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelFilterAction(String direct, ImageIcon icon, String desc, Integer mnemonic) {
            super(direct, icon, desc, mnemonic);
            this.direct = direct;
        }

        public void actionPerformed(ActionEvent e) {

            // Create and apply the filter
            target.getImage().apply(new SobelFilter(direct));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a Emboss Filter.
     * </p>
     * 
     * @see EmbossFilter
     */
    public class EmbossFilterAction extends ImageAction {
        String directions;

        /**
         * <p>
         * Create a new Emboss Filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossFilterAction(String directions, ImageIcon icon, String desc, Integer mnemonic) {
            super(directions, icon, desc, mnemonic);
            this.directions = directions;
        }

        public void actionPerformed(ActionEvent e) {

            // Create and apply the filter
            target.getImage().apply(new EmbossFilter(directions));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("filterRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("filterRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class SoftBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new soft-blur filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the soft blur image action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class GaussianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new gausian filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the gaussian filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * {@link GaussianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("filterRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }
}
