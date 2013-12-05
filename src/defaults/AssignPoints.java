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
public class AssignPoints implements Runnable {
    
      Scanner in = new Scanner(System.in); //Inizializzo lettore di linea
      
      ArrayList turni,coppie;
      int numTurno;

    


    public AssignPoints(ArrayList turni, ArrayList coppie ,int numTurno){
        this.turni=turni;
        this.numTurno=numTurno;
        this.coppie=coppie;
    }


      @Override
      public void run(){
    //Operazione di calcolo del turno
        Turno temp=(Turno) turni.get(numTurno);
        for(int i=0;i<temp.getTavoli().length;i++){//itero per numero di tavoli
            System.out.println("Inserire punteggi Tavolo " + (i+1) + ":");
            System.out.println("Coppia 1 - " + findCoppia(temp.getTavolo(i).getCop1()).toString());
            int pun1=Integer.valueOf(in.nextLine());//inserisco punteggio coppia 1
            System.out.println("Coppia 2 - " + findCoppia(temp.getTavolo(i).getCop2()).toString() );
            int pun2=Integer.valueOf(in.nextLine());//inserisco punteggio coppia 2

           temp.getTavolo(i).assegnaPunteggi(pun1, pun2);

        }
        
        temp.setCalcolato(true);
        
        
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
                
            }
        }
        
        return null;
    }

}