/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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

/**
 *
 * @author Giorgio
 */
public class SearchDatabase extends Thread{
    public void run(){
        File transferFile;
        System.out.println("Server-8777:avviato");
        try {
            ServerSocket serverSocket=new ServerSocket(8777);
            while(true){
                ConnectDatabase database=new ConnectDatabase("all","aicon07","193.183.99.188",3306);
                Socket socket=serverSocket.accept();
                InputStream is=socket.getInputStream();
                
                System.out.println("Server-8777:Collegato sulla socket di ricerca nel database, porta 8777 , cerco nel database");
                    
                transferFile = createXML(database.dumpTornei());

                                   
                OutputStream os = socket.getOutputStream();

                System.out.println("Server-8777:Sending Files..."); 

                byte [] bytearray = new byte [(int)transferFile.length()];
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(transferFile));
                bis.read(bytearray, 0, bytearray.length);

                os.write(bytearray,0,bytearray.length);
                os.flush();
                System.out.println("Server-8777:File Sent!"); 
                
                socket.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static File parseSelectionTournament(File file){
        File toRet = null;
        try {  
  
            File xmlFile = file;  
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
            Document doc = documentBuilder.parse(xmlFile);  
  
            doc.getDocumentElement().normalize();  
   
   
   
            String path = doc.getElementsByTagName("select_tournament").item(0).getTextContent();  
            toRet=new File(path);
            
   
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{
            return toRet;
        }
    }
    
    public static File createXML(ArrayList<TorneoDatabase> tornei){
        File tempfile=null;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            
            for(TorneoDatabase torneo:tornei){
            
                Element element = doc.createElement("Tournament");
                rootElement.appendChild(element);
                
                Element element_id = doc.createElement("id");
                element_id.appendChild(doc.createTextNode(String.valueOf(torneo.getId())));
                element.appendChild(element_id);
                
                Element element_name = doc.createElement("name");
                element_name.appendChild(doc.createTextNode(String.valueOf(torneo.getName())));
                element.appendChild(element_name);
                
                Element element_path = doc.createElement("name_file");
                element_path.appendChild(doc.createTextNode(String.valueOf(torneo.getName_file())));
                element.appendChild(element_path);
            
            }
            
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                return tempfile;
            
        }
                
        
    }
}
