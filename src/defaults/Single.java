/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

/**
 *
 * @author playrom
 */
public class Single implements Comparable {
    
    private int id;
    private String name;
    protected int victory;
    protected int master;
    boolean alone;
    
    
    
    public Single(int id,String nome,boolean alone){
        this.id=id;
        this.name=nome;
        this.alone=alone;
        victory=0;
        master=0;
    }

    public Single(int id, String nome, int victory, int master, boolean alone) {
        this(id,nome,alone);
        this.victory=victory;
        this.master=master;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public boolean isAlone() {
        return alone;
    }

    public void setAlone(boolean alone) {
        this.alone = alone;
    }
    
    @Override
    public String toString() {
        return   name;
    }
    
    public int compareTo(Single cop){
        int confront=0;
        
        if(cop==null) return -2;
        
        if(this.getVictory()==cop.getVictory()){ //Se i punti Victori delle coppie sono uguali
            if(this.getMaster()==cop.getMaster()) confront = 0; //Se Punti partite totali sono uguali allora coppie a pari merito
            else if(this.getMaster()>cop.getMaster()) confront =-1; //Se no Coppia 1 è piu alta in classifica
            else if(this.getMaster()<cop.getMaster()) confront = 1; //Se no Coppia 1 è piu bassa in classifica
        }
        
        else if(this.getVictory() > cop.getVictory()) confront = -11; //Se victory points coppia 1 sono maggiori allora piu alta in classifica
        else if(this.getVictory()<cop.getVictory()) confront =1; //Se victory point coppia 1 sono minori allora piu bassa in classifica
        
        return confront;
    }
    
    public int compareTo(Object o){
        if(o!=null && o instanceof Single  ){ //override della comparazione
            return compareTo((Single) o);
            }
        return -2;
    }
    
    
    
}
