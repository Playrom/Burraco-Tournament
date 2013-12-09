package defaults;


import static defaults.Tavolo.checkCoppia;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class CancelPoints implements Runnable {
    
      Scanner in = new Scanner(System.in); //Inizializzo lettore di linea
      
      ArrayList turni,coppie;
      int numTurno;

    


    public CancelPoints(ArrayList turni, ArrayList coppie ,int numTurno){
        this.turni=turni;
        this.numTurno=numTurno;
        this.coppie=coppie;
    }


      @Override
      public void run(){
    //Operazione di calcolo del turno
        Turno temp=(Turno) turni.get(numTurno);
        try{
            temp.checkCalcolato(); //turno già calcolato?
            System.out.println("Errore Turno Non Calcolato");
            return;
        }catch(ErroreGiaCalcolato e){
            for(int i=0;i<temp.getTavoli().length;i++){//itero per numero di tavoli
            temp.getTavolo(i).annullaPunteggi();
            temp.setCalcolato(false);// ora indico che il turno è stato calcolato
        }
        
        }
        
        
        
}
      
        public Coppia findCoppia(int id){ //trovo coppia
        for(int i=0;i<coppie.size();i++){
            Coppia temp=(Coppia) coppie.get(i);
            try{
                checkCoppia(temp);
                if(temp.getId()==id){
                    return temp;
                }
            }catch(ErroreNonCoppia e){
                
            }
        }
        
        return null;
    }
        
        

}