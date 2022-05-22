/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author kiralymark
 */
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