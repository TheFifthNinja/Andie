package cosc202.andie;

import java.awt.*;
import java.util.*;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.imageio.*;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills and Team O
 * @version 1.0
 */
public class Andie {

    public static ResourceBundle bundle;
    public static Preferences prefs;
    public static JFrame frame;
    public static Locale Locale;
    public static JToolBar toolBar;
    public static ImagePanel imagePanel;

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see String #startFrame() startFrame
     */
    public static void main(String[] args) throws Exception {

        prefs = Preferences.userNodeForPackage(Andie.class);

        // Calls start frame method
        startFrame();
    }

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     */
    public static void startFrame() {
        java.util.Locale.setDefault(new Locale(prefs.get("language", "en"),
                prefs.get("country", "NZ")));

        bundle = ResourceBundle.getBundle("Languages/MessageBundle");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set up the main GUI frame
                    frame = new JFrame(bundle.getString("title"));

                    Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
                    frame.setIconImage(image);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // The main content area is an ImagePanel
                    imagePanel = new ImagePanel();
                    ImageAction.setTarget(imagePanel);
                    JScrollPane scrollPane = new JScrollPane(imagePanel);
                    frame.add(scrollPane, BorderLayout.CENTER);

                    // Add in menus for various types of action the user may perform.
                    JMenuBar menuBar = new JMenuBar();

                    // Setting up toolbar and formatting it under a boxlayout
                    toolBar = new JToolBar();
                    toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

                    // File menus are pretty standard, so things that usually go in File menus go
                    // here.
                    FileActions fileActions = new FileActions();
                    menuBar.add(fileActions.createMenu());

                    // Likewise Edit menus are very common, so should be clear what might go here.
                    EditActions editActions = new EditActions();
                    menuBar.add(editActions.createMenu());

                    // View actions control how the image is displayed, but do not alter its actual
                    // content
                    ViewActions viewActions = new ViewActions();
                    menuBar.add(viewActions.createMenu());

                    // Filters apply a per-pixel operation to the image, generally based on a local
                    // window
                    FilterActions filterActions = new FilterActions();
                    menuBar.add(filterActions.createMenu());

                    // Actions that affect the representation of colour in the image
                    ColourActions colourActions = new ColourActions();
                    menuBar.add(colourActions.createMenu());

                    // Macros Actions
                    MacrosActions macrosActions = new MacrosActions();
                    menuBar.add(macrosActions.createMenu());

                    DrawActions drawActions = new DrawActions();
                    menuBar.add(drawActions.createMenu());

                    frame.setJMenuBar(menuBar);
                    frame.add(toolBar, BorderLayout.NORTH);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }

}
