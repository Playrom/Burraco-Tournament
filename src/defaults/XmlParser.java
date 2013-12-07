package defaults;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */

import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;

public class XmlParser implements Runnable{

    ArrayList passing[];
    String filename;
    
    XmlParser(String name){
        passing=new ArrayList[2];
        filename=name;
    }
  

 public void readXML() {  
  try {  
  
   File xmlFile = new File(this.filename);  
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
     .newInstance();  
   DocumentBuilder documentBuilder = documentFactory  
     .newDocumentBuilder();  
   Document doc = documentBuilder.parse(xmlFile);  
  
   doc.getDocumentElement().normalize();  
   NodeList nodeList = doc.getElementsByTagName("Coppia");  
  
   System.out.println("Root element :"  
     + doc.getDocumentElement().getNodeName());  
  
   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
    Node node = nodeList.item(temp);  
  
    System.out.println("\nElement type :" + node.getNodeName());  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;  
  
     System.out.println("ID : "  
       + elemento.getElementsByTagName("id").item(0)  
         .getTextContent());  
     System.out.println("Nome 1 : "  
       + elemento.getElementsByTagName("nome1").item(0)  
         .getTextContent());  
     System.out.println("Nome 2 : "  
       + elemento.getElementsByTagName("nome2").item(0)  
         .getTextContent());  
     System.out.println("Tipo : "  
       + elemento.getElementsByTagName("tipo").item(0)  
         .getTextContent());  
     System.out.println("Master : "  
       + elemento.getElementsByTagName("master").item(0)  
         .getTextContent());  
     
     System.out.println("Victory : "  
       + elemento.getElementsByTagName("victory").item(0)  
         .getTextContent());  
  
    }  
   }  
  } catch (Exception e) {  
   e.printStackTrace();  
  }  
 } 
 
 
 
 
 public ArrayList[] load() {  
  try {  
  
   File xmlFile = new File(this.filename);  
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
     .newInstance();  
   DocumentBuilder documentBuilder = documentFactory  
     .newDocumentBuilder();  
   Document doc = documentBuilder.parse(xmlFile);  
  
   doc.getDocumentElement().normalize();  
   NodeList nodeList = doc.getElementsByTagName("Coppia");  
   
   passing[0]=new ArrayList(nodeList.getLength());
   ArrayList coppie=passing[0];
  
  
   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
    Node node = nodeList.item(temp);  
  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;
  
     int id=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
     String nome1=elemento.getElementsByTagName("nome1").item(0).getTextContent();  
     String nome2=elemento.getElementsByTagName("nome2").item(0).getTextContent();  
     boolean tipo=Boolean.valueOf(elemento.getElementsByTagName("tipo").item(0).getTextContent());
     int master,victory;
     try{
         master=Integer.valueOf(elemento.getElementsByTagName("master").item(0).getTextContent());
     }catch(java.lang.NumberFormatException e){
         master=0;
     }
     
     
     try{
        victory=Integer.valueOf(elemento.getElementsByTagName("victory").item(0).getTextContent());  
     }catch(java.lang.NumberFormatException e){
        victory=0;
     }
     
     coppie.add(new Coppia(nome1,nome2,id,tipo,master,victory));
  
    }  
   }
   
    NodeList nodeTurni = doc.getElementsByTagName("Turno");  
    passing[1]=new ArrayList(nodeTurni.getLength());
    ArrayList turni=passing[1];
   
   for (int temp = 0; temp < nodeTurni.getLength(); temp++) {  
    Node node = nodeTurni.item(temp);  
    
  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;
     int idTurno=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
     int numTavoli=Integer.valueOf(elemento.getElementsByTagName("numtavoli").item(0).getTextContent());
     boolean calcolato=Boolean.valueOf(elemento.getElementsByTagName("calcolato").item(0).getTextContent());

     Tavolo[] tavoli=new Tavolo[numTavoli];
     
     NodeList tav = elemento.getElementsByTagName("Tavolo");
     
     
     for (int temp2 = 0; temp2 < tav.getLength(); temp2++) {  
        Node nodeTav = tav.item(temp2);  
  
  
            if (nodeTav.getNodeType() == Node.ELEMENT_NODE) {  
  
                 Element elem = (Element) nodeTav;
     
     
                    int id,id_uno,id_due,pun1,pun2;

                    try{ 
                        id=Integer.valueOf(elem.getElementsByTagName("id").item(0).getTextContent());
                    }catch(java.lang.NumberFormatException e){
                        id=0;
                    }

                    try{ 
                       id_uno=Integer.valueOf(elem.getElementsByTagName("id_uno").item(0).getTextContent()); 
                    }catch(java.lang.NumberFormatException e){
                       id_uno=0;
                    }

                    try{
                       id_due=Integer.valueOf(elem.getElementsByTagName("id_due").item(0).getTextContent()); 
                    }catch(java.lang.NumberFormatException e){
                       id_due=0;
                    }

                    try{
                      pun1=Integer.valueOf(elem.getElementsByTagName("pun1").item(0).getTextContent());  
                    }catch(java.lang.NumberFormatException e){
                      pun1=0;
                    }

                    try{
                      pun2=Integer.valueOf(elem.getElementsByTagName("pun2").item(0).getTextContent());  
                    }catch(java.lang.NumberFormatException e){
                       pun2=0;
                    }

                    tavoli[temp2]=new Tavolo(id_uno,id_due,id,pun1,pun2,coppie);

                    System.out.println("test");
                }
            
     }
  
     turni.add(new Turno(coppie,tavoli,idTurno,calcolato));
    }
   }
   
   
   
  } catch (Exception e) {  
   e.printStackTrace();  
  }
  
  return passing;

 } 
 

 
 
 public void run(){
     
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
 
             try {
                    DocumentBuilder builder = dbf.newDocumentBuilder();
                    File xmlFile = new File(filename);
                    Document document = builder.parse(xmlFile);
                    this.readXML();
             } catch (SAXException sxe) {
                    Exception  x = sxe;
                    if (sxe.getException() != null)  x = sxe.getException();
                    x.printStackTrace();
             } catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
             } catch (IOException ioe) {
                    ioe.printStackTrace();
             }
     
 }

    
}
