package FrontEnd_layer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//GENERIKUS osztály
class GenericClass<T> {
    private T data;
    public GenericClass(T data) {
        this.data = data;
    }
    public T getData() {
        return this.data;
    }
}

public class Main_screen{
    
    public static int egyensúly = 0;
    public static int main_scr_fajlszamlalo = 0;
    
    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{

        System.out.println(Main_screen_function(egyensúly));
    }
    
    public static double Main_screen_function(double egyensúly) throws XPathExpressionException, ParserConfigurationException, FileNotFoundException, SAXException, IOException{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
         
        Document doc = db.parse(new FileInputStream(new File("program_database.xml")));
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        Scanner scanner = new Scanner(new File("system_files/c_variable.txt"));
                while(scanner.hasNextInt())
                {
                    main_scr_fajlszamlalo = scanner.nextInt();
                }
        
        XPathExpression expr = xpath.compile("//transaction[azon<='"+main_scr_fajlszamlalo+"']/price/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;

        ArrayList<String> list=new ArrayList<>(); 
        
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i).getNodeValue());
        }
        
        //*************** GENERIKUS osztály inicializálása *******************
        double sum = 0;
        PrintWriter writer = new PrintWriter("blokk.xlsx", "UTF-8");
        
        GenericClass<String> Obj_fejlec_datum = new GenericClass<>(LocalDate.now().toString());
        GenericClass<String> Obj_fejlec_ido = new GenericClass<>(LocalTime.now().toString());
        
        GenericClass<String> Obj_fejlec = new GenericClass<>("OTTHONI KÖLTSÉGVETÉSI ALKALMAZÁS \n BLOKK \nLekérve:");
        writer.println(Obj_fejlec.getData() +" "+ Obj_fejlec_datum.getData() +" "+ Obj_fejlec_ido.getData());
        
        writer.println("sorszám" +" "+ "forgalomirány" +" "+ "összeg");
        
        for (int j = 0; j < nodes.getLength(); j++){
            try{
                GenericClass<Integer> Obj_sorszam = new GenericClass<>(j);
                GenericClass<Double> Obj = new GenericClass<>(Double.parseDouble(nodes.item(j).getNodeValue()));
                if (Obj.getData() >= 0){
                    GenericClass<String> Obj_type = new GenericClass<>("bevétel");
                    writer.println(Obj_sorszam.getData() + " " + Obj_type.getData() + " " + Obj.getData());
                } else{
                    GenericClass<String> Obj_type = new GenericClass<>("kiadás");
                    writer.println(Obj_sorszam.getData() + " " + Obj_type.getData() + " " + Obj.getData());
                }
                sum += Obj.getData();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR 1 \nNem számítható helyes érték,\n"
                        + "mivel érvénytelen összegek is léteznek az adatbázisban.");
            }
        }
        writer.close();
        
        //***************************************************************
        
        
        int darabszam = list.size();
        
        try{
            egyensúly = Math.round(sum / darabszam);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR 2 \nNem számítható helyes érték, \n"
                    + "mivel még egy bejegyzés sincs az adatbázisban.");
        }
        
        return egyensúly;
    }
}