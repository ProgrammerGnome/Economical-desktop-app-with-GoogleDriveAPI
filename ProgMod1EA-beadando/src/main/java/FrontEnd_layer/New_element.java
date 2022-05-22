package FrontEnd_layer;

import BackEnd_layer.New_element_to_node;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class New_element implements ActionListener{
    
    JFrame window0 = new JFrame("Új tranzakció felvétele az adatbázsba");
    public static int fajlszamlalo = 0;
    
    JTextField inputitemTextField = new JTextField("bevetel / kiadas    -   A megfelelot hagyja bent!");
    
    JTextField dateTextField = new JTextField();
    JTextField priceTextField = new JTextField("Kiadasnal -, bevetelnel + jel megadasa kotelezo!");
    JTextField descriptionTextField = new JTextField();
    
    JLabel inputitemLabel = new JLabel("Pénzforgalom típusa: A megfelelőt hagyja bent!", JLabel.CENTER);
    
    JLabel dateLabel = new JLabel("Dátum: ", JLabel.CENTER);
    JLabel priceLabel = new JLabel("Összeg devizajel nélkül (pl.: -100 vagy +200): ", JLabel.CENTER);
    JLabel descriptionLabel = new JLabel("Leírás: ", JLabel.CENTER);
    
    
    JButton signUpButton = new JButton("Mentés");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    
    New_element() {
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setRows(5);
        
        window0.setLayout(g1);
        
        signUpButton.addActionListener(this);

        
        window0.add(inputitemLabel);
        window0.add(inputitemTextField);
        

        window0.add(dateLabel);
        window0.add(dateTextField);
        
        window0.add(priceLabel);
        window0.add(priceTextField);
        
        window0.add(descriptionLabel);
        window0.add(descriptionTextField);
        
        
        window0.add(blank);
        window0.add(signUpButton);
        
        window0.setSize(600,400);

        
        window0.setVisible(true);
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            try {
                
                Scanner scanner = new Scanner(new File("system_files/c_variable.txt"));
                while(scanner.hasNextInt())
                {
                    fajlszamlalo = scanner.nextInt();
                }

                fajlszamlalo = fajlszamlalo + 1;

                Writer wr = new FileWriter("system_files/c_variable.txt");
                wr.write(Integer.toString(fajlszamlalo));
                wr.close();

                //System.out.println("\n"+fajlszamlalo);
                
               
                New_element_to_node.type = inputitemTextField.getText();
                
                New_element_to_node.date = dateTextField.getText();
                New_element_to_node.price = priceTextField.getText();
                
                
                try{
                    int number_test = parseInt(New_element_to_node.price);
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR 4\nAz összeg mezőnél érvénytelen"
                            + " formátumban adta meg az adatot.\nKérem használja a MEGLÉVŐ ELEM MÓDOSÍTÁSA opciót!");
                }
                
                New_element_to_node.description = descriptionTextField.getText();
                
                New_element_to_node.ERROR_nodetag_bovito();
                
                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (IOException ex) {
                Logger.getLogger(New_element.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}