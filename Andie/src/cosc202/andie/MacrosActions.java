package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Macro menu.
 * </p>
 * 
 * <p>
 * The Macro menu contains actions that affect create Marcos to use later
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Daniel West
 * @version 1.0
 */
public class MacrosActions {
    
    /** A list of actions for the Marco menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Marco menu actions.
     * </p>
     */
    public MacrosActions() {
        actions = new ArrayList<Action>();
        actions.add(new StartMacroAction(Andie.bundle.getString("startMacro"), null, Andie.bundle.getString("startMacroDesc"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new LoadMacroAction(Andie.bundle.getString("loadMacro"), null, Andie.bundle.getString("loadMacro"), Integer.valueOf(KeyEvent.VK_F)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Marco actions.
     * </p>
     * 
     * @return The Marco menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("macros"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     *  Starts and End Marco
     * </p>
     * 
     */
    public class StartMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new StartMacroAction action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        StartMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the StartMacroAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the StartMacroAction is triggered.
         * Starts and End Marco
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (target.getImage().getCurrentImage() == null)
            {
                JOptionPane.showMessageDialog(null, Andie.bundle.getString("applyCatch"));
                return;
            }
            target.getImage().startMacro();
        }
    }
    
    /**
     * <p>
     * Action Loads Macro
     * </p>
     * 
     */
    public class LoadMacroAction extends ImageAction {

        /**
         * <p>
         * Create a LoadMacroAction action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LoadMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the LoadMacroAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LoadMacroAction is triggered.
         * Load a marco
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (target.getImage().getCurrentImage() == null)
            {
                JOptionPane.showMessageDialog(null, Andie.bundle.getString("applyCatch"));
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().loadMacro(imageFilepath);
                } catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, Andie.bundle.getString("correctFile"));
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
