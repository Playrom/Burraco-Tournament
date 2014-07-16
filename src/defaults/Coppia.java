package defaults;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * La classe coppia presenta l'indicazione degli id dei suoi due componenti
 * @author Giorgio
 */
public class Coppia implements Comparable{
    
    private Single uno; //Nome primo giocatore
    private Single due; //Nome secondo giocatore
    private int id; //ID della coppia
    private boolean mobile; //0 Coppia Fissa - 1 Coppia Mobile

    /**
     *
     */
    protected int victory; //Victory Points della coppia

    /**
     *
     */
    protected int master; //Somma dei punti delle partite totali
    
    /**
     * Questo costruttore serve per inizializzare la coppia , da usare se la coppia viene creata al momento
     * @param uno ID del primo giocatore
     * @param due ID del secondo giocatore 
     * @param id ID della coppia
     * @param tipo Tipologia della coppia, false - COPPIA FISSA , true - COPPIA MOBILE
     * @param singles SingleList contenente tutti i giocatori del torneo
     */
    public Coppia(Single uno, Single due , int id , boolean tipo   ){
        this.uno=uno;
        this.due=due;
        this.id=id;
        this.mobile=tipo;
        victory=0;
        master=0;
    }
    
    

    /**
     * Questo costruttore serve per inizializzare tutti i componenti della coppia, utilizzare se torneo già avviato
     * @param uno ID del primo giocatore
     * @param due ID del secondo giocatore 
     * @param id ID della coppia
     * @param tipo Tipologia della coppia, false - COPPIA FISSA , true - COPPIA MOBILE
     * @param singles SingleList contenente tutti i giocatori del torneo
     * @param master Numero intero per indicare il numero di master point totali della coppia
     * @param victory Numero intero per indicare il numero di victory point totali della coppia
     */
    public Coppia(Single uno, Single due , int id , boolean tipo , int master,int victory){
        this(uno,due,id,tipo);
        this.master=master;
        this.victory=victory;
    }

    /**
     *
     * @return
     */
    public Single getUno (){
        return uno;
    }
   
    /**
     *
     * @return
     */
    public Single getDue (){
        return due;
    }
    

    /**
     *
     * @return
     */
    public boolean getType(){
        if(this instanceof Coppia){
            return mobile;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public int getVictPoints(){
        return victory;
    }
    
    /**
     *
     * @return
     */
    public int getMastPoints(){
        return master;
    }
    
    /**
     *
     * @param name
     */
    
    
    /**
     *
     * @param type
     */
    public void setType(boolean type){
        mobile=type;
    }
    
    /**
     *
     * @param punti
     */
    public void setVictPoints(int punti){
        victory=punti;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    

    /**
     *
     * @param punti
     */
    public void setMastPoints(int punti){
        master=punti;
    }

    @Override
    public String toString() {
        return   uno.getName() + " - " + due.getName();
    }
    
    /**
     *
     * @return
     */
    public String toAllString(){
        return "|\t" + uno.getName()  + "\t\t\t" + due.getName()  +"\t\t\t"+victory+"\t\t\t" + master;
    }

    /**
     *
     * @param cop
     * @return
     */
    public int compareTo(Coppia cop){
        int confront=0;
        
        if(cop==null) return -2;
        
        if(this.getVictPoints()==cop.getVictPoints()){ //Se i punti Victori delle coppie sono uguali
            if(this.getMastPoints()==cop.getMastPoints()) confront = 0; //Se Punti partite totali sono uguali allora coppie a pari merito
            else if(this.getMastPoints()>cop.getMastPoints()) confront =-1; //Se no Coppia 1 è piu alta in classifica
            else if(this.getMastPoints()<cop.getMastPoints()) confront = 1; //Se no Coppia 1 è piu bassa in classifica
        }
        
        else if(this.getVictPoints()>cop.getVictPoints()) confront = -1; //Se victory points coppia 1 sono maggiori allora piu alta in classifica
        else if(this.getVictPoints()<cop.getVictPoints()) confront =1; //Se victory point coppia 1 sono minori allora piu bassa in classifica
        
        return confront;
    }
    
    @Override
    public int compareTo(Object o){
        if(o!=null && o instanceof Coppia  ){ //override della comparazione
            return compareTo((Coppia) o);
            }
        return -2;
    }
    
    
}
