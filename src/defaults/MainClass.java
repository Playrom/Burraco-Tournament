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
        
        ArrayList coppie,turni;
        ArrayList[] all;
        
        
        String filename="src/defaults/coppia.xml";
        
        
        
           
 
        
        
        
        
        
        
        
        
        
        
        
        
        System.out.println("Digita 1 per caricare da file xml" );
        System.out.println("Digita 2 inserire coppie" );

            if(Integer.valueOf(in.nextLine()) == 1){
                XmlParser parser=new XmlParser(filename);
                parser.run();
                all=parser.load();
                coppie=all[0];
                turni=all[1];
                torneo=new Torneo(coppie,turni);
                
            } 
            else {
                System.out.println("Quanti turni? " );
                int numTurni=Integer.valueOf(in.nextLine());
                System.out.println("Quante coppie partecipano? " );
                int numCoppie=Integer.valueOf(in.nextLine());

                CreationCoppie th1=new CreationCoppie(numCoppie);
                th1.run();

                coppie=th1.getCoppie();
                torneo=new Torneo(coppie,numTurni);

            } 
        
        
        
        
        
        
        
       
        
          
        
        while(true){
        
        
       
        



            System.out.println("Digita 1 per continuare con un altro turno" );
            System.out.println("Digita 2 per stampare il torneo" );
            System.out.println("Digita 3 per aggiungere punti turno" );
            System.out.println("Digita 4 per stampare status coppie" );  
            int temp=Integer.valueOf(in.nextLine());
            if( temp == 1){
                torneo.displayTorneo();
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

            //Arrays.sort((Object[])torneo.coppie);
    
        }
    
           
    }


    
}
