package BackEnd_layer;

import FrontEnd_layer.Element_modify_GUI;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Element_modify {
  static String inputFile = "program_database.xml";

  public static void Element_modify_funciton() throws Exception {
    Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(new InputSource(inputFile));

    XPath xpath = XPathFactory.newInstance().newXPath();
    NodeList nodes = (NodeList)xpath.evaluate
        (Element_modify_GUI.elérési_út_mod, doc, XPathConstants.NODESET);

    for (int idx = 0; idx < nodes.getLength(); idx++) {
        nodes.item(idx).setTextContent(Element_modify_GUI.mire_mod);
    }
 
    Transformer xformer = TransformerFactory.newInstance().newTransformer();
    xformer.transform
        (new DOMSource(doc), new StreamResult(new File(inputFile)));
  }
  
//sokszorositas
  public static void Element_modify_funciton2() throws Exception {
    Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(new InputSource(inputFile));

    XPath xpath = XPathFactory.newInstance().newXPath();
    NodeList nodes = (NodeList)xpath.evaluate
        (Element_modify_GUI.elérési_út_mod2, doc, XPathConstants.NODESET);

    for (int idx = 0; idx < nodes.getLength(); idx++) {
        nodes.item(idx).setTextContent(Element_modify_GUI.mire_mod2);
    }
 
    Transformer xformer = TransformerFactory.newInstance().newTransformer();
    xformer.transform
        (new DOMSource(doc), new StreamResult(new File(inputFile)));
  }
  public static void Element_modify_funciton3() throws Exception {
    Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(new InputSource(inputFile));

    XPath xpath = XPathFactory.newInstance().newXPath();
    NodeList nodes = (NodeList)xpath.evaluate
        (Element_modify_GUI.elérési_út_mod3, doc, XPathConstants.NODESET);

    for (int idx = 0; idx < nodes.getLength(); idx++) {
        nodes.item(idx).setTextContent(Element_modify_GUI.mire_mod3);
    }

    Transformer xformer = TransformerFactory.newInstance().newTransformer();
    xformer.transform
        (new DOMSource(doc), new StreamResult(new File(inputFile)));
  }
  public static void Element_modify_funciton4() throws Exception {
    Document doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(new InputSource(inputFile));

    XPath xpath = XPathFactory.newInstance().newXPath();
    NodeList nodes = (NodeList)xpath.evaluate
        (Element_modify_GUI.elérési_út_mod4, doc, XPathConstants.NODESET);

    for (int idx = 0; idx < nodes.getLength(); idx++) {
        nodes.item(idx).setTextContent(Element_modify_GUI.mire_mod4);
    }
 
    Transformer xformer = TransformerFactory.newInstance().newTransformer();
    xformer.transform
        (new DOMSource(doc), new StreamResult(new File(inputFile)));
  }
}