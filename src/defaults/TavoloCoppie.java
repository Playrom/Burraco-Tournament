package defaults;

import exception.ErroreNonCoppia;
import exception.ErroreNonSingle;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class TavoloCoppie extends Tavolo{
    
    private Coppia cop1,cop2; //coppie a questo tavolo
    
    public TavoloCoppie(Coppia uno, Coppia due , int id,int pun1,int pun2 ,int smazzate ){
        super(id,pun1,pun2,smazzate);
        cop1=uno;
        cop2=due;
        
    }
    
    public TavoloCoppie(Coppia uno, Coppia due , int id , int smazzate){
        super(id,smazzate);
        cop1=uno;
        cop2=due;
        
    }
    
    
    
    @Override
    public void assegnaPunteggi(int pun1 , int pun2){ //assegno punteggio alle due coppie
        
        this.setPun1(pun1);
        this.setPun2(pun2);
        
        int max=this.differenza(pun1, pun2); //calcolo massimo differenza
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            cop1.setVictPoints(cop1.getVictPoints()+max);//coppia 1 prende max victory points
            cop2.setVictPoints(cop2.getVictPoints()+20-max); //coppia 2 prende 20-max victory points
            
            cop1.getUno().setVictory(cop1.getUno().getVictory()+max);//coppia 1 prende max victory points
            cop1.getDue().setVictory(cop1.getDue().getVictory()+max);//coppia 1 prende max victory points
            cop2.getUno().setVictory(cop2.getUno().getVictory()+20-max);//coppia 1 prende max victory points
            cop2.getDue().setVictory(cop2.getDue().getVictory()+20-max);//coppia 1 prende max victory points
            
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            cop1.setVictPoints(cop1.getVictPoints()+20-max);//coppia 1 prende 20-max victory points
            cop2.setVictPoints(cop2.getVictPoints()+max);//coppia 2 prende max victory points
            
            cop1.getUno().setVictory(cop1.getUno().getVictory()+20-max);//coppia 1 prende max victory points
            cop1.getDue().setVictory(cop1.getDue().getVictory()+20-max);//coppia 1 prende max victory points
            cop2.getUno().setVictory(cop2.getUno().getVictory()+max);//coppia 1 prende max victory points
            cop2.getDue().setVictory(cop2.getDue().getVictory()+max);//coppia 1 prende max victory points
            
        }
        
        cop1.setMastPoints(cop1.getMastPoints()+pun1); //assegno mastpoints1
        cop2.setMastPoints(cop2.getMastPoints()+pun2); //assegno mastpoints2
        
        cop1.getUno().setMaster(cop1.getUno().getMaster()+pun1);//coppia 1 prende max victory points
        cop1.getDue().setMaster(cop1.getDue().getMaster()+pun1);//coppia 1 prende max victory points
        cop2.getUno().setMaster(cop2.getUno().getMaster()+pun2);//coppia 1 prende max victory points
        cop2.getDue().setMaster(cop2.getDue().getMaster()+pun2);//coppia 1 prende max victory points

        
    }
    
    @Override
    public void annullaPunteggi(){
        int max=this.differenza(pun1, pun2); //calcolo massimo differenza
        
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            cop1.setVictPoints(cop1.getVictPoints()-max);//coppia 1 prende max victory points
            cop2.setVictPoints(cop2.getVictPoints()-(20-max)); //coppia 2 prende 20-max victory points
            
            cop1.getUno().setVictory(cop1.getUno().getVictory()-max);//coppia 1 prende max victory points
            cop1.getDue().setVictory(cop1.getDue().getVictory()-max);//coppia 1 prende max victory points
            cop2.getUno().setVictory(cop2.getUno().getVictory()-(20-max));//coppia 1 prende max victory points
            cop2.getDue().setVictory(cop2.getDue().getVictory()-(20-max));//coppia 1 prende max victory points
            
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            cop1.setVictPoints(cop1.getVictPoints()-(20-max));//coppia 1 prende 20-max victory points
            cop2.setVictPoints(cop2.getVictPoints()-max);//coppia 2 prende max victory points
            
            cop1.getUno().setVictory(cop1.getUno().getVictory()-(20-max));//coppia 1 prende max victory points
            cop1.getDue().setVictory(cop1.getDue().getVictory()-(20-max));//coppia 1 prende max victory points
            cop2.getUno().setVictory(cop2.getUno().getVictory()-max);//coppia 1 prende max victory points
            cop2.getDue().setVictory(cop2.getDue().getVictory()-max);//coppia 1 prende max victory points
            
        }
        
        cop1.setMastPoints(cop1.getMastPoints()-pun1); //assegno mastpoints1
        cop2.setMastPoints(cop2.getMastPoints()-pun2); //assegno mastpoints2
        
        cop1.getUno().setMaster(cop1.getUno().getMaster()-pun1);//coppia 1 prende max victory points
        cop1.getDue().setMaster(cop1.getDue().getMaster()-pun1);//coppia 1 prende max victory points
        cop2.getUno().setMaster(cop2.getUno().getMaster()-pun2);//coppia 1 prende max victory points
        cop2.getDue().setMaster(cop2.getDue().getMaster()-pun2);//coppia 1 prende max victory points

        
        this.setPun1(0);
        this.setPun2(0);
    }

    public Coppia getCop1() {
        return cop1;
    }

    public void setCop1(Coppia cop1) {
        this.cop1 = cop1;
    }

    public Coppia getCop2() {
        return cop2;
    }

    public void setCop2(Coppia cop2) {
        this.cop2 = cop2;
    }
    

    
    
    public static boolean checkCoppia (Object e) throws ErroreNonCoppia{ 
        if((e instanceof Coppia)){
            return true;
        } else { throw new ErroreNonCoppia();
    }
}
    
    @Override
    public String toString(){
            return    "Coppia 1: " + cop1.getUno().getName()
                    + " - " + cop1.getDue().getName()
                    + " --> " + this.getPun1()
                    + "\nCoppia 2: " + cop2.getUno().getName()
                    + " - " + cop2.getDue().getName()
                    + " --> " + this.getPun2()
                    + "\n------------";
    }
    
   

    @Override
    public String toStringCop1() {
        return cop1.toString();
    }

    @Override
    public String toStringCop2() {
        return cop2.toString();
    }
    
}
