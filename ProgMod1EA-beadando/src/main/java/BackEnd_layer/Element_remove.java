package BackEnd_layer;

import FrontEnd_layer.Element_remove_GUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Element_remove {
 
    public static void Element_remove_function() throws Exception {
        
        try{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
         
        Document doc = db.parse(new FileInputStream(new File("program_database.xml")));
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        
        
        XPathExpression expr = xpath.compile("//transaction[azon<"+Element_remove_GUI.elérési_sorszám+"]/azon/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        ArrayList<String> list=new ArrayList<>(); 
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i).getNodeValue());
        }
        int torlendo = list.size();
        
        Element element = (Element) doc.getElementsByTagName("transaction").item(torlendo);
 
        element.getParentNode().removeChild(element);
         
        doc.normalize();
         
        prettyPrint(doc);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR 3\nAz Ön által megadott tranzakció azonosítóval\n"
                    + "rednelkező elem nem létezik az adatbázisban.");
        } 
    }
     
    public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        //System.out.print(out.toString());
        String kiiro = out.toString();
        
        try (FileOutputStream fout = new FileOutputStream("program_database.xml")) {
            String kiiro_r = kiiro.replace("    ", "");
            String kiiro_r2 = kiiro_r.replace("\n", "");
            char ch[] = kiiro_r2.toCharArray();
            
            int i;
            for (i = 0; i < kiiro_r2.length(); i++) {
                fout.write(ch[i]);
            }
        }
    } 
}