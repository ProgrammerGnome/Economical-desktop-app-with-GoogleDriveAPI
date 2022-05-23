package BackEnd_layer;

import FrontEnd_layer.New_element;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;

public class New_element_to_node {
    public static String type = "";
    
    public static String date = "";
    public static String price = "";
    public static String description = "";
    
    public static String randomString = "";

    /*
    public static String ERROR_nodetag_bovito_S(String randomString) throws IOException {
        return randomString;
    }
    */
        
    public static void ERROR_nodetag_bovito() {
        try {
            File xmlFile = new File("program_database.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Element documentElement = document.getDocumentElement();
            
            Element textNode_randomtranz = document.createElement("azon");
            textNode_randomtranz.setTextContent(Integer.toString(New_element.fajlszamlalo));
            
            Element textNode = document.createElement("type");
            textNode.setTextContent(type);
            
            Element textNode1 = document.createElement("date");
            textNode1.setTextContent(date);
            
            Element textNode2 = document.createElement("price");
            textNode2.setTextContent(price);
            
            Element textNode3 = document.createElement("description");
            textNode3.setTextContent(description);
            
            Element nodeElement = document.createElement("transaction");
            
            nodeElement.appendChild(textNode_randomtranz);
            nodeElement.appendChild(textNode);
            nodeElement.appendChild(textNode1);
            nodeElement.appendChild(textNode2);
            nodeElement.appendChild(textNode3);
            
            documentElement.appendChild(nodeElement);
            document.replaceChild(documentElement, documentElement);
            Transformer tFormer =
                TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
            tFormer.transform(source, result);


        } catch (IOException | IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException | SAXException ex) {
            System.out.println(ex);
        }
    }
}