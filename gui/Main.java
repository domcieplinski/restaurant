package gui;

import gui.pdf.PdfGenerator;

import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.showMessageDialog;


public class Main {

    public static void main(String[] args) {
        Preferences pref = Preferences.userNodeForPackage(Settings.class);
        if(pref.get("title", "root") == "root" ||
                pref.get("tables_amount", "root") == "root" ||
                pref.get("tax", "root") == "root" ||
                pref.get("street", "root") == "root" ||
                pref.get("state", "root") == "root" ||
                pref.get("zipcode", "root") == "root"
        ){
            showMessageDialog(null, "You have to set all settings!");
            Settings settings = new Settings();
            Settings.frame.setVisible(true);
        }
        else
            new MainView();
    }


}
