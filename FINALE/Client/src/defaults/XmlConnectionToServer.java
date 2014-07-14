/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Giorgio
 */
public class XmlConnectionToServer {
    
    
    
    public static File sendSelection(String path){
        File tempfile=null;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            
            Element element = doc.createElement("select_tournament");
            element.appendChild(doc.createTextNode(String.valueOf(path)));
            rootElement.appendChild(element);
            
            // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(tempfile=new File("temp_send.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File prepared for sent!");
                
                return tempfile;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                return tempfile;
            
        }
                
        
    }
    
    
    public static ArrayList<TorneoDatabase> parseListTournament(File file){
        ArrayList<TorneoDatabase> tornei=new ArrayList();
        try {  
  
            File xmlFile = file;  
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
            Document doc = documentBuilder.parse(xmlFile);  
  
            doc.getDocumentElement().normalize();  
   
   
   
            NodeList nodeFirst = doc.getElementsByTagName("Tournament");  
            for (int temp = 0; temp < nodeFirst.getLength(); temp++) {  
                Node node = nodeFirst.item(temp);  
                if (node.getNodeType() == Node.ELEMENT_NODE) {  
                    Element elemento = (Element) node;
                    int id=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
                    String nome=elemento.getElementsByTagName("name").item(0).getTextContent();  
                    String name_file=elemento.getElementsByTagName("name_file").item(0).getTextContent();  
                    
                    tornei.add(new TorneoDatabase(id,nome,name_file));
                    
                }
            }
            
            return tornei;
   
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{
            return tornei;
        }
    }
    
}
