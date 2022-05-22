package FrontEnd_layer;

import BackEnd_layer.Element_remove;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Element_remove_GUI implements ActionListener{
    
    JFrame window3 = new JFrame("Tranzakció törlése az adatbázisból");
    public static int elérési_sorszám = 0;
    
    JTextField inputitemTextField = new JTextField();
    JLabel inputitemLabel = new JLabel("Kérem adja meg a törlendő tranzakció azonosító számát:", JLabel.CENTER);
    JButton signUpButton = new JButton("Törlés");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    
    Element_remove_GUI() {
        
        GridLayout g1 = new GridLayout();
        g1.setColumns(1);
        g1.setRows(3);
        
        window3.setLayout(g1);
        
        signUpButton.addActionListener(this);

        window3.add(inputitemLabel);
        window3.add(inputitemTextField);

        //window.add(blank);
        window3.add(signUpButton);
        
        window3.setSize(500,300);

        
        window3.setVisible(true);
    }   
    
    Properties properties = new Properties();
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            try {
                
                elérési_sorszám = Integer.parseInt(inputitemTextField.getText());

                Element_remove.Element_remove_function();
 
                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (Exception ex) {
                Logger.getLogger(Element_remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}