package old;


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
public class CreationCoppie implements Runnable {
    
    protected ArrayList coppie;
    private int numcoppie;
    
    Scanner in = new Scanner(System.in); //Inizializzo lettore di linea

    public CreationCoppie(int num){
        this.numcoppie=num;
        coppie=new ArrayList(num);
    }
    
    @Override
    public void run(){
     int k=0;
     
     
     
    
       /* for(int i=0;i<this.numcoppie;i=i+1){
            
                
            
                System.out.println("\n----\nCoppia " + (i+1) + " - Inserire Nome Giocatore 1" );
                String one=in.nextLine();

                System.out.println("Coppia " + (i+1) + " - Inserire Nome Giocatore 2" );
                String two=in.nextLine();

                System.out.println("Coppia " + (i+1) + " - Inserire Tipologia" );
                Boolean type=Boolean.valueOf(in.nextLine());

                coppie.add(new Coppia(one,two , (i) , type)); //Creo la coppia
            
          
         
        }*/
        
        
}
    
    public ArrayList getCoppie(){
            return coppie;
    }
    
}
