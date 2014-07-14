package defaults;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author playrom
 */

import java.util.Random;

public class Urna  {
    
    
    private int estrattiInOrdine[];//numero degli estratti in ordine
    private int next;
    
    
    public Urna(int num){
         
        estrattiInOrdine = new int[num];//array estratti in oridine viene inizzializzato con numero passato
        
        for(int i=0;i<estrattiInOrdine.length;i++) estrattiInOrdine[i]=-1;//setto tutti gli id a 0
        
        next=0;
        
    }
    
    
    public void createCasualArray(){
        
         //creazione array casuale con tripla randomizzazione
        
        
        
        for(int i=0;i<estrattiInOrdine.length;i++){
                    checkEstratto(i , estrattiInOrdine.length);
        }
        
        
        
    }
    
    
    public boolean checkEstratto( int indice , int max){
        
        boolean diRitorno=true;//variabile di controllo
        
        //creazione array casuale con tripla randomizzazione
        Random r=new Random( (int) ((Math.random())*100000));
        int estratto;
        
        do{
        
            estratto = r.nextInt(estrattiInOrdine.length); //estraggo un numero
        
        for(int i=0;i<estrattiInOrdine.length;i++){ //iterazione per controllo se il numero è gia stato estratto
            
             System.out.print(estratto + "-> "  + "estratto ---> ");//numero estratto

            if( estratto == estrattiInOrdine[i] ){ //numero gia nell'array, break ciclo e passo avanti        
                System.out.print(estratto + " in confronto a -> " + estrattiInOrdine[i] + " - uguale \nESTRAGGO ALTRO NUMERO\n\n");
                        diRitorno=true;//setto come numero gia presente
                        break;

            } else {
            
                System.out.print(estratto + " in confronto a -> " + estrattiInOrdine[i] + " - diverso \n");
            
                diRitorno=false;//setto come numero assente

        }
            
        

        }
        
        }while(diRitorno);
        
            System.out.print(estratto + " Inserito nel posto->" + indice + "\n");
            estrattiInOrdine[indice]=estratto; //inserisco nel posto indice
            return diRitorno;


        
    }
    
    
    public void printEstratti(){
        for(int i=0;i<estrattiInOrdine.length;i++) System.out.println(estrattiInOrdine[i]);
    }
    
    public int numEstratti(){
        return this.estrattiInOrdine.length;
    }
    
    public int getNum(int indice){
        return estrattiInOrdine[indice];
    }
    
    public void setNext(int num){
        this.next=+num;
    }
    
    public int getNext(){
        return this.next;
    }
    
    public int movingNext(){
        int ritornare=this.getNum(next);
        this.setNext(this.getNext()+1);
        return ritornare;
    }
    
    public int getIndex(int numero){
        for(int i=0;i<estrattiInOrdine.length;i++){
            if(estrattiInOrdine[i]==numero) return i;
               
        }
         return 0;
    }
  
    
    
    
    
}
