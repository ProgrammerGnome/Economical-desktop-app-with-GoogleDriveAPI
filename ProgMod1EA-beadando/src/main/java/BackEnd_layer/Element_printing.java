package BackEnd_layer;

import FrontEnd_layer.Element_printing_GUI;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
 
public class Element_printing 
{
  public static void Element_printing_function() throws Exception 
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("program_database.xml");
 

 
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();
    
    
    XPathExpression expr = xpath.compile(Element_printing_GUI.elérési_út);
    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nodes = (NodeList) result;
    
    FileOutputStream fkiir = new FileOutputStream("system_files/search.txt");
    
    //String kifele = nodes.toString();
    char separate[] = "\n".toCharArray();
    
    for (int i = 0; i < nodes.getLength(); i++) {
        char[] ch = nodes.item(i).getNodeValue().toCharArray();
        int hossz = ch.length;
        //System.out.println(i);
        for (int j = 0; j < hossz; j++){fkiir.write(ch[j]);}
        fkiir.write(separate[0]);
    }
    
    Kiír mukodj = new Kiír();
    mukodj.Ki();
    
  }
}