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
public class CreationTablesCoppie extends CreationTables implements Runnable{
    private Urna fisse,mobili;
    private ArrayList coppie,singles;
    
    public CreationTablesCoppie(ArrayList coppie,int numTurni,int smazzate){
        super(numTurni,smazzate);
        this.coppie=coppie;
        this.fisse=new Urna(coppie.size()/2);
        this.mobili=new Urna(coppie.size()/2);
        this.fisse.createCasualArray();
        this.mobili.createCasualArray();
    }
    
    @Override
    public void run(){
        turni.add(new Turno(coppie,0,false,smazzate));
        for(int i=1;i<numTurni;i++) turni.add(new Turno(coppie ,creaTavoliTurno(i),i,false,smazzate));
        
    }
    
    
    @Override
    public Tavolo[] creaTavoliTurno(int i){
            Turno turnoPrec=(Turno) turni.get(i-1);
            Tavolo tavArr[]=new Tavolo[turnoPrec.getTavoli().length];
            for(int k=0;k<tavArr.length;k++){
                TavoloCoppie tempTav=(TavoloCoppie)turnoPrec.getTavolo(k);
                if(k==0){
                    TavoloCoppie mob=(TavoloCoppie)turnoPrec.getTavolo(tavArr.length-1);
                    tavArr[k]=new TavoloCoppie(tempTav.getCop1(),mob.getCop2(),k,smazzate);
                }else{
                    TavoloCoppie mob=(TavoloCoppie)turnoPrec.getTavolo(k-1);
                    tavArr[k]=new TavoloCoppie(tempTav.getCop1(),mob.getCop2(),k,smazzate);
                }
            }
            
        return tavArr;
    }

}
