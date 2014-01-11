package defaults;

import exception.ErroreNonCoppia;
import exception.ErroreNonSingle;
import static defaults.TavoloSingoli.checkSingle;
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
    
    private int cop1,cop2; //coppie a questo tavolo
    private ArrayList coppie,singles;
    
    public TavoloCoppie(int uno, int due , int id,int pun1,int pun2 , ArrayList coppie, ArrayList singles ){
        super(id,pun1,pun2);
        cop1=uno;
        cop2=due;
        this.coppie=coppie;
        this.singles=singles;
        
    }
    
    public TavoloCoppie(int uno, int due , int id , ArrayList coppie, ArrayList singles){
        super(id);
        cop1=uno;
        cop2=due;
        this.coppie=coppie;
        this.singles=singles;
        
    }
    
    public Coppia findCoppia(int id){
        for(int i=0;i<coppie.size();i++){
            Coppia temp=(Coppia) coppie.get(i);
            try{
                checkCoppia(temp);
                if(temp.getId()==id){
                    return temp;
                }
            }catch(ErroreNonCoppia e){
                System.out.println("Errore non coppia");
            }
        }
        
        return null;
    }

    
    
    
    
    
    @Override
    public void assegnaPunteggi(int pun1 , int pun2){ //assegno punteggio alle due coppie
        
        this.setPun1(pun1);
        this.setPun2(pun2);
        
        int max=Tavolo.differenza(pun1, pun2); //calcolo massimo differenza
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            findCoppia(cop1).setVictPoints(findCoppia(cop1).getVictPoints()+max);//coppia 1 prende max victory points
            findCoppia(cop2).setVictPoints(findCoppia(cop2).getVictPoints()+20-max); //coppia 2 prende 20-max victory points
            
            findSingle(findCoppia(cop1).getUno()).setVictory(findSingle(findCoppia(cop1).getUno()).getVictory()+max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop1).getDue()).setVictory(findSingle(findCoppia(cop1).getDue()).getVictory()+max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getUno()).setVictory(findSingle(findCoppia(cop2).getUno()).getVictory()+20-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getDue()).setVictory(findSingle(findCoppia(cop2).getDue()).getVictory()+20-max);//coppia 1 prende max victory points
            
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            findCoppia(cop1).setVictPoints(findCoppia(cop1).getVictPoints()+20-max);//coppia 1 prende 20-max victory points
            findCoppia(cop2).setVictPoints(findCoppia(cop2).getVictPoints()+max);//coppia 2 prende max victory points
            
            findSingle(findCoppia(cop1).getUno()).setVictory(findSingle(findCoppia(cop1).getUno()).getVictory()+20-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop1).getDue()).setVictory(findSingle(findCoppia(cop1).getDue()).getVictory()+20-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getUno()).setVictory(findSingle(findCoppia(cop2).getUno()).getVictory()+max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getDue()).setVictory(findSingle(findCoppia(cop2).getDue()).getVictory()+max);//coppia 1 prende max victory points
            
        }
        
        findCoppia(cop1).setMastPoints(findCoppia(cop1).getMastPoints()+pun1); //assegno mastpoints1
        findCoppia(cop2).setMastPoints(findCoppia(cop2).getMastPoints()+pun2); //assegno mastpoints2
        
        findSingle(findCoppia(cop1).getUno()).setMaster(findSingle(findCoppia(cop1).getUno()).getMaster()+pun1);//coppia 1 prende max victory points
        findSingle(findCoppia(cop1).getDue()).setMaster(findSingle(findCoppia(cop1).getDue()).getMaster()+pun1);//coppia 1 prende max victory points
        findSingle(findCoppia(cop2).getUno()).setMaster(findSingle(findCoppia(cop2).getUno()).getMaster()+pun2);//coppia 1 prende max victory points
        findSingle(findCoppia(cop2).getDue()).setMaster(findSingle(findCoppia(cop2).getDue()).getMaster()+pun2);//coppia 1 prende max victory points

        
    }
    
    @Override
    public void annullaPunteggi(){
        int max=Tavolo.differenza(pun1, pun2); //calcolo massimo differenza
        
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            findCoppia(cop1).setVictPoints(findCoppia(cop1).getVictPoints()-max);//coppia 1 prende max victory points
            findCoppia(cop2).setVictPoints(findCoppia(cop2).getVictPoints()-(20-max)); //coppia 2 prende 20-max victory points
            
            findSingle(findCoppia(cop1).getUno()).setVictory(findSingle(findCoppia(cop1).getUno()).getVictory()-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop1).getDue()).setVictory(findSingle(findCoppia(cop1).getDue()).getVictory()-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getUno()).setVictory(findSingle(findCoppia(cop2).getUno()).getVictory()-(20-max));//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getDue()).setVictory(findSingle(findCoppia(cop2).getDue()).getVictory()-(20-max));//coppia 1 prende max victory points
            
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            findCoppia(cop1).setVictPoints(findCoppia(cop1).getVictPoints()-(20-max));//coppia 1 prende 20-max victory points
            findCoppia(cop2).setVictPoints(findCoppia(cop2).getVictPoints()-max);//coppia 2 prende max victory points
            
            findSingle(findCoppia(cop1).getUno()).setVictory(findSingle(findCoppia(cop1).getUno()).getVictory()-(20-max));//coppia 1 prende max victory points
            findSingle(findCoppia(cop1).getDue()).setVictory(findSingle(findCoppia(cop1).getDue()).getVictory()-(20-max));//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getUno()).setVictory(findSingle(findCoppia(cop2).getUno()).getVictory()-max);//coppia 1 prende max victory points
            findSingle(findCoppia(cop2).getDue()).setVictory(findSingle(findCoppia(cop2).getDue()).getVictory()-max);//coppia 1 prende max victory points
            
        }
        
        findCoppia(cop1).setMastPoints(findCoppia(cop1).getMastPoints()-pun1); //assegno mastpoints1
        findCoppia(cop2).setMastPoints(findCoppia(cop2).getMastPoints()-pun2); //assegno mastpoints2
        
        findSingle(findCoppia(cop1).getUno()).setMaster(findSingle(findCoppia(cop1).getUno()).getMaster()-pun1);//coppia 1 prende max victory points
        findSingle(findCoppia(cop1).getDue()).setMaster(findSingle(findCoppia(cop1).getDue()).getMaster()-pun1);//coppia 1 prende max victory points
        findSingle(findCoppia(cop2).getUno()).setMaster(findSingle(findCoppia(cop2).getUno()).getMaster()-pun2);//coppia 1 prende max victory points
        findSingle(findCoppia(cop2).getDue()).setMaster(findSingle(findCoppia(cop2).getDue()).getMaster()-pun2);//coppia 1 prende max victory points

        
        this.setPun1(0);
        this.setPun2(0);
    }
    

    public int getCop1() {
        return cop1;
    }

    public void setCop1(int cop1) {
        this.cop1 = cop1;
    }

    public int getCop2() {
        return cop2;
    }

    public void setCop2(int cop2) {
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
            return    "Coppia 1: " + this.findCoppia(this.getCop1()).getName1()
                    + " - " + this.findCoppia(this.getCop1()).getName2()
                    + " --> " + this.getPun1()
                    + "\nCoppia 2: " + this.findCoppia(this.getCop2()).getName1()
                    + " - " + this.findCoppia(this.getCop2()).getName2()
                    + " --> " + this.getPun2()
                    + "\n------------";
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

    @Override
    public String toStringCop1() {
        return findCoppia(cop1).toString();
    }

    @Override
    public String toStringCop2() {
        return findCoppia(cop2).toString();
    }
    
}
