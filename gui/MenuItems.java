package gui;

import java.io.*;

public class MenuItems {
    
    public MenuItems(){

        FileReader file;
        String line;

        {
            try {
                file = new FileReader("data.dat");
                BufferedReader bufferedReader = new BufferedReader(file);
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }




    }
}

