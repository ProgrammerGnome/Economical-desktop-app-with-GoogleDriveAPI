package FrontEnd_layer;

import BackEnd_layer.Element_modify;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import static java.lang.Integer.parseInt;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Element_modify_GUI implements ActionListener{
    
    public static String elérési_út_mod = "";
    public static String mire_mod = "";
    
    public static String elérési_út_mod2 = "";
    public static String mire_mod2 = "";
    
    public static String elérési_út_mod3 = "";
    public static String mire_mod3 = "";
    
    public static String elérési_út_mod4 = "";
    public static String mire_mod4 = "";
    
    public static String modositando = "";
    public static String modositott = "";
    
    
    JFrame window1 = new JFrame("Tranzakció módosítása az adatbázisban");
    JTextField inputitemTextField = new JTextField();
    JTextField inputitem2TextField = new JTextField("bevetel / kiadas    -   A megfelelot hagyja bent!");

    
    JTextField date2TextField = new JTextField();
    
    JTextField price2TextField = new JTextField("Kiadasnal -, bevetelnel + jel megadasa kotelezo!");
            
    JTextField description2TextField = new JTextField();
    
    
    JLabel inputitemLabel = new JLabel("Kérem adja meg a módosítandó elem tranzakció azonosítóját:", JLabel.CENTER);
    JLabel inputitem2Label = new JLabel("Amire módosítani szeretnéd:                     "
            + "-> Pénzforgalom típusa : ", JLabel.RIGHT);
    
    JLabel date2Label = new JLabel("-> Dátum : ", JLabel.RIGHT);
    JLabel price2Label = new JLabel("-> Összeg devizajel nélkül (pl.: -100 vagy +200): ", JLabel.RIGHT);
    JLabel description2Label = new JLabel("-> Leírás: ", JLabel.RIGHT);
    
    JButton signUpButton = new JButton("Módosítás");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    
    Element_modify_GUI() {
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setRows(6);
        
        window1.setLayout(g1);
        
        signUpButton.addActionListener(this);

        window1.add(inputitemLabel);
        window1.add(inputitemTextField);
        
        
        window1.add(inputitem2Label);
        window1.add(inputitem2TextField);
        
        
        window1.add(date2Label); window1.add(date2TextField);
        window1.add(price2Label); window1.add(price2TextField);
        window1.add(description2Label); window1.add(description2TextField);
        
        
        window1.add(blank);
        window1.add(signUpButton);
        
        window1.setSize(800,400);

        
        window1.setVisible(true);
    }   
    
    Properties properties = new Properties();
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            try {
            
                elérési_út_mod = "//transaction[azon='"+inputitemTextField.getText()+"']/type/text()";
                mire_mod = inputitem2TextField.getText();
                
                //
                elérési_út_mod2 = "//transaction[azon='"+inputitemTextField.getText()+"']/date/text()";
                mire_mod2 = date2TextField.getText();
                //
                elérési_út_mod3 = "//transaction[azon='"+inputitemTextField.getText()+"']/price/text()";
                mire_mod3 = price2TextField.getText();
                
                try{
                    int number_test_ = parseInt(mire_mod3);
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR 5\nAz összeg mezőnél érvénytelen"
                            + " formátumban adta meg az adatot.\nKérem módosítsa ismét a következő bejegyzést:\n"
                            +"Tranzakció azonosító: "+inputitemTextField.getText());
                }
                
                //
                elérési_út_mod4 = "//transaction[azon='"+inputitemTextField.getText()+"']/description/text()";
                mire_mod4 = description2TextField.getText();
                
                //New_element_to_node.abc2 = inputitemTextField.getText();
                Element_modify.Element_modify_funciton();
                
                Element_modify.Element_modify_funciton2();
                Element_modify.Element_modify_funciton3();
                Element_modify.Element_modify_funciton4();

                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (Exception ex) {
                Logger.getLogger(Element_remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}