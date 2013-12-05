package defaults;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
   
    
    public Torneo(int numCoppie,int numTurni){
        
        this.coppie=new ArrayList(numCoppie);
        this.turni=new ArrayList(numTurni);
        this.fisse=new Urna(numCoppie/2);
        this.mobili=new Urna(numCoppie/2);
        this.fisse.createCasualArray();
        this.mobili.createCasualArray();
         
        
                 
        int k=0;
        for(int i=0;i<coppie.size();i=i+2){
            
            System.out.println("Coppia " + (i+1) );
            
            coppie.add(new Coppia("Test1" , "Test2" , (i) , false ));
            
            System.out.println("Coppia " + (i+2) );
            
            coppie.add(new Coppia("Test1" , "Test2" , (i+1) , true ));
            
            k++;

            
        }
        
       
        
    }
    
    public  Torneo(ArrayList coppie){
        this.coppie=coppie;
        
    }
    
     public  Torneo(ArrayList coppie,ArrayList turni){
        this.coppie=coppie;
        this.turni=turni;
        
    }
    
    public  Torneo(ArrayList coppie,int numTurni){
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
        
        CreationTables th2=new CreationTables(coppie,numTurni);
        th2.run();
        
        this.turni=th2.getTurni();
    }
    
    public void displayTorneo(){
        System.out.println("TUTTI I TAVOLI DEL TORNEO\n");
        int i=0;
        for(;i<turni.size();i++){
            Turno temp=(Turno) turni.get(i);
            System.out.println("TURNO " + (i+1) + temp.isCalcolato() + "\n");
            temp.displayTables();
        }
            
    
    }
    
    public void displayStatus() {
        System.out.println("NOTIZIE SULLE COPPIE DEL TORNEO\n");
        int i=0;
        System.out.println( "|\tNome1\t\t\tNome2\t\t\tVictory\t\t\tMaster");
        for(;i<turni.size();i++){
            
            Coppia temp;
            temp = (Coppia) coppie.get(i);
            System.out.println(temp.toAllString());
            
        }
    }
    
    public void calcolaTurno(int turno) throws IOException {
        //Operazione di calcolo del turno
        
        AssignPoints th3=new AssignPoints(this.turni,coppie,turno);
        th3.run();
       
    }
    
    public static boolean checkCoppia (Object e) throws ErroreNonCoppia{
        if((e instanceof Coppia)){
            return true;
        } else { throw new ErroreNonCoppia();
    }
    
    }
    
    
    
    
    
    
    
    
    
    
}
