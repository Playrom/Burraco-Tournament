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
    protected int smazzate;
    
    public Tavolo(int id,int pun1,int pun2,int smazzate){
        
        this.id=id;
        this.pun1=pun1;
        this.pun2=pun2;
        this.smazzate=smazzate;
        
    }
    
    public Tavolo(int id ,int smazzate){
        this.id=id;
        this.smazzate=smazzate;
        pun1=pun2=0;
        
    }

    public int getSmazzate() {
        return smazzate;
    }

    public void setSmazzate(int smazzate) {
        this.smazzate = smazzate;
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
    
    
    
    
    
    
    
    public int differenza(int pun1 , int pun2){
        int somma=0; //punti massimi

        if(smazzate==3){
            int differenza=Math.abs(pun1 - pun2);
        

            if (differenza<=50) {
                somma=10;
            }

            if (differenza<=150 && differenza > 50) {
                somma=11;
            }

            if (differenza>150 && differenza <=250){
                somma=12;
            }

            if (differenza>250 && differenza <=350) {
                somma=13;
            }

            if (differenza>350 && differenza <=500) {
                somma=14;
            }

            if (differenza>500 & differenza <=650) {
                somma=15;
            }

            if (differenza>650 && differenza<= 820) {
                somma=16;
            }

            if (differenza>825 && differenza <= 1000) {
                somma=17;
            }

            if (differenza>1000 && differenza<=1250) {
                somma=18;
            }

            if (differenza>1250 && differenza<=1500) {
                somma=19;
            }

            if (differenza>1500) {
                somma=20;
            }
        } else if(smazzate==4){
            
            int differenza=Math.abs(pun1 - pun2);
        

            if (differenza<=100) {
                somma=10;
            }

            if (differenza<=300 && differenza > 100) {
                somma=11;
            }

            if (differenza>300 && differenza <=500){
                somma=12;
            }

            if (differenza>500 && differenza <=700) {
                somma=13;
            }

            if (differenza>700 && differenza <=900) {
                somma=14;
            }

            if (differenza>900 & differenza <=1100) {
                somma=15;
            }

            if (differenza>1100 && differenza<= 1300) {
                somma=16;
            }

            if (differenza>1300 && differenza <= 1500) {
                somma=17;
            }

            if (differenza>1500 && differenza<=1700) {
                somma=18;
            }

            if (differenza>1700 && differenza<=2000) {
                somma=19;
            }

            if (differenza>2000) {
                somma=20;
            }
            
        }
    
        return somma;
        
    }
    
    
    abstract public String toStringCop1();
    abstract public String toStringCop2();
    
    

   
    
   
    
   
    
}
