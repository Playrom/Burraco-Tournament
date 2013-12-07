package defaults;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class Torneo {
    
    
    protected ArrayList turni,coppie,copfisse,copmobili;
    private Urna fisse,mobili;
    private int numTurni;
   
    
     public  Torneo(ArrayList coppie,ArrayList turni){ // costruttore con liste premade
        this.coppie=coppie;
        this.turni=turni;
        
    }
    
    public  Torneo(ArrayList coppie,int numTurni){ // costruttore con liste premade , devo creare i turni
        this.coppie=coppie;
        this.numTurni=numTurni;
        this.turni=new ArrayList(numTurni);
        
        copfisse=new ArrayList(this.coppie.size()/2); //meta coppie sono fisse 
        copmobili=new ArrayList(this.coppie.size()/2); //meta coppie sono mobili
       
        int k=0,j=0;
        for(int i=0 ; i<this.coppie.size() ; i++){
           try{
            Coppia temp=(Coppia) coppie.get(i);
            if(temp.getType() && this.checkCoppia(temp)){ //se data[i] coppia mobile , 1 indica coppia mobile
              copmobili.add(temp); //inserisco nell'array di coppie mobili
              k++;
            } else {
                copfisse.add(temp);//senno inserisco in array coppie fisse
                j++;
            }
           } catch(ErroreNonCoppia e) {
               System.out.println("Errore - Non è coppia");
               
           }

            
        
            
            
        }
        
        CreationTables th2=new CreationTables(coppie,numTurni); // creo thread per creazione tavoli e turni
        th2.run();
        
        this.turni=th2.getTurni();
    }
    
    public void displayTorneo(){ //display del torneo , tutti i tavoli di tutti i turni , e indica se il turno stato calcolato
        System.out.println("TUTTI I TAVOLI DEL TORNEO\n");
        int i=0;
        for(;i<turni.size();i++){
            Turno temp=(Turno) turni.get(i);
            System.out.println("TURNO " + (i+1) + temp.isCalcolato() + "\n");
            temp.displayTables();
        }
            
    
    }
    
    public void displayStatus() { //display tutte le coppie e la loro situazione
        System.out.println("NOTIZIE SULLE COPPIE DEL TORNEO\n");
        int i=0;
        System.out.println( "|\tNome1\t\t\tNome2\t\t\tVictory\t\t\tMaster");
        for(;i<coppie.size();i++){
            
            Coppia temp;
            temp = (Coppia) coppie.get(i);
            System.out.println(temp.toAllString());
            
        }
    }
    
    public void displayClassifica(){ //display coppie e loro situazione in ordine
        System.out.println("CLASSIFICA COPPIE TORNEO\n");
        int i=0;
        System.out.println( "|\tNome1\t\t\tNome2\t\t\tVictory\t\t\tMaster");
        ArrayList ordinate=new ArrayList(coppie);
        Collections.sort(ordinate);
        for(;i<ordinate.size();i++){
            
            Coppia temp;
            temp = (Coppia) ordinate.get(i);
            System.out.println(temp.toAllString());
            
        } 
    }
    
    public void calcolaTurno(int turno) throws IOException {//Operazione di calcolo del turno
        
        AssignPoints th3=new AssignPoints(this.turni,coppie,turno);
        th3.run();
       
    }
    
    public static boolean checkCoppia (Object e) throws ErroreNonCoppia{//check se l'elemento è una coppia
        if((e instanceof Coppia)){
            return true;
        } else { throw new ErroreNonCoppia();
    }
    
    }

    public ArrayList getTurni() {
        return turni;
    }

    public void setTurni(ArrayList turni) {
        this.turni = turni;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
