package cosc202.andie;

import java.awt.event.ActionEvent;
import javax.swing.*;
/**
 * <p>
 * AbstractAction to change the language
 * </p>
 * 
 * <p>
 * A language modifier tool that requires the user to restart program to see changes
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Kevin Albert
 * @version 1.0
 */
public class Language extends AbstractAction{

    /**
     * <p>
     * Create a new language operation
     * </p>
     * 
     * @param name The name of the option
     * @param icon The picture used to label the option
     * @param desc The description of the option
     * @param nmemonic The key input for the option
     */
    Language(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon);
        if (desc != null) {
           putValue(SHORT_DESCRIPTION, desc);
        }
        if (mnemonic != null) {
            putValue(MNEMONIC_KEY, mnemonic);
        }
    }

    /** 
    * <p>
    * Will open a menu for the user to select a language and then prompt the user to restart the program to see changes.
    * </p>
    *
    * {@link Language}.
    *
    * @param e Event triggering a callback
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        //A string array that displays all language options
        String[] lang = {Andie.bundle.getString("english"), Andie.bundle.getString("french"), Andie.bundle.getString("german"), Andie.bundle.getString("greek"), Andie.bundle.getString("indonesian"), Andie.bundle.getString("italian"), Andie.bundle.getString("japanese"), 
                         Andie.bundle.getString("mandarin"), Andie.bundle.getString("maori"), Andie.bundle.getString("russian"), Andie.bundle.getString("spanish"), Andie.bundle.getString("swedish")}; 
        
        //Warning befor user changes the language
        int cont = JOptionPane.showConfirmDialog(null, Andie.bundle.getString("warnQuestion"), Andie.bundle.getString("confirm"), 0);
        if(cont == 1){
            return;
        }   

        //A String variable that will prompt the user to select a language
        String option = (String) JOptionPane.showInputDialog(null, Andie.bundle.getString("selectLang"), Andie.bundle.getString("language"), JOptionPane.INFORMATION_MESSAGE, null, lang, lang[0]);
        
        //If statement to see if option has a value or not
        if(option == null){
            return;
        }else{
            //if statment to see if language select was english
            if(option.equalsIgnoreCase(Andie.bundle.getString("english"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "en");
                Andie.prefs.put("country", "NZ");
                Andie.startFrame();
                return; 
            }
            //if statment to see if language select was italian
            if(option.equalsIgnoreCase(Andie.bundle.getString("italian"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "it");
                Andie.prefs.put("country", "IT");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was german
            if(option.equalsIgnoreCase(Andie.bundle.getString("german"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "ge");
                Andie.prefs.put("country", "GE");
                Andie.startFrame();                
                return;
            }
            //if statment to see if language select was japanese
            if(option.equalsIgnoreCase(Andie.bundle.getString("japanese"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "jp");
                Andie.prefs.put("country", "JP");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was russian
            if(option.equalsIgnoreCase(Andie.bundle.getString("russian"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "ru");
                Andie.prefs.put("country", "RU");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was spanish
            if(option.equalsIgnoreCase(Andie.bundle.getString("spanish"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "sp");
                Andie.prefs.put("country", "SP");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was indonesian
            if(option.equalsIgnoreCase(Andie.bundle.getString("indonesian"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "in");
                Andie.prefs.put("country", "IN");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was maori
            if(option.equalsIgnoreCase(Andie.bundle.getString("maori"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "ma");
                Andie.prefs.put("country", "NZ");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was swedish
            if(option.equalsIgnoreCase(Andie.bundle.getString("swedish"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "sw");
                Andie.prefs.put("country", "SW");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was greek
            if(option.equalsIgnoreCase(Andie.bundle.getString("greek"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "gr");
                Andie.prefs.put("country", "GR");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was mandarin
            if(option.equalsIgnoreCase(Andie.bundle.getString("mandarin"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "md");
                Andie.prefs.put("country", "CH");
                Andie.startFrame();
                return;
            }
            //if statment to see if language select was french
            if(option.equalsIgnoreCase(Andie.bundle.getString("french"))){
                Andie.frame.dispose();
                Andie.prefs.put("language", "fr");
                Andie.prefs.put("country", "FR");
                Andie.startFrame();
                return; 
            }
        }
    }
}


