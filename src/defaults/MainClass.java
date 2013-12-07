package defaults;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Giorgio
 */
public class MainClass  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassCastException {
        
        Scanner in = new Scanner(System.in); //Inizializzo lettore di linea
        
        Torneo torneo;
        
        ArrayList coppie,turni=null;
        ArrayList[] all;
        int numTurni=0;
        
        
        String filename="src/defaults/coppia.xml";
        String filename2="src/defaults/coppia.xml";
        
        System.out.println("Digita 1 per caricare da file xml" );
        System.out.println("Digita 2 inserire coppie" );
        Boolean loaded;//se 0 vuol dire che sto creando i turni, se 1 li sto caricando da xml
            if(Integer.valueOf(in.nextLine()) == 1){
                XmlParser parser=new XmlParser(filename);
                parser.run();
                all=parser.load();
                coppie=all[0];
                turni=all[1];
                loaded=true;
                
            } 
            else {
                System.out.println("Quanti turni? " );
                numTurni=Integer.valueOf(in.nextLine());
                System.out.println("Quante coppie partecipano? " );
                int numCoppie=Integer.valueOf(in.nextLine());

                CreationCoppie th1=new CreationCoppie(numCoppie);
                th1.run();

                coppie=th1.getCoppie();
                loaded=false;

            }
            
            
            if(loaded){
                torneo=new Torneo(coppie,turni);
            }else {
                torneo=new Torneo(coppie,numTurni);
            }
        
        
        
        
        
        
        
       
        
          
        
        while(true){
        
        
       
        



            System.out.println("Digita 1 per ottenere la classifica" );
            System.out.println("Digita 2 per stampare il torneo" );
            System.out.println("Digita 3 per aggiungere punti turno" );
            System.out.println("Digita 4 per stampare status coppie" );  
            System.out.println("Digita 5 per scrivere su xml" ); 
            int temp=Integer.valueOf(in.nextLine());
            if( temp == 1){
                torneo.displayClassifica();
            }
            
            if(temp == 2){
                torneo.displayTorneo();
            }
            
            if(temp == 3){
                
                System.out.println("Digita numero turno da stampare (numeri reali, non id)" );
                temp=Integer.valueOf(in.nextLine());
                torneo.calcolaTurno(temp-1);
            }
            
            if(temp == 4){
                torneo.displayStatus();
            }
            
            if(temp == 5){
                XmlWriter write=new XmlWriter(filename2,coppie,torneo.getTurni());
                write.run();
            }

            //Arrays.sort((Object[])torneo.coppie);
    
        }
    
           
    }


    
}
