package defaults;


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
public class CreationTablesSingle extends CreationTables {
    private Urna fisse,mobili;
    private ArrayList singles;
    
    public CreationTablesSingle(ArrayList singles,int numTurni,int smazzate){
        super(numTurni,smazzate);
        this.singles=singles;
    }
    
    @Override
    public void run(){
        turni.add(new Turno(singles,0,true,smazzate));
        for(int i=1;i<numTurni;i++) turni.add(new Turno(singles , singleTurno(i),i,true,smazzate));
        
    }
    
    
    @Override
    public Tavolo[] creaTavoliTurno(int i){
            Turno turnoPrec=(Turno) turni.get(i-1);
            Tavolo tavArr[]=new Tavolo[turnoPrec.getTavoli().length];
            
            for(int k=0;k<tavArr.length;k++){
                TavoloSingoli tempTav=(TavoloSingoli)turnoPrec.getTavolo(k);
                if(k==0){
                    
                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-2)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-3)%tavArr.length);

                    
                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,smazzate);
                    
                }else if(k==1){
                    
                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-2)%tavArr.length);


                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,smazzate);

                }else if(k==2){

                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((k-2)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((tavArr.length-1)%tavArr.length);


                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,smazzate);

                
                 
                    
                }else{
                    TavoloSingoli prec1=(TavoloSingoli)turnoPrec.getTavolo((k-1)%tavArr.length);
                    TavoloSingoli prec2=(TavoloSingoli)turnoPrec.getTavolo((k-2)%tavArr.length);
                    TavoloSingoli prec3=(TavoloSingoli)turnoPrec.getTavolo((k-3)%tavArr.length);

                    
                    tavArr[k]=new TavoloSingoli(tempTav.getPla1(),prec1.getPla2(), prec2.getPla3() , prec3.getPla4(),k,smazzate);
                }
            }
            
        return tavArr;
    }

    
    public Tavolo[] singleTurno(int n ){
        
        Turno turnoPrec=(Turno) turni.get(n-1);
        Tavolo tavoli[]=new Tavolo[(singles.size())/4]; //esistono tavoli per meta delle coppie
        
            
        
        
        int numTavoli=tavoli.length;
        
        Urna tempCoppie=new Urna(singles.size());
        tempCoppie.createCasualArray();


         for(int i=0;i<tavoli.length;i++){
             Single toad[]=new Single[4];
             System.out.println(i);
             int tempLocationUrna=tempCoppie.getNext();
             
             while(true){
             
                    tempCoppie.setNext(tempLocationUrna);
                    
                    for(int k=0;k<toad.length;k++){
                        toad[k]=(Single) singles.get(tempCoppie.movingNext());
                    }

                    Tavolo tavTemp=new TavoloSingoli(toad[0], toad[1], toad[2], toad[3] , i , smazzate);// costruisco tavolo cercando la coppia fissa e mobile per ogni tavolo, e assegno l'id al tavolo
                    
                    if(!turnoPrec.getTavolo(i).equals(tavTemp)){
                       tavoli[i]=new TavoloSingoli(toad[0], toad[1], toad[2], toad[3] , i ,smazzate);// costruisco tavolo cercando la coppia fissa e mobile per ogni tavolo, e assegno l'id al tavolo
                       break;
                    }
                
             }

         }
         
         return tavoli;
     }
    
    
}
