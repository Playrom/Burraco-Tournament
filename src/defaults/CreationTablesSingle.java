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
public class CreationTablesSingle extends CreationTables {
    private Urna fisse,mobili;
    private ArrayList singles;
    
    public CreationTablesSingle(ArrayList singles,int numTurni){
        super(numTurni);
        this.singles=singles;
    }
    
    @Override
    public void run(){
        turni.add(new Turno(singles,0,true));
        for(int i=1;i<numTurni;i++) turni.add(new Turno(singles , creaTavoliTurno(i),i,true));
        
    }
    
    
    @Override
    public Tavolo[] creaTavoliTurno(int i){
            Turno turnoPrec=(Turno) turni.get(i-1);
            Tavolo tavArr[]=new Tavolo[turnoPrec.getTavoli().length];
            
            for(int k=0;k<tavArr.length;k++){
                TavoloSingoli tempTav=(TavoloSingoli)turnoPrec.getTavolo(k);
                if(k==0){
                    
                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);

                    
                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,singles);
                }else{
                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);

                    
                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,singles);
                }
            }
            
        return tavArr;
    }

}
