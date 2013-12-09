package defaults;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giorgio
 */
public class Coppia implements Comparable{
    
    private String nome1; //Nome primo giocatore
    private String nome2; //Nome secondo giocatore
    private int id; //ID della coppia
    private boolean tipo; //0 Coppia Fissa - 1 Coppia Mobile
    protected int victory; //Victory Points della coppia
    protected int master; //Somma dei punti delle partite totali
    
    public Coppia(String nome1 , String nome2 , int id , boolean tipo ){
        this.nome1=nome1;
        this.nome2=nome2;
        this.id=id;
        this.tipo=tipo;
        victory=0;
        master=0;
    }
    
    
    
 
    
    public Coppia(String nome1 , String nome2 , int id , boolean tipo , int master,int victory){
        this(nome1,nome2,id,tipo);
        this.master=master;
        this.victory=victory;
    }
    
    
    public String getName1 (){
        return nome1;
    }
   
    public String getName2 (){
        return nome2;
    }
    
   
    
    public boolean getType(){
        if(this instanceof Coppia){
            return tipo;
        } else {
            return false;
        }
    }
    
        
    public int getVictPoints(){
        return victory;
    }
    
    public int getMastPoints(){
        return master;
    }
    
    public void setName1(String name ){
        nome1=nome1.replaceAll(nome1, name);
    }
    
    public void setName2(String name ){
        nome2=nome2.replaceAll(nome2, name);
    }
    
    public void setType(boolean type){
        tipo=type;
    }
    
    public void setVictPoints(int punti){
        victory=punti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    public void setMastPoints(int punti){
        master=punti;
    }

    @Override
    public String toString() {
        return   nome1 + " - " + nome2;
    }
    
    public String toAllString(){
        return "|\t" + nome1 + "\t\t\t" + nome2 +"\t\t\t"+victory+"\t\t\t" + master;
    }
    
    
    
    
    public int compareTo(Coppia cop){
        int confront=0;
        
        if(cop==null) return -2;
        
        if(this.getVictPoints()==cop.getVictPoints()){ //Se i punti Victori delle coppie sono uguali
            if(this.getMastPoints()==cop.getMastPoints()) confront = 0; //Se Punti partite totali sono uguali allora coppie a pari merito
            else if(this.getMastPoints()>cop.getMastPoints()) confront =-1; //Se no Coppia 1 è piu alta in classifica
            else if(this.getMastPoints()<cop.getMastPoints()) confront = 1; //Se no Coppia 1 è piu bassa in classifica
        }
        
        else if(this.getVictPoints()>cop.getVictPoints()) confront = -11; //Se victory points coppia 1 sono maggiori allora piu alta in classifica
        else if(this.getVictPoints()<cop.getVictPoints()) confront =1; //Se victory point coppia 1 sono minori allora piu bassa in classifica
        
        return confront;
    }
    
    @Override
    public int compareTo(Object o){
        if(o!=null && o instanceof Coppia  ){ //override della comparazione
            return compareTo((Coppia) o);
            }
        return -2;
    }
    
    
}
