package defaults;


import java.io.IOException;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO!!!   NON COPPIE MA SINGOLI , VICTORY POINTS PER 4 MANI , caricare impostazioni da file xml,
/**
 *
 * @author Giorgio
 */
public class MainClass  implements Runnable{

    
    private Torneo torneo;
    Scanner in = new Scanner(System.in); //Inizializzo lettore di linea
    private ArrayList coppie,turni=null;
    private SingleList singles;
    private Object[] all;
    private int numTurni=0,smazzate=0;
    private String nomeTorneo;
    private boolean loaded,started,alone;//se 0 vuol dire che sto creando i turni, se 1 li sto caricando da xml


  
    
    public MainClass(){
        Torneo torneo=null;
        
    }
    
    public void loadXml(String filename){
        XmlParser parser=new XmlParser(filename);
        parser.run();
        all=parser.load();
        coppie=(ArrayList) all[0];
        torneo=(Torneo) all[1];
        singles=(SingleList) (ArrayList) all[2];
        loaded=true;
        alone=torneo.isAlone();
        started=torneo.isStarted();
        numTurni=torneo.getNumTurni();
        nomeTorneo=torneo.getNome();
    }
    
    
    public void writeXml(String filename){
        XmlWriter parser=new XmlWriter(filename,coppie,singles,torneo);
        parser.run();
    }
            
    
    public void saveCouplesPreTournament(String filename){
        
        if(alone){
            XmlWriter write=new XmlWriter(filename,coppie,singles,torneo.getNome(),torneo.getNumTurni(),alone);
            write.run();
        }else{
            XmlWriter write=new XmlWriter(filename,coppie,singles,torneo.getNome(),torneo.getNumTurni(),alone);
            write.run();
        }
        

    }
    
    public void startTournamentAndCreate(){
        if(alone){
            boolean temp=false;
            if(loaded) temp=true; 
            torneo=new Torneo(singles,numTurni,temp,nomeTorneo);
           
        }else{
            boolean temp=false;
            if(loaded) temp=true; 
            torneo=new Torneo(coppie,singles,numTurni,temp,nomeTorneo);
        }
    }
    
    public void startTournament(){
        torneo.creaTurni();
    }
    
    public void addSingle(String nome){
        singles.add(new Single(singles.size(),nome,torneo.isAlone()));
    }
    
    public void addCoppia(String uno,String due,boolean tipo){
        singles.add(new Single(singles.size(),uno,torneo.isAlone()));
        singles.add(new Single(singles.size(),due,torneo.isAlone()));
        
        coppie.add(new Coppia( singles.size()-1,singles.size(),coppie.size(),tipo,singles));
        
    }
    
    public ArrayList getCoppie(){
        return coppie;
    }
    
    public Torneo getTorneo(){
        return torneo;
    }
    
    @Override
    public  void run() {
        
     
    
          
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isStarted() {
        return torneo.isStarted();
    }

    public void setStarted(boolean started) {
        this.torneo.setStarted(started);
    }
    
    public int getNumTurni(){
        return this.torneo.getNumTurni();
    }
    
    public String getNomeTorneo(){
        return this.torneo.getNome();
    }
    
    public void setNomeTorneo(String nome){
        torneo.setNome(nome);
    }
    
    public void setNumTurni(int t){
        this.torneo.setNumTurni(t);
    }

    public ArrayList getSingles() {
        return singles;
    }

    public void setSingles(SingleList singles) {
        this.singles = singles;
    }
    
    public void initSingles(){
        singles=new SingleList();
    }
     
    public void initCoppie(){
        coppie=new ArrayList();
    }

    public boolean isAlone() {
        return alone;
    }

    public void setAlone(boolean alone) {
        this.alone = alone;
    }
    
    public void setStart(String nome,boolean started,boolean alone,int numTurni,int smazzate) {
        this.started=started;
        this.alone=alone;
        this.numTurni=numTurni;
        this.smazzate=smazzate;
        this.nomeTorneo=nome;
    }
    
    
    
    
    


    
}
