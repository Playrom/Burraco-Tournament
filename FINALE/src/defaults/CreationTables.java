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
public abstract class CreationTables implements Runnable{
    protected ArrayList turni;
    protected int numTurni,smazzate;
    
    public CreationTables(int numTurni,int smazzate){
        
        this.numTurni=numTurni;
        this.smazzate=smazzate;
        turni=new ArrayList(numTurni);
    }
    
    
    public ArrayList getTurni(){
        return turni;
    }
    
    abstract public Tavolo[] creaTavoliTurno(int i);

}
