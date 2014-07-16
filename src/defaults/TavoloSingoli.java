/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;


import exception.ErroreNonSingle;
import java.util.ArrayList;


/**
 *
 * @author playrom
 */
public class TavoloSingoli extends Tavolo{
    
    private Single pla1,pla2,pla3,pla4; //1-2 Coppia Uno , 3-4 Coppia 4
    
    public TavoloSingoli(Single uno,Single due,Single tre,Single quattro,int id,int pun1, int pun2,int smazzate){
        super(id,pun1,pun2,smazzate);
        this.pla1=uno;
        this.pla2=due;
        this.pla3=tre;
        this.pla4=quattro;
        
    }

    public TavoloSingoli(Single pla1, Single pla2, Single pla3, Single pla4, int id,int smazzate) {
        super(id,smazzate);
        this.pla1 = pla1;
        this.pla2 = pla2;
        this.pla3 = pla3;
        this.pla4 = pla4;
        pun1=0;
        pun2=0;
    }
    

    @Override
    public void assegnaPunteggi(int pun1, int pun2) {
        this.setPun1(pun1);
        this.setPun2(pun2);
        
        int max=this.differenza(pun1, pun2); //calcolo massimo differenza
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            pla1.setVictory(pla1.getVictory()+max);//coppia 1 prende max victory points
            pla2.setVictory(pla2.getVictory()+max);//coppia 1 prende max victory points
            pla3.setVictory(pla3.getVictory()+20-max); //coppia 2 prende 20-max victory points
            pla4.setVictory(pla4.getVictory()+20-max); //coppia 2 prende 20-max victory points
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            pla1.setVictory(pla1.getVictory()+20-max);//coppia 1 prende 20-max victory points
            pla2.setVictory(pla2.getVictory()+20-max);//coppia 1 prende 20-max victory points
            pla3.setVictory(pla3.getVictory()+max);//coppia 2 prende max victory points
            pla4.setVictory(pla4.getVictory()+max);//coppia 2 prende max victory points
        }
        
        pla1.setMaster(pla1.getMaster()+pun1); //assegno mastpoints1
        pla2.setMaster(pla2.getMaster()+pun1); //assegno mastpoints1
        pla3.setMaster(pla3.getMaster()+pun2); //assegno mastpoints2
        pla4.setMaster(pla4.getMaster()+pun2); //assegno mastpoints2
    }

    @Override
    public void annullaPunteggi() {
        int max=this.differenza(pun1, pun2); //calcolo massimo differenza

        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            pla1.setVictory(pla1.getVictory()-max);//coppia 1 prende max victory points
            pla2.setVictory(pla2.getVictory()-max);//coppia 1 prende max victory points
            pla3.setVictory(pla3.getVictory()-(20-max)); //coppia 2 prende 20-max victory points
            pla4.setVictory(pla4.getVictory()-(20-max)); //coppia 2 prende 20-max victory points
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            pla1.setVictory(pla1.getVictory()-(20-max));//coppia 1 prende 20-max victory points
            pla2.setVictory(pla2.getVictory()-(20-max));//coppia 1 prende 20-max victory points
            pla3.setVictory(pla3.getVictory()-max);//coppia 2 prende max victory points
            pla4.setVictory(pla4.getVictory()-max);//coppia 2 prende max victory points
        }
        
        pla1.setMaster(pla1.getMaster()-pun1); //assegno mastpoints1
        pla2.setMaster(pla2.getMaster()-pun1); //assegno mastpoints1
        pla3.setMaster(pla3.getMaster()-pun2); //assegno mastpoints2
        pla4.setMaster(pla4.getMaster()-pun2); //assegno mastpoints2
        
        this.setPun1(0);
        this.setPun2(0);
    
    }

    
    
    @Override
    public String toString(){
            return    "Coppia 1: " + pla1.getName()
                    + " - " + pla2.getName()
                    + " --> " + this.getPun1()
                    + "\nCoppia 2: " + pla3.getName()
                    + " - " + pla4.getName()
                    + " --> " + this.getPun2()
                    + "\n------------";
    }

    @Override
    public String toStringCop1() {
        return pla1.toString() + " - " + pla2.toString();
    }

    @Override
    public String toStringCop2() {
        return pla3.toString() + " - " + pla4.toString();
    }


    @Override
    public boolean equals(Object obj) {
        boolean temp=true;
        if (obj == null) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return true;
        }
        final TavoloSingoli other = (TavoloSingoli) obj;
        if (!this.pla1.equals(other.pla1)  && !this.pla1.equals(other.pla2) && !this.pla1.equals(other.pla3) && !this.pla1.equals(other.pla4)) {
            temp=false;
        }
        if (!this.pla2.equals(other.pla1)  && !this.pla2.equals(other.pla2) && !this.pla2.equals(other.pla3) && !this.pla2.equals(other.pla4)) {
            temp=false;
        }
        if (!this.pla3.equals(other.pla1)  && !this.pla3.equals(other.pla2) && !this.pla3.equals(other.pla3) && !this.pla3.equals(other.pla4)) {
            temp=false;
        }
        if (!this.pla4.equals(other.pla1)  && !this.pla4.equals(other.pla2) && !this.pla4.equals(other.pla3) && !this.pla4.equals(other.pla4)) {
            temp=false;
        }
        return temp;
    }

    public Single getPla1() {
        return pla1;
    }

    public void setPla1(Single pla1) {
        this.pla1 = pla1;
    }

    public Single getPla2() {
        return pla2;
    }

    public void setPla2(Single pla2) {
        this.pla2 = pla2;
    }

    public Single getPla3() {
        return pla3;
    }

    public void setPla3(Single pla3) {
        this.pla3 = pla3;
    }

    public Single getPla4() {
        return pla4;
    }

    public void setPla4(Single pla4) {
        this.pla4 = pla4;
    }
    
    
    
    
    
    
}
