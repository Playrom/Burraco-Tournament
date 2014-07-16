package defaults;


import exception.ErroreNonCoppia;
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
    
    
    protected ArrayList turni,coppie,copfisse,copmobili,singles;
    private Urna fisse,mobili;
    private int numTurni,smazzate;
    private boolean started,alone;
    private String nome;
   
    
     public  Torneo(ArrayList coppie,ArrayList singles,ArrayList turni){ // costruttore con liste premade
        this.coppie=coppie;
        this.singles=singles;
        this.turni=turni;
        numTurni=turni.size();
        this.alone=false;
        
    }
     
      public  Torneo(ArrayList singles,ArrayList turni){ // costruttore con liste premade
        this.singles=singles;
        this.turni=turni;
        numTurni=turni.size();
        this.alone=true;
        
    }
     
     public  Torneo(ArrayList coppie,ArrayList singles,ArrayList turni,boolean started,String nome){ // costruttore con liste premade
        this(coppie,singles,turni);
        this.started=started;
        this.nome=nome;
        
    }
     
     public  Torneo(ArrayList singles,ArrayList turni,boolean started,String nome){ // costruttore con liste premade
        this(singles,turni);
        this.started=started;
        this.nome=nome;
        
    }
     
     public  Torneo(ArrayList coppie,ArrayList singles,ArrayList turni,boolean started,String nome,int numTurni){ // costruttore con liste premade
        this(coppie,singles,turni,started,nome);
        this.numTurni=numTurni;
        
    }
     
      public  Torneo(ArrayList coppie,ArrayList singles,ArrayList turni,boolean started,String nome,int numTurni,int smazzate){ // costruttore con liste premade
        this(coppie,singles,turni,started,nome,smazzate);
        this.numTurni=numTurni;
        
    }
     
     public  Torneo(ArrayList singles,ArrayList turni,boolean started,String nome,int smazzate){ // costruttore con liste premade
        this(singles,turni,started,nome);
        this.smazzate=smazzate;
        
    }
     
     public  Torneo(ArrayList singles,ArrayList turni,boolean started,String nome,int numTurni,int smazzate){ // costruttore con liste premade
        this(singles,turni,started,nome,smazzate);
        this.numTurni=numTurni;
        
    }
    
    public  Torneo(ArrayList coppie,ArrayList singles,int numTurni,int smazzate){ // costruttore con liste premade , devo creare i turni
        this.coppie=coppie;
        this.singles=singles;
        this.numTurni=numTurni;
        this.alone=alone;
        this.smazzate=smazzate;
        creaTurni();
    }
    
    public  Torneo(ArrayList coppie,ArrayList singles,int numTurni,boolean started,String nome,int smazzate){
        this(coppie,singles,numTurni,smazzate);
        this.started=started;
        this.nome=nome;
    }
    
    
    public  Torneo(ArrayList singles,int numTurni,int smazzate){ // costruttore con liste premade , devo creare i turni
        this.singles=singles;
        this.numTurni=numTurni;
        this.alone=true;
        this.smazzate=smazzate;
        creaTurni();
    }
    
    public  Torneo(ArrayList singles,int numTurni,boolean started,String nome,int smazzate){
        this(singles,numTurni,smazzate);
        this.started=started;
        this.nome=nome;
    }
    
    public void creaTurni(){
        this.turni=new ArrayList(numTurni);
        
        
        
        this.started=true;
        
        CreationTables th2;
        if(alone){
            th2=new CreationTablesSingle(singles,numTurni,smazzate);
        } else {
            th2=new CreationTablesCoppie(coppie,numTurni,smazzate);
        }
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
    
    /*public void displayStatus() { //display tutte le coppie e la loro situazione
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
    }2
    
    public void calcolaTurno(int turno) throws IOException {//Operazione di calcolo del turno
        
        AssignPoints th3=new AssignPoints(this.turni,coppie,turno);
        th3.run();
       
    }
    
    public void annullaTurno(int turno) throws IOException {//Operazione di calcolo del turno
        
        CancelPoints th3=new CancelPoints(this.turni,coppie,turno);
        th3.run();
       
    }*/
    

    public boolean isAlone() {
        return alone;
    }

    public void setAlone(boolean alone) {
        this.alone = alone;
    }
    
    

    public ArrayList getTurni() {
        return turni;
    }

    public void setTurni(ArrayList turni) {
        this.turni = turni;
    }

    public int getNumTurni() {
        return numTurni;
    }

    public void setNumTurni(int numTurni) {
        this.numTurni = numTurni;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSmazzate() {
        return smazzate;
    }

    public void setSmazzate(int smazzate) {
        this.smazzate = smazzate;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
