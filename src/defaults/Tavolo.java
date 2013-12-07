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
public class Tavolo {
    
    private int cop1,cop2; //coppie a questo tavolo
    private int pun1,pun2;
    private int id; //id del tavolo
    private ArrayList coppie;
    
    public Tavolo(int uno, int due , int id,int pun1,int pun2 , ArrayList coppie ){
        cop1=uno;
        cop2=due;
        this.id=id;
        this.pun1=pun1;
        this.pun2=pun2;
        this.coppie=coppie;
        
    }
    
    public Tavolo(int uno, int due , int id , ArrayList coppie){
        cop1=uno;
        cop2=due;
        this.id=id;
        this.coppie=coppie;
        pun1=pun2=0;
        
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
    
    
    
    
    
    public void assegnaPunteggi(int pun1 , int pun2){ //assegno punteggio alle due coppie
        
        this.setPun1(pun1);
        this.setPun2(pun2);
        
        int max=this.differenza(pun1, pun2); //calcolo massimo differenza
        if(pun1>=pun2){//se coppia 1 ha piu punti di coppia 2
            findCoppia(cop1).setVictPoints(max);//coppia 1 prende max victory points
            findCoppia(cop2).setVictPoints(20-max); //coppia 2 prende 20-max victory points
        }
        
        if(pun1<pun2){//se coppia 1 ha meno punti di coppia 2
            findCoppia(cop1).setVictPoints(20-max);//coppia 1 prende 20-max victory points
            findCoppia(cop2).setVictPoints(max);//coppia 2 prende max victory points
        }
        
        findCoppia(cop1).setMastPoints(pun1); //assegno mastpoints1
        findCoppia(cop2).setMastPoints(pun2); //assegno mastpoints2
        
    }
    
    public int differenza(int pun1 , int pun2){
        
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
    
}
