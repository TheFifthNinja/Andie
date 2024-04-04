package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

 /**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EditActions {
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;

    /** the ANDIE frame */
    protected JFrame frame;

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     * 
     * 
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        
        Action undoAction = new UndoAction(Andie.bundle.getString("undo"), null, Andie.bundle.getString("undo"), Integer.valueOf(KeyEvent.VK_Z));
        undoAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        actions.add(undoAction);

        Action redoAction = new RedoAction(Andie.bundle.getString("redo"), null, Andie.bundle.getString("redo"), Integer.valueOf(KeyEvent.VK_Y));
        redoAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        actions.add(redoAction);
        
        /**
         * Creating the icon for the undo function
         * Uses a free image that depicts the undo function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon undoIcon = new ImageIcon("src/Icons/undo.png");
        undoIcon = new ImageIcon(undoIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton undoButton = new JButton(undoAction);
        undoButton.setIcon(undoIcon);
        undoButton.setText("");
        
        /**
         * Creating the icon for the redo function
         * Uses a free image that depicts the redo function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon redoIcon = new ImageIcon("src/Icons/redo.png");
        redoIcon = new ImageIcon(redoIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton redoButton = new JButton(redoAction);
        redoButton.setIcon(redoIcon);
        redoButton.setText("");

        Andie.toolBar.add(undoButton);
        Andie.toolBar.add(redoButton);
        
        this.frame = Andie.frame;
    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(Andie.bundle.getString("edit"));

        for (Action action: actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            frame.setSize(new Dimension(
                (int)(target.getImage().getCurrentImage().getWidth()*target.getZoom()/100)+20,
                (int)(target.getImage().getCurrentImage().getHeight()*target.getZoom()/100)+110));
            target.repaint();
            target.getParent().revalidate();
        }
    }

     /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */   
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        
        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            frame.setSize(new Dimension(
                (int)(target.getImage().getCurrentImage().getWidth()*target.getZoom()/100)+20,
                (int)(target.getImage().getCurrentImage().getHeight()*target.getZoom()/100)+110));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
