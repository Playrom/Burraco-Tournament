/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
 * @author playrom
 */
public class LoadPreference {
    
    
    
    public static Map load(File file){
        Map<String,String> preference=null;
        try {  
  
            File xmlFile = file;  
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
            Document doc = documentBuilder.parse(xmlFile);  
  
            doc.getDocumentElement().normalize();  
   
            preference=new HashMap();
   
            NodeList nodeFirst = doc.getElementsByTagName("preferences");  
              
                Node node = nodeFirst.item(0);  
                if (node.getNodeType() == Node.ELEMENT_NODE) {  
                    Element elemento = (Element) node;
                    String ip=elemento.getElementsByTagName("ip_server").item(0).getTextContent();  
                    preference.put("ip_server", ip);
                    
                    String ip_database=elemento.getElementsByTagName("ip_database").item(0).getTextContent();  
                    preference.put("ip_database", ip_database);
                    
                    
                    String port_database=elemento.getElementsByTagName("port_database").item(0).getTextContent(); 
                    preference.put("port_database", port_database);
                    
                    String username=elemento.getElementsByTagName("username").item(0).getTextContent(); 
                    preference.put("username", username);
                    
                    String pass=elemento.getElementsByTagName("password").item(0).getTextContent(); 
                    preference.put("password", pass);
                    
                    String port_connection=elemento.getElementsByTagName("port_connection").item(0).getTextContent(); 
                    preference.put("port_connection", port_connection);
                    
                    String port_save=elemento.getElementsByTagName("port_save").item(0).getTextContent(); 
                    preference.put("port_save", port_save);
                    
                    String database_name=elemento.getElementsByTagName("database_name").item(0).getTextContent(); 
                    preference.put("database_name", database_name);
                    

                    
                }
                
                return preference;
                
            
        }catch (Exception e) {  

            preference.put("ip_server", "127.0.0.1");
            preference.put("port_database", "3306");
            preference.put("port_database", "3306");
            preference.put("port_save", "8780");
            preference.put("password", "aicon07");
            preference.put("port_connection", "8777");
            preference.put("ip_database", "193.183.99.188");
            preference.put("database_name", "burraco");
            preference.put("username", "all");
            
            
            return preference;
        }
    }
    
    public static void save(File file,HashMap<String,String> preference){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("preferences");
            doc.appendChild(rootElement);
            
            Element element = doc.createElement("ip_server");
            element.appendChild(doc.createTextNode(String.valueOf(preference.get("ip_server"))));
            rootElement.appendChild(element);
            
            Element element_port_data = doc.createElement("port_database");
            element_port_data.appendChild(doc.createTextNode(String.valueOf(preference.get("port_database"))));
            rootElement.appendChild(element_port_data);
            
            Element element_user = doc.createElement("username");
            element_user.appendChild(doc.createTextNode(String.valueOf(preference.get("username"))));
            rootElement.appendChild(element_user);
            
            Element element_pass = doc.createElement("password");
            element_pass.appendChild(doc.createTextNode(String.valueOf(preference.get("password"))));
            rootElement.appendChild(element_pass);
            
            Element element_connection = doc.createElement("port_connection");
            element_connection.appendChild(doc.createTextNode(String.valueOf(preference.get("port_connection"))));
            rootElement.appendChild(element_connection);
            
            Element element_data = doc.createElement("database_name");
            element_data.appendChild(doc.createTextNode(String.valueOf(preference.get("database_name"))));
            rootElement.appendChild(element_data);
            
            // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
                
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
}
