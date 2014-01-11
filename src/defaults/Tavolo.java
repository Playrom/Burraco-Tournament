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
public abstract class Tavolo implements Tables{
    
    protected int pun1,pun2;
    protected int id; //id del tavolo
    
    public Tavolo(int id,int pun1,int pun2){
        
        this.id=id;
        this.pun1=pun1;
        this.pun2=pun2;
        
    }
    
    public Tavolo(int id ){
        this.id=id;
        pun1=pun2=0;
        
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPun1() {
        return pun1;
    }

    public void setPun1(int pun1) {
        this.pun1 = pun1;
    }

    public int getPun2() {
        return pun2;
    }

    public void setPun2(int pun2) {
        this.pun2 = pun2;
    }
    
    
    
    
    
    
    
    static public int differenza(int pun1 , int pun2){
        
        int differenza=Math.abs(pun1 - pun2); //valore assoluto della differenza
        
        int somma=0; //punti massimi
        
        if (differenza<55) {
            somma=10;
        }
    
        if (differenza<155 && differenza > 50) {
            somma=11;
        }
    
        if (differenza>150 && differenza <255){
            somma=12;
        }
    
        if (differenza>250 && differenza <355) {
            somma=13;
        }
    
        if (differenza>350 && differenza <505) {
            somma=14;
        }
    
        if (differenza>500 & differenza <655) {
            somma=15;
        }

        if (differenza>650 && differenza< 825) {
            somma=16;
        }
    
        if (differenza>825 && differenza < 1005) {
            somma=17;
        }
    
        if (differenza>1000 && differenza<1255) {
            somma=18;
        }
    
        if (differenza>1250 && differenza<1505) {
            somma=19;
        }
    
        if (differenza>=1505) {
            somma=20;
        }
    
        return somma;
        
    }
    
    
    abstract public String toStringCop1();
    abstract public String toStringCop2();
    
    

   
    
   
    
   
    
}
