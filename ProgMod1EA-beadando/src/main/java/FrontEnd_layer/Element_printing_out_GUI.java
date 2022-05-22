package FrontEnd_layer;

import BackEnd_layer.All_element_toTXTprinting;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Element_printing_out_GUI {
    public static void main(String[] args) {
        Kiír2 k = new Kiír2();
        k.Ki2();
    }
}
class Kiír2 extends JFrame {
    public void Ki2() {
        String sor;
        DefaultListModel < String > lm = new DefaultListModel < > ();
        JList < String > jl = new JList < > (lm);
        JScrollPane scroll = new JScrollPane(jl);
        scroll.setPreferredSize(new Dimension(280, 360));
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(360, 100);
        
        All_element_toTXTprinting a = new All_element_toTXTprinting();
        
        try (Scanner file = new Scanner(new File("system_files/search.txt"))) {
            while (file.hasNext()) {
                sor = file.nextLine();
                lm.addElement("Tranzakció azonosító:    "+sor);
            }
            file.close();
        } catch (IOException error) {
            System.err.println("Hiba: " + error.getMessage());
        } finally {
            add(scroll);
            setTitle("Lekérdezés");
            pack();
            setVisible(true);
        }
    }
}