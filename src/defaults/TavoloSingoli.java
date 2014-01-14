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
    
    private int pla1,pla2,pla3,pla4; //1-2 Coppia Uno , 3-4 Coppia 4
    private ArrayList singles;
    
    public TavoloSingoli(int uno,int due,int tre,int quattro,int id,int pun1, int pun2,ArrayList singles){
        super(id,pun1,pun2);
        this.pla1=uno;
        this.pla2=due;
        this.pla3=tre;
        this.pla4=quattro;
        
        this.singles=singles;
    }

    public TavoloSingoli(int pla1, int pla2, int pla3, int pla4, int id, ArrayList singles) {
        super(id);
        this.pla1 = pla1;
        this.pla2 = pla2;
        this.pla3 = pla3;
        this.pla4 = pla4;
        this.singles = singles;
        pun1=0;
        pun2=0;
    }
    

    @Override
    public void assegnaPunteggi(int pun1, int pun2) {
        this.setPun1(pun1);
        this.setPun2(pun2);
        
        int max=Tavolo.differenza(pun1, pun2); //calcolo massimo differenza
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            findSingle(pla1).setVictory(findSingle(pla1).getVictory()+max);//coppia 1 prende max victory points
            findSingle(pla2).setVictory(findSingle(pla2).getVictory()+max);//coppia 1 prende max victory points
            findSingle(pla3).setVictory(findSingle(pla3).getVictory()+20-max); //coppia 2 prende 20-max victory points
            findSingle(pla4).setVictory(findSingle(pla4).getVictory()+20-max); //coppia 2 prende 20-max victory points
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            findSingle(pla1).setVictory(findSingle(pla1).getVictory()+20-max);//coppia 1 prende 20-max victory points
            findSingle(pla2).setVictory(findSingle(pla2).getVictory()+20-max);//coppia 1 prende 20-max victory points
            findSingle(pla3).setVictory(findSingle(pla3).getVictory()+max);//coppia 2 prende max victory points
            findSingle(pla4).setVictory(findSingle(pla4).getVictory()+max);//coppia 2 prende max victory points
        }
        
        findSingle(pla1).setMaster(findSingle(pla1).getMaster()+pun1); //assegno mastpoints1
        findSingle(pla2).setMaster(findSingle(pla2).getMaster()+pun1); //assegno mastpoints1
        findSingle(pla3).setMaster(findSingle(pla3).getMaster()+pun2); //assegno mastpoints2
        findSingle(pla4).setMaster(findSingle(pla4).getMaster()+pun2); //assegno mastpoints2
    }

    @Override
    public void annullaPunteggi() {
        int max=Tavolo.differenza(pun1, pun2); //calcolo massimo differenza

        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            findSingle(pla1).setVictory(findSingle(pla1).getVictory()-max);//coppia 1 prende max victory points
            findSingle(pla2).setVictory(findSingle(pla2).getVictory()-max);//coppia 1 prende max victory points
            findSingle(pla3).setVictory(findSingle(pla3).getVictory()-(20-max)); //coppia 2 prende 20-max victory points
            findSingle(pla4).setVictory(findSingle(pla4).getVictory()-(20-max)); //coppia 2 prende 20-max victory points
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            findSingle(pla1).setVictory(findSingle(pla1).getVictory()-(20-max));//coppia 1 prende 20-max victory points
            findSingle(pla2).setVictory(findSingle(pla2).getVictory()-(20-max));//coppia 1 prende 20-max victory points
            findSingle(pla3).setVictory(findSingle(pla3).getVictory()-max);//coppia 2 prende max victory points
            findSingle(pla4).setVictory(findSingle(pla4).getVictory()-max);//coppia 2 prende max victory points
        }
        
        findSingle(pla1).setMaster(findSingle(pla1).getMaster()-pun1); //assegno mastpoints1
        findSingle(pla2).setMaster(findSingle(pla2).getMaster()-pun1); //assegno mastpoints1
        findSingle(pla3).setMaster(findSingle(pla3).getMaster()-pun2); //assegno mastpoints2
        findSingle(pla4).setMaster(findSingle(pla4).getMaster()-pun2); //assegno mastpoints2
        
        this.setPun1(0);
        this.setPun2(0);
    
    }

    public int getPla1() {
        return pla1;
    }

    public void setPla1(int pla1) {
        this.pla1 = pla1;
    }

    public int getPla2() {
        return pla2;
    }

    public void setPla2(int pla2) {
        this.pla2 = pla2;
    }

    public int getPla3() {
        return pla3;
    }

    public void setPla3(int pla3) {
        this.pla3 = pla3;
    }

    public int getPla4() {
        return pla4;
    }

    public void setPla4(int pla4) {
        this.pla4 = pla4;
    }

    

    public ArrayList getSingles() {
        return singles;
    }

    public void setSingles(ArrayList singles) {
        this.singles = singles;
    }
    
    
    public Single findSingle(int id){
        for(int i=0;i<singles.size();i++){
            Single temp=(Single) singles.get(i);
            try{
                checkSingle(temp);
                if(temp.getId()==id){
                    return temp;
                }
            }catch(ErroreNonSingle e){
                System.out.println("Errore non single");
            }
        }
        
        return null;
    }
    
    
    
    public static boolean checkSingle (Object e) throws ErroreNonSingle{ 
        if((e instanceof Single)){
            return true;
            } else { throw new ErroreNonSingle();
        }
    }   
    
    
    @Override
    public String toString(){
            return    "Coppia 1: " + this.findSingle(this.getPla1()).getName()
                    + " - " + this.findSingle(this.getPla2()).getName()
                    + " --> " + this.getPun1()
                    + "\nCoppia 2: " + this.findSingle(this.getPla3()).getName()
                    + " - " + this.findSingle(this.getPla4()).getName()
                    + " --> " + this.getPun2()
                    + "\n------------";
    }

    @Override
    public String toStringCop1() {
        return findSingle(pla1).toString() + " - " + findSingle(pla2).toString();
    }

    @Override
    public String toStringCop2() {
        return findSingle(pla3).toString() + " - " + findSingle(pla4).toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        if (this.pla1 != other.pla1  && this.pla1 != other.pla2 && this.pla1 != other.pla3 && this.pla1 != other.pla4) {
            temp=false;
        }
        if (this.pla2 != other.pla1  && this.pla2 != other.pla2 && this.pla2 != other.pla3 && this.pla2 != other.pla4) {
            temp=false;
        }
        if (this.pla3 != other.pla1  && this.pla3 != other.pla2 && this.pla4 != other.pla3 && this.pla4 != other.pla4) {
            temp=false;
        }
        if (this.pla4 != other.pla1  && this.pla4 != other.pla2 && this.pla4 != other.pla3 && this.pla4 != other.pla4) {
            temp=false;
        }
        return temp;
    }
    
    
    
    
    
    
}
