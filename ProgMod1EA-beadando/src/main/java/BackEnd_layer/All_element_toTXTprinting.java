package BackEnd_layer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class All_element_toTXTprinting {
    public static void All_element_toTXTprinting_function() {
        try {
            File file = new File("program_database.xml");
            try (FileWriter output = new FileWriter("system_files/db_to_scr.txt")) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                
                NodeList nodeList = doc.getElementsByTagName("transaction");
                for (int itr = 0; itr < nodeList.getLength(); itr++) {
                    Node node = nodeList.item(itr);
                    //System.out.println("\nNode Name :" + node.getNodeName());
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        
                        //System.out.println("Tranzakcio azonosito: " + eElement.getElementsByTagName("azon").item(0).getTextContent());
                        output.write("\nTranzakcio azonosito :   "); output.write(eElement.getElementsByTagName("azon").item(0).getTextContent());
                        
                        //System.out.println("Pénzforgalom típusa: " + eElement.getElementsByTagName("type").item(0).getTextContent());
                        output.write("\nPénzforgalom típusa :   "); output.write(eElement.getElementsByTagName("type").item(0).getTextContent());
                        
                        //System.out.println("Dátum: " + eElement.getElementsByTagName("date").item(0).getTextContent());
                        output.write("\nDátum :   "); output.write(eElement.getElementsByTagName("date").item(0).getTextContent());
                        
                        //System.out.println("Összeg: " + eElement.getElementsByTagName("price").item(0).getTextContent());
                        output.write("\nÖsszeg :   "); output.write(eElement.getElementsByTagName("price").item(0).getTextContent());
                        
                        //System.out.println("Leírás: " + eElement.getElementsByTagName("description").item(0).getTextContent());
                        output.write("\nLeírás :   "); output.write(eElement.getElementsByTagName("description").item(0).getTextContent());
                        
                        output.write("\n----------------------------------------------"
                                + "----------------------------------------------------------------------");
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}