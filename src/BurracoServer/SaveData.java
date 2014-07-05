/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author playrom
 */
public class SaveData extends Thread {
    
    @Override
    public void run(){
        ConnectDatabase database=new ConnectDatabase("all","aicon07","193.183.99.188",3306);
        try {
            ServerSocket server=new ServerSocket(8780);
            while(true){
                Socket socket=server.accept();
                InputStream is=socket.getInputStream();
            
                byte[] aByte = new byte[1];
                int bytesRead;

                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                fos = new FileOutputStream( "temp.xml" );
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do {
                        baos.write(aByte);
                        bytesRead = is.read(aByte);
                } while (bytesRead != -1);

                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();

                File elaborate=new File("temp.xml");
                TorneoDatabase temp=parse(elaborate);
                database.saveTorneo(temp.getName(), temp.getName_file());
            }
        } catch (IOException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static TorneoDatabase parse(File file){
        TorneoDatabase ret=null;
        try {  
  
            File xmlFile = file;  
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
            Document doc = documentBuilder.parse(xmlFile);  
  
            doc.getDocumentElement().normalize();  
   
   
   
            NodeList nodeFirst = doc.getElementsByTagName("Torneo");  
              
                Node node = nodeFirst.item(0);  
                if (node.getNodeType() == Node.ELEMENT_NODE) {  
                    Element elemento = (Element) node;
                    String nome=elemento.getElementsByTagName("nome").item(0).getTextContent();  
                    String name_file=nome.replace(" ", "-")+".xml";
                    
                    ret=new TorneoDatabase(-1,nome,name_file);
                    
                }
                
                file.renameTo(new File(ret.getName_file()));
            
            return ret;
   
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{
            return ret;
        }
    }
}
