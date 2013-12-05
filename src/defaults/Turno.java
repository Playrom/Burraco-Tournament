package defaults;


import java.io.IOException;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class Turno {
    
     Scanner in = new Scanner(System.in); //Inizializzo lettore di linea
    
    Tavolo tavoli[]; //tutti i tavoli a questo turno
    ArrayList copfisse,copmobili,all;//elenco tutte le coppie fisse e mobili.
    Urna fisse,mobili;
    Boolean calcolato;
    
    public Turno(ArrayList data ){
        
                
        copfisse=new ArrayList(data.size()/2); //meta coppie sono fisse 
        copmobili=new ArrayList(data.size()/2); //meta coppie sono mobili
        this.tavoli=new Tavolo[(data.size())/2]; //esistono tavoli per meta delle coppie
        
        this.fisse=new Urna(data.size()/2);
        this.mobili=new Urna(data.size()/2);
        this.fisse.createCasualArray();
        this.mobili.createCasualArray();
        this.calcolato=false;
        all=data;
       
        int k=0,j=0;
        for(int i=0 ; i<data.size() ; i++){
             try{
                Coppia temp=(Coppia) data.get(i);
                
                if(temp.getType() && checkCoppia(temp)){ //se data[i] coppia mobile , 1 indica coppia mobile
                    copmobili.add(temp); //inserisco nell'array di coppie mobili
                    k++;
                } else {
                    copfisse.add(temp);//senno inserisco in array coppie fisse
                    j++;
                }
            
             }catch (ErroreNonCoppia e){
                 System.out.println("Errore Non Coppia");
                       
             }
            
            
        }
       
        
        
        for(int i=0;i<tavoli.length;i++){
            
            System.out.println(i);
            try{
                Coppia tempfisse=(Coppia) copfisse.get(fisse.movingNext());
                Coppia tempmobili=(Coppia) copmobili.get(mobili.movingNext());
                if(checkCoppia(tempfisse));
                if(checkCoppia(tempmobili));
                tavoli[i]=new Tavolo(tempfisse.getId() , tempmobili.getId() , i ,all);/*
                 * costruisco tavolo cercando la coppia fissa e mobile per ogni tavolo, e assegno l'id al tavolo
                 */
            }catch (ErroreNonCoppia e){
                System.out.println("Errore Non Coppia");
            }
            
        }
        
        
    }
    
    
    
    
    
    public Turno(ArrayList data , Tavolo[] tavoli){
        
                
        copfisse=new ArrayList(data.size()/2); //meta coppie sono fisse 
        copmobili=new ArrayList(data.size()/2); //meta coppie sono mobili
        this.tavoli=tavoli; //esistono tavoli per meta delle coppie
        this.calcolato=false;
        all=data;
        
        
       
        int k=0,j=0;
        for(int i=0 ; i<data.size() ; i++){
             try{
                Coppia temp=(Coppia) data.get(i);
                
                if(temp.getType() && checkCoppia(temp)){ //se data[i] coppia mobile , 1 indica coppia mobile
                    copmobili.add(temp); //inserisco nell'array di coppie mobili
                    k++;
                } else {
                    copfisse.add(temp);//senno inserisco in array coppie fisse
                    j++;
                }
            
             }catch (ErroreNonCoppia e){
                 System.out.println("Errore Non Coppia");
                       
             }
            
            
        }
       
        
        
        
        
        
    }
    
    
    
    
    /*public void calcolaTurno() throws IOException {
        //Operazione di calcolo del turno
        for(int i=0;i<tavoli.length;i++){//itero per numero di tavoli
            System.out.print("Inserire punteggi Tavolo " + (i+1) + ":");
                    System.out.println("Coppia 1 " );
                    int pun1=Integer.valueOf(in.nextLine());//inserisco punteggio coppia 1
                    System.out.println("Coppia 2 " );
                    int pun2=Integer.valueOf(in.nextLine());//inserisco punteggio coppia 2
                    
                    tavoli[i].assegnaPunteggi(pun1, pun2); //asegno i punteggi


        }
        
        this.calcolato=true;
        
        
        
    }*/
    
    
    
    
    public void displayTables(){
        
        for(int n=0;n<tavoli.length;n++){
            System.out.println("Tavolo " + tavoli[n].getId()  + " - " + tavoli.length + " tavoli in totale");
            System.out.println("Nome Giocatore 1 - Coppia 1 " + this.tavoli[n].findCoppia(this.tavoli[n].getCop1()).getName1());
            System.out.println("Nome Giocatore 2 - Coppia 1 " + this.tavoli[n].findCoppia(this.tavoli[n].getCop1()).getName2());
            System.out.println("Nome Giocatore 1 - Coppia 2 " + this.tavoli[n].findCoppia(this.tavoli[n].getCop2()).getName1());
            System.out.println("Nome Giocatore 2 - Coppia 2 " + this.tavoli[n].findCoppia(this.tavoli[n].getCop2()).getName2());
            System.out.println("------------");
            
        }
    }

    public Tavolo[] getTavoli() {
        return tavoli;
    }

    public void setTavoli(Tavolo[] tavoli) {
        this.tavoli = tavoli;
    }
    
    public Tavolo getTavolo(int id) {
        return tavoli[id];
    }

    public Boolean getCalcolato() {
        return calcolato;
    }

    public void setCalcolato(Boolean calcolato) {
        this.calcolato = calcolato;
    }
    
    public String isCalcolato(){
        if(calcolato) return " Calcolato ";
        else return " Da Calcolare ";
    }
    
    

    
    public static boolean checkCoppia (Object e) throws ErroreNonCoppia{
        if((e instanceof Coppia)){
            return true;
        } else { throw new ErroreNonCoppia();
    }
    }
    
}
