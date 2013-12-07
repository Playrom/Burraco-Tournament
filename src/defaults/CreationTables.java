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
public class CreationTables implements Runnable{
    private Urna fisse,mobili;
    private ArrayList coppie,turni;
    int numTurni;
    
    public CreationTables(ArrayList coppie,int numTurni){
        this.coppie=coppie;
        this.fisse=new Urna(coppie.size()/2);
        this.mobili=new Urna(coppie.size()/2);
        this.fisse.createCasualArray();
        this.mobili.createCasualArray();
        this.numTurni=numTurni;
        turni=new ArrayList(numTurni);
    }
    
    @Override
    public void run(){
        turni.add(new Turno(coppie,0));
        for(int i=1;i<turni.size();i++) turni.add(new Turno(coppie , creaTavoliTurno(i),i));
        
    }
    
    public ArrayList getTurni(){
        return turni;
    }
    
    public Tavolo[] creaTavoliTurno(int i){
            Turno turnoPrec=(Turno) turni.get(i-1);
            Tavolo tavArr[]=new Tavolo[turnoPrec.getTavoli().length];
            for(int k=0;k<tavArr.length;k++){
                Tavolo tempTav=turnoPrec.getTavolo(k);
                if(k==0){
                    tavArr[k]=new Tavolo(turnoPrec.getTavolo(k).getCop1(),turnoPrec.getTavolo(tavArr.length-1).getCop2(),k,coppie);
                }else{
                    tavArr[k]=new Tavolo(turnoPrec.getTavolo(k).getCop1(),turnoPrec.getTavolo(k-1).getCop2(),k,coppie);
                }
            }
            
            
            
            
            
        
        
        return tavArr;
    }

}
