package BackEnd_layer;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Full_db_to_screen {
    public static void main(String[] args) {
        Kiír k = new Kiír();
        k.Ki();
    }
}

class Kiír extends JFrame {
    public void Ki() {
        String sor;
        DefaultListModel < String > lm = new DefaultListModel < > ();
        JList < String > jl = new JList < > (lm);
        
        JScrollPane scroll = new JScrollPane(jl);
        scroll.setPreferredSize(new Dimension(750, 500));
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(360, 100);
        
        All_element_toTXTprinting.All_element_toTXTprinting_function();
        
        try (Scanner file = new Scanner(new File("system_files/db_to_scr.txt"))) {
            while (file.hasNext()) {
                sor = file.nextLine();
                lm.addElement(sor);
            }
            file.close();
        } catch (IOException error) {
            System.err.println("Hiba: " + error.getMessage());
        } finally {
            add(scroll);
            setTitle("Tranzakciómegjelenítő");
            pack();
            setVisible(true);
        }
    }
}