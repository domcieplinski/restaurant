package gui;
import java.util.prefs.Preferences;

public class Guest{
    static Preferences pref = Preferences.userNodeForPackage(Settings.class);
    static int tables_amount = Integer.parseInt(pref.get("tables_amount", "root"));
    static Guest[] guest =  new Guest[tables_amount];

    boolean active = false;
    public Guest(int number){

        
    }

    static boolean setActive(int number){
        System.out.println("Jestem w srodku w gosciu nr : " + number);
        return guest[number].active;
    }
}
