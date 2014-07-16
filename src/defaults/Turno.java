package defaults;


import exception.ErroreNonCoppia;
import exception.ErroreNonSingle;
import exception.ErroreGiaCalcolato;
import java.io.IOException;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class Turno {
    
    
    Tavolo tavoli[]; //tutti i tavoli a questo turno
    ArrayList<Coppia> copfisse,copmobili;
    ArrayList all,tutte[];//elenco tutte le coppie fisse e mobili.
    Urna fisse,mobili;
    boolean calcolato,tipo;
    int id,numTavoli,smazzate;
    
    public Turno(ArrayList data ,int id, boolean tipo,int smazzate){
        
        this.calcolato=false;
        this.id=id;
        all=data;
        this.tipo=tipo;
        
        this.smazzate=smazzate;
       
        if(tipo){
            singleTurno();
        }else{
            coppieTurno();
        }
        
    }
    
    
    
    
    
    
    public Turno(ArrayList data , Tavolo[] tavoli,int id,boolean tipo,int smazzate){//Creo un turno avendo tavoli e coppie
        
                
        copfisse=new ArrayList(data.size()/2); //meta coppie sono fisse 
        copmobili=new ArrayList(data.size()/2); //meta coppie sono mobili
        this.tavoli=tavoli; //esistono tavoli per meta delle coppie
        this.calcolato=false;
        this.id=id;
        all=data;
        this.numTavoli=tavoli.length;
        this.tipo=tipo;
        this.smazzate=smazzate;
        
    }
    
    
    public Turno(ArrayList data , Tavolo[] tavoli,int id,boolean calcolato,boolean tipo,int smazzate){
        this(data,tavoli,id,tipo,smazzate);
        this.calcolato=calcolato;
    }
    
    
    
    public void displayTables(){//stampo le info di ogni tavolo di questo turno
        
        for(int n=0;n<tavoli.length;n++){
            System.out.println("Tavolo " + tavoli[n].getId()  + " - " + tavoli.length + " tavoli in totale");
            System.out.println(tavoli[n].toString());
            
        }
    }

    public Tavolo[] getTavoli() {
        return tavoli;
    }

    public void setTavoli(Tavolo[] tavoli) {
        this.tavoli = tavoli;
    }
    
    public Tavolo getTavolo(int id) {
        return tavoli[id];
    }

    public Boolean getCalcolato() {
        return calcolato;
    }

    public void setCalcolato(Boolean calcolato) {
        this.calcolato = calcolato;
    }
    
    public String isCalcolato(){
        if(calcolato) return " Calcolato ";
        else return " Da Calcolare ";
    }
    
    public int getNumTavoli(){
        return this.numTavoli;
    }
    
    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    
    
    public void coppieTurno(){
        
        copfisse=new ArrayList<Coppia>(all.size()/2); //meta coppie sono fisse 
        copmobili=new ArrayList<Coppia>(all.size()/2); //meta coppie sono mobili
        this.fisse=new Urna(all.size()/2);
        this.mobili=new Urna(all.size()/2);
        this.fisse.createCasualArray();
        this.mobili.createCasualArray();
        
        this.tavoli=new Tavolo[(all.size())/2]; //esistono tavoli per meta delle coppie
        this.numTavoli=tavoli.length;
        
        int k=0,j=0;
        for(int i=0 ; i<all.size() ; i++){
                Coppia temp=(Coppia) all.get(i);
                
                if(temp.getType()){ //se data[i] coppia mobile , 1 indica coppia mobile
                    copmobili.add(temp); //inserisco nell'array di coppie mobili
                    k++;
                } else {
                    copfisse.add(temp);//senno inserisco in array coppie fisse
                    j++;
                }
       
            
        }
       
        
        
        for(int i=0;i<tavoli.length;i++){

            System.out.println(i);
            Coppia tempfisse=copfisse.get(fisse.movingNext());//prendo l'elemento con id contenuto nell'array dell'urna fisse
            Coppia tempmobili=copmobili.get(mobili.movingNext());//ugualmente per l'urna mobile
                
            tavoli[i]=new TavoloCoppie(tempfisse , tempmobili , i ,smazzate);
            
            
        }
        
    }
    
    public void singleTurno(){
        
        tutte=new ArrayList[4];
        
        
        this.tavoli=new Tavolo[(all.size())/4]; //esistono tavoli per meta delle coppie
        this.numTavoli=tavoli.length;
        
        this.fisse=new Urna(all.size());
        fisse.createCasualArray();


         for(int i=0;i<tavoli.length;i++){
             Single toad[]=new Single[4];
             System.out.println(i);
                
                 for(int k=0;k<toad.length;k++){
                     toad[k]= (Single) all.get(fisse.movingNext());
                 }
                 
                 tavoli[i]=new TavoloSingoli(toad[0], toad[1], toad[2], toad[3] , i ,smazzate);// costruisco tavolo cercando la coppia fissa e mobile per ogni tavolo, e assegno l'id al tavolo

         }

     }

    
    public void checkCalcolato() throws ErroreGiaCalcolato{ //check se questo turno è gia stato calcolato 
            if(this.calcolato==true) throw new ErroreGiaCalcolato();
     }
    
    
}
