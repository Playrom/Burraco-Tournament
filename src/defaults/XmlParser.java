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

    Object passing[];
    String filename;
    int smazzate;
    ArrayList<Single> singles;
    ArrayList<Coppia> coppie;
    
    XmlParser(String name){
        passing=new Object[3];
        filename=name;
    }
  


 
 
 public Object[] load() {  
  try {  
  
   File xmlFile = new File(this.filename);  
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
     .newInstance();  
   DocumentBuilder documentBuilder = documentFactory  
     .newDocumentBuilder();  
   Document doc = documentBuilder.parse(xmlFile);  
  
   doc.getDocumentElement().normalize();  
   
   
   
   NodeList nodeSingle = doc.getElementsByTagName("Single");  
   
   passing[2]=new ArrayList<Single>(nodeSingle.getLength());
   singles=(ArrayList<Single>) passing[2];
  
  
   for (int temp = 0; temp < nodeSingle.getLength(); temp++) {  
    Node node = nodeSingle.item(temp);  
  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;
  
     int id=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
     String nome=elemento.getElementsByTagName("nome").item(0).getTextContent();  
     int master,victory;
     boolean alone=Boolean.valueOf(elemento.getElementsByTagName("alone").item(0).getTextContent());
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
     
     singles.add(new Single(id,nome,victory,master,alone));
  
    }  
   }
   
   
   
   
   
   
   
   NodeList nodeList = doc.getElementsByTagName("Coppia");  
   
   passing[0]=new ArrayList(nodeList.getLength());
   coppie=(ArrayList) passing[0];
  
  
   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
    Node node = nodeList.item(temp);  
  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;
  
     int id=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
     int nome1=Integer.valueOf(elemento.getElementsByTagName("nome1").item(0).getTextContent());  
     int nome2=Integer.valueOf(elemento.getElementsByTagName("nome2").item(0).getTextContent());  
     boolean tipo=Boolean.valueOf(elemento.getElementsByTagName("tipo").item(0).getTextContent());
     int master,victory,id_database;
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
     
     try{
        id_database=Integer.valueOf(elemento.getElementsByTagName("id_database").item(0).getTextContent());  
     }catch(java.lang.NumberFormatException e){
        id_database=-1;
     }
     
     Single one=null,two=null;
     
     for(Single tempSing:singles){
         if(tempSing.getId()==nome1) {
             one=tempSing;
         }
         if(tempSing.getId()==nome2) {
             two=tempSing;
         }
     }
     
     coppie.add(new Coppia(one,two,id,tipo,master,victory));
  
    }  
   }
   
    NodeList nodeTorneo = doc.getElementsByTagName("Torneo");
    
    Element elementTorneo=(Element) nodeTorneo.item(0);
    
    String nomeTorneo=elementTorneo.getElementsByTagName("nome").item(0).getTextContent();
    boolean started=Boolean.valueOf(elementTorneo.getElementsByTagName("started").item(0).getTextContent());
    int numTurni=Integer.valueOf(elementTorneo.getElementsByTagName("numturni").item(0).getTextContent());
    boolean alone=Boolean.valueOf(elementTorneo.getElementsByTagName("alone").item(0).getTextContent());
    smazzate=Integer.valueOf(elementTorneo.getElementsByTagName("smazzate").item(0).getTextContent());
    
    NodeList nodeTurni=elementTorneo.getElementsByTagName("Turno");
    
    
    ArrayList turni=new ArrayList(nodeTurni.getLength());
   
   for (int temp = 0; temp < nodeTurni.getLength(); temp++) {  
    Node node = nodeTurni.item(temp);  
    
  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element elemento = (Element) node;
     int idTurno=Integer.valueOf(elemento.getElementsByTagName("id").item(0).getTextContent());  
     int numTavoli=Integer.valueOf(elemento.getElementsByTagName("numtavoli").item(0).getTextContent());
     boolean calcolato=Boolean.valueOf(elemento.getElementsByTagName("calcolato").item(0).getTextContent());

     Tavolo[] tavoli=new Tavolo[numTavoli];
     
     NodeList tav = elemento.getElementsByTagName("Tavolo");
     
     if (alone) {
         getTavoliSingle(tav,tavoli);
     } else {
         getTavoliCoppie(tav,tavoli);
     }
     
     ArrayList templist=null;
     if(alone) templist=singles;
     else templist=coppie;
     
  
     turni.add(new Turno(templist,tavoli,idTurno,calcolato,alone,smazzate));
     

    }
   }
   
   if (alone) {
        passing[1]=new Torneo(singles,turni,started,nomeTorneo,numTurni,smazzate);
     } else {
        passing[1]=new Torneo(coppie,singles,turni,started,nomeTorneo,numTurni,smazzate);
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
 
 
 public void getTavoliCoppie(NodeList tav,Tavolo[] tavoli){
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
                    Coppia one=null,two=null;
                    for(Coppia tempCo:coppie){
                        if(tempCo.getId()==id_uno) {
                            one=tempCo;
                        }
                        if(tempCo.getId()==id_due) {
                            two=tempCo;
                        }
                    }

                    tavoli[temp2]=new TavoloCoppie(one,two,id,pun1,pun2,smazzate);

                    System.out.println("test");
                }
            
     }
 }
 
 
 public void getTavoliSingle(NodeList tav,Tavolo[] tavoli){
     for (int temp2 = 0; temp2 < tav.getLength(); temp2++) {  
        Node nodeTav = tav.item(temp2);  
  
  
            if (nodeTav.getNodeType() == Node.ELEMENT_NODE) {  
  
                 Element elem = (Element) nodeTav;
     
     
                    int id,id_uno,id_due,id_tre,id_quattro,pun1,pun2;

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
                        id_tre=Integer.valueOf(elem.getElementsByTagName("id_tre").item(0).getTextContent());
                    }catch(java.lang.NumberFormatException e){
                        id_tre=0;
                    }
                    
                    try{ 
                        id_quattro=Integer.valueOf(elem.getElementsByTagName("id_quattro").item(0).getTextContent());
                    }catch(java.lang.NumberFormatException e){
                        id_quattro=0;
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
                    
                    Single one=null,two=null,three=null,four=null;
                    for(Single tempSing:singles){
                        if(tempSing.getId()==id_uno) {
                            one=tempSing;
                        }
                        if(tempSing.getId()==id_due) {
                            two=tempSing;
                        }
                        if(tempSing.getId()==id_tre) {
                            three=tempSing;
                        }
                        if(tempSing.getId()==id_quattro) {
                            four=tempSing;
                        }
                    }

                    tavoli[temp2]=new TavoloSingoli(one,two,three,four,id,pun1,pun2,smazzate);

                    System.out.println("test");
                }
            
     }
 }

    
}
