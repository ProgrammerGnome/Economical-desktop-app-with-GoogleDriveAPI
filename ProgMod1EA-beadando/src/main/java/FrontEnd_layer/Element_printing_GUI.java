package FrontEnd_layer;

import BackEnd_layer.Element_printing;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

enum Enum_combobox {
            A("pénzforgalom típusa"),
            B("dátum"),
            C("összeg"),
            D("leírás");
            public final String label;
            private Enum_combobox(String label){
                this.label = label;
            }
}

public class Element_printing_GUI implements ActionListener, ItemListener{
    
    JFrame window2 = new JFrame("Tranzakció keresése az adatbázisban");
    public static String elérési_út = "ez nem az ám";
    
    JTextField inputitemTextField = new JTextField();
    JLabel inputitemLabel = new JLabel("Kérem adja meg a tartalmazandó szövegrészt: ", JLabel.CENTER);
    JLabel mitLabel = new JLabel("Kérem válaszon a legördülő menüből: ", JLabel.CENTER);
    JButton signUpButton = new JButton("Keresés");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    JComboBox < String > combo = new JComboBox < > ();
    
    
    Element_printing_GUI() {
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setRows(3);
        
        window2.setLayout(g1);
        
        signUpButton.addActionListener(this);
        
        window2.add(mitLabel);
        
        combo.addItem(Enum_combobox.A.label);
        combo.addItem(Enum_combobox.B.label);
        combo.addItem(Enum_combobox.C.label);
        combo.addItem(Enum_combobox.D.label);
        
        //combo.addItemListener(this);
        window2.add(combo);
            
        window2.add(inputitemLabel);
        window2.add(inputitemTextField);
        
        window2.add(blank);
        
        window2.add(signUpButton);
        
        window2.setSize(600,300);

        
        
        window2.setVisible(true);
    }   
    
    Properties properties = new Properties();
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            //String a = "";
            try {
                if (combo.getSelectedItem().equals(Enum_combobox.A.label)){
                    //
                    elérési_út = "//transaction[contains(type,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals(Enum_combobox.B.label)) {
                    elérési_út = "//transaction[contains(date,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals(Enum_combobox.C.label)) {
                    elérési_út = "//transaction[contains(price,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals(Enum_combobox.D.label)) {
                    elérési_út = "//transaction[contains(description,'"+inputitemTextField.getText()+"')]/azon/text()";
                }

                Element_printing.Element_printing_function();
 
                //JOptionPane.showMessageDialog(null, "File Written Succesfully!"); 
                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (Exception ex) {
                Logger.getLogger(Element_remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}