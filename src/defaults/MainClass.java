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
    private int numTurni=0;
    private String nomeTorneo;
    private boolean loaded,started,alone;//se 0 vuol dire che sto creando i turni, se 1 li sto caricando da xml


  
    
    public MainClass(){
        Torneo torneo=null;
        alone=false;
    }
    
    public void loadXml(String filename){
        XmlParser parser=new XmlParser(filename);
        parser.run();
        all=parser.load();
        coppie=(ArrayList) all[0];
        torneo=(Torneo) all[1];
        singles=(SingleList) (ArrayList) all[2];
        loaded=true;
        started=torneo.isStarted();
        numTurni=torneo.getNumTurni();
        nomeTorneo=torneo.getNome();
    }
    
    
    public void writeXml(String filename){
        XmlWriter parser=new XmlWriter(filename,coppie,singles,torneo);
        parser.run();
    }
            
    /*public void createTournamentZero(String nomeTorneo,int numTurni,int numCoppie){
        CreationCoppie th1=new CreationCoppie(numCoppie);
        th1.run();

        data=th1.getCoppie();
        loaded=false;
        started=false;
    }
    
    public void addCouplesPreTournament(int numCoppie){
        CreationCoppie th1=new CreationCoppie(numCoppie);
        th1.run();

        ArrayList newCoppie=th1.getCoppie();
        for (Object x : newCoppie){
            if (!coppie.contains(x)){
            Coppia t=(Coppia) x;
            t.setId(coppie.size());
            coppie.add(x);
            }

        }
    }*/
    
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
            torneo=new Torneo(singles,numTurni,true,nomeTorneo);
        }else{
            torneo=new Torneo(coppie,singles,numTurni,true,nomeTorneo);
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
        
       
      /*  
       
            
        
                
                if(started){
                    
                    
                } else {
                    
                    if(numTurni==0){
                        System.out.println("Quanti turni? " );
                        numTurni=Integer.valueOf(in.nextLine());                    
                    }
                    
                    while(true){
                        System.out.println("Digita 1 per stampare le coppie" );
                        System.out.println("Digita 2 per aggiungere coppie" );
                        System.out.println("Digita 3 per startare torneo" );
                        System.out.println("Digita 4 per salvare torneo e non startare" );
                        int temp=Integer.valueOf(in.nextLine());

                        if( temp == 1){
                            for(int e=0;e<coppie.size();e++){
                                Coppia tcop = (Coppia) coppie.get(e);
                                System.out.println(tcop.toAllString());
                            }
                        }

                        if( temp == 2){
                           System.out.println("Quante coppie vuoi aggiungere? " );
                           int numCoppie=Integer.valueOf(in.nextLine());
                           
                        }
                        
                        if( temp == 3){
                            break;
                        }
                        if( temp == 4){
                            
                            System.exit(0);
                        }
                        
                    }
                  
                }
              
            
            
            
        
        while(true){
        
        
       
        



            System.out.println("Digita 1 per ottenere la classifica" );
            System.out.println("Digita 2 per stampare il torneo" );
            System.out.println("Digita 3 per aggiungere punti turno" );
            System.out.println("Digita 4 per stampare status coppie" );  
            System.out.println("Digita 5 per scrivere su xml" ); 
            System.out.println("Digita 6 per annullare calcolo turno" );
            int temp=Integer.valueOf(in.nextLine());
            if( temp == 1){
                torneo.displayClassifica();
            }
            
            if(temp == 2){
                torneo.displayTorneo();
            }
            
            if(temp == 3){
                
                System.out.println("Digita numero turno da calcolare (numeri reali, non id)" );
                temp=Integer.valueOf(in.nextLine());
                torneo.calcolaTurno(temp-1);
            }
            
            if(temp == 4){
                torneo.displayStatus();
            }
            
            if(temp == 5){
                XmlWriter write=new XmlWriter(filename2,coppie,torneo);
                write.run();
            }
            
            if(temp == 6){
                
                System.out.println("Digita numero turno da annullare (numeri reali, non id)" );
                temp=Integer.valueOf(in.nextLine());
                torneo.annullaTurno(temp-1);
            }

            //Arrays.sort((Object[])torneo.coppie);
    
        }*/
    
          
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
    
    
    
    


    
}
