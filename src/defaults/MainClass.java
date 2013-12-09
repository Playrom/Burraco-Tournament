package defaults;


import java.io.IOException;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO!!!  
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
        
        Torneo torneo=null;
        
        ArrayList coppie,turni=null;
        Object[] all;
        int numTurni=0;
        String nomeTorneo;
        
        
        String filename="src/defaults/coppia.xml";
        String filename2="src/defaults/coppia.xml";
        
        System.out.println("Digita 1 per caricare da file xml" );
        System.out.println("Digita 2 inserire coppie" );
        boolean loaded,started;//se 0 vuol dire che sto creando i turni, se 1 li sto caricando da xml
            if(Integer.valueOf(in.nextLine()) == 1){
                XmlParser parser=new XmlParser(filename);
                parser.run();
                all=parser.load();
                coppie=(ArrayList) all[0];
                torneo=(Torneo) all[1];
                loaded=true;
                started=torneo.isStarted();
                numTurni=torneo.getNumTurni();
                nomeTorneo=torneo.getNome();
                
            } 
            else {
                System.out.println("Nome del torneo: " );
                nomeTorneo=in.nextLine();
                System.out.println("Quanti turni? " );
                numTurni=Integer.valueOf(in.nextLine());
                System.out.println("Quante coppie partecipano? " );
                int numCoppie=Integer.valueOf(in.nextLine());

                CreationCoppie th1=new CreationCoppie(numCoppie);
                th1.run();

                coppie=th1.getCoppie();
                loaded=false;
                started=false;
            }
            
        
                
                if(started){
                    
                    
                } else {
                    
                    if(numTurni==0){
                        System.out.println("Quanti turni? " );
                        numTurni=Integer.valueOf(in.nextLine());                    
                    }
                    
                    while(true){
                        System.out.println("Digita 1 per stampare le coppie" );
                        System.out.println("Digita 2 per aggiungere coppie" );
                        System.out.println("Digita 3 per startare torneo" );
                        System.out.println("Digita 4 per salvare torneo e non startare" );
                        int temp=Integer.valueOf(in.nextLine());

                        if( temp == 1){
                            for(int e=0;e<coppie.size();e++){
                                Coppia tcop = (Coppia) coppie.get(e);
                                System.out.println(tcop.toAllString());
                            }
                        }

                        if( temp == 2){
                           System.out.println("Quante coppie vuoi aggiungere? " );
                           int numCoppie=Integer.valueOf(in.nextLine());
                           CreationCoppie th1=new CreationCoppie(numCoppie);
                           th1.run();

                           ArrayList newCoppie=th1.getCoppie();
                           for (Object x : newCoppie){
                               if (!coppie.contains(x)){
                               Coppia t=(Coppia) x;
                               t.setId(coppie.size());
                               coppie.add(x);
                               }

                           }
                        }
                        
                        if( temp == 3){
                            break;
                        }
                        if( temp == 4){
                            XmlWriter write=new XmlWriter(filename2,coppie,nomeTorneo,numTurni);
                            write.run();
                            System.exit(0);
                        }
                        
                    }
                  torneo=new Torneo(coppie,numTurni,true,nomeTorneo);
                }
              
            
            
            
        
        while(true){
        
        
       
        



            System.out.println("Digita 1 per ottenere la classifica" );
            System.out.println("Digita 2 per stampare il torneo" );
            System.out.println("Digita 3 per aggiungere punti turno" );
            System.out.println("Digita 4 per stampare status coppie" );  
            System.out.println("Digita 5 per scrivere su xml" ); 
            System.out.println("Digita 6 per annullare calcolo turno" );
            int temp=Integer.valueOf(in.nextLine());
            if( temp == 1){
                torneo.displayClassifica();
            }
            
            if(temp == 2){
                torneo.displayTorneo();
            }
            
            if(temp == 3){
                
                System.out.println("Digita numero turno da calcolare (numeri reali, non id)" );
                temp=Integer.valueOf(in.nextLine());
                torneo.calcolaTurno(temp-1);
            }
            
            if(temp == 4){
                torneo.displayStatus();
            }
            
            if(temp == 5){
                XmlWriter write=new XmlWriter(filename2,coppie,torneo);
                write.run();
            }
            
            if(temp == 6){
                
                System.out.println("Digita numero turno da annullare (numeri reali, non id)" );
                temp=Integer.valueOf(in.nextLine());
                torneo.annullaTurno(temp-1);
            }

            //Arrays.sort((Object[])torneo.coppie);
    
        }
    
           
    }


    
}
