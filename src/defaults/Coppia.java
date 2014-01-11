package defaults;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class Coppia implements Comparable{
    
    private int uno; //Nome primo giocatore
    private int due; //Nome secondo giocatore
    private int id; //ID della coppia
    private boolean mobile; //0 Coppia Fissa - 1 Coppia Mobile
    protected int victory; //Victory Points della coppia
    protected int master; //Somma dei punti delle partite totali
    SingleList singles;
    
    public Coppia(int uno, int due , int id , boolean tipo , ArrayList singles  ){
        this.uno=uno;
        this.due=due;
        this.id=id;
        this.mobile=tipo;
        this.singles=(SingleList) singles;
        victory=0;
        master=0;
    }
    
    
    
 
    
    public Coppia(int uno, int due , int id , boolean tipo , int master,int victory,ArrayList singles){
        this(uno,due,id,tipo,singles);
        this.master=master;
        this.victory=victory;
    }
    
    
    public String getName1 (){
        return singles.findSingle(uno).getName();
    }
   
    public String getName2 (){
        return singles.findSingle(due).getName();
    }
    
   
    
    public boolean getType(){
        if(this instanceof Coppia){
            return mobile;
        } else {
            return false;
        }
    }
    
        
    public int getVictPoints(){
        return victory;
    }
    
    public int getMastPoints(){
        return master;
    }
    
    public void setName1(String name ){
        singles.findSingle(uno).setName(name);
    }
    
    public void setName2(String name ){
        singles.findSingle(due).setName(name);
    }
    
    public void setType(boolean type){
        mobile=type;
    }
    
    public void setVictPoints(int punti){
        victory=punti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }
    
    
    
    
    
    
    public void setMastPoints(int punti){
        master=punti;
    }

    @Override
    public String toString() {
        return   singles.findSingle(uno).getName() + " - " + singles.findSingle(due).getName();
    }
    
    public String toAllString(){
        return "|\t" + singles.findSingle(uno).getName()  + "\t\t\t" + singles.findSingle(due).getName()  +"\t\t\t"+victory+"\t\t\t" + master;
    }
    
    
    
    
    public int compareTo(Coppia cop){
        int confront=0;
        
        if(cop==null) return -2;
        
        if(this.getVictPoints()==cop.getVictPoints()){ //Se i punti Victori delle coppie sono uguali
            if(this.getMastPoints()==cop.getMastPoints()) confront = 0; //Se Punti partite totali sono uguali allora coppie a pari merito
            else if(this.getMastPoints()>cop.getMastPoints()) confront =-1; //Se no Coppia 1 è piu alta in classifica
            else if(this.getMastPoints()<cop.getMastPoints()) confront = 1; //Se no Coppia 1 è piu bassa in classifica
        }
        
        else if(this.getVictPoints()>cop.getVictPoints()) confront = -11; //Se victory points coppia 1 sono maggiori allora piu alta in classifica
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
