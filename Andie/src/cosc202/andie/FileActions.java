package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
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
public class FileActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;
    /** the ANDIE frame */
    protected JFrame frame;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     * 
     * 
     */
    public FileActions() {
        actions = new ArrayList<Action>();

        Action openAction = new FileOpenAction(Andie.bundle.getString("open"), null, Andie.bundle.getString("openDesc"),
            Integer.valueOf(KeyEvent.VK_O));
            openAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
            actions.add(openAction);

        Action saveAction = new FileSaveAction(Andie.bundle.getString("save"), null, Andie.bundle.getString("saveDesc"),
            Integer.valueOf(KeyEvent.VK_S));
            saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            actions.add(saveAction);

        actions.add(new FileSaveAsAction(Andie.bundle.getString("saveAs"), null, Andie.bundle.getString("saveAsDesc"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new LanguageAction(Andie.bundle.getString("language"), null, Andie.bundle.getString("langDesc"), Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new FileExportAction(Andie.bundle.getString("export"), null, Andie.bundle.getString("exportDesc"), Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExitAction(Andie.bundle.getString("exit"), null, Andie.bundle.getString("exitDesc"), Integer.valueOf(0)));
        
        /**
         * Creating the icon for the save function
         * Uses a free image that depicts the save function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon saveIcon = new ImageIcon("src/Icons/save.png");
        saveIcon = new ImageIcon(saveIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton saveButton = new JButton(saveAction);
        saveButton.setIcon(saveIcon);
        saveButton.setText("");

        /**
         * Creating the icon for the open function
         * Uses a free image that depicts the open function
         * <a href="https://pixabay.com/">CC BY-NC-SA
         */
        ImageIcon openIcon = new ImageIcon("src/Icons/open.png");
        openIcon = new ImageIcon(openIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton openButton = new JButton(openAction);
        openButton.setIcon(openIcon);
        openButton.setText("");

        Andie.toolBar.add(openButton);
        Andie.toolBar.add(saveButton);

        this.frame = Andie.frame;
        
    }

    /**
     * <p>
     * Create a menu contianing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("file"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                    frame.setSize(new Dimension(
                        (int)(target.getImage().getCurrentImage().getWidth()*target.getZoom()/100)+20,
                        (int)(target.getImage().getCurrentImage().getHeight()*target.getZoom()/100)+110));
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, Andie.bundle.getString("correctFile"));
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, Andie.bundle.getString("applyCatch"));
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to export a file to a file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new File-export action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);

        }

        /**
         * <p>
         * Callback for when the File-export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            // fileChooser.setAcceptAllFileFilterUsed(false);
            // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Image",
            // "jpeg", "jpg"));
            // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Image",
            // "png"));
            // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG Image",
            // "jpg"));
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    // FileFilter filter = fileChooser.getFileFilter();
                    // String extension;
                    // if (filter instanceof FileNameExtensionFilter) {
                    // FileNameExtensionFilter extensionFilter = (FileNameExtensionFilter) filter;
                    // extension = extensionFilter.getExtensions()[0];
                    // } else {
                    // extension = "png";
                    // }
                    target.getImage().export(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    /**
     * <p>
     * Action to change the language for the ANDIE application.
     * </p>
     */
    public class LanguageAction extends Language {

        /**
         * <p>
         * Create a new Language action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LanguageAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

    }

}
