/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giorgio
 */
public class ConnectDatabase {
    
    private Connection database;
    private  String user,pass,ip,database_name;
    private  int port;
    private String jdbc;
    private Statement statement;
    private ResultSet result;
    private ArrayList<Single> singles;
    
    
    
     public ConnectDatabase(String username,String password,String ip,int port,String database_name)  {
        user=username;
        pass=password;
        this.ip=ip;
        this.port=port;
        this.database_name=database_name;
        
        try{
            
            System.out.println("invio richiesta");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            database=DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database_name+"?database?connectTimeout=60000&socketTimeout=60000&" + "user="+user+"&password="+pass);

            System.out.println("loggato");

            statement = database.createStatement();

        } catch (Exception ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ArrayList<Single> dumpSingoli(){
        ArrayList<Single> singoli=new ArrayList<>();
        try {
            String tempQuery;

            result = statement.executeQuery("select *,SUM(punti) as somma , COUNT(*) as numero_partite from punteggi  left outer join giocatori on giocatori.id=punteggi.id_giocatore group by punteggi.id_giocatore");
            while(result.next()){
                String nome=result.getString("nome");
                int id_database=Integer.valueOf(result.getString("id"));
                int media=Math.round(Integer.valueOf(result.getString("somma"))/Integer.valueOf(result.getString("numero_partite")));
                singoli.add(new Single(id_database,nome,media));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return singoli;
    }
    
    public String updateSingoli(ArrayList<Single> singoli,String nometorneo){
        String path=null;
        try{
            path=nometorneo.replaceAll(" ", "-").concat(".xml");
            File save=new File(path);
            
            result = statement.executeQuery("SELECT * FROM tornei WHERE nome='" + nometorneo +"';");
            int id_torneo=-1;
            while(result.next()) { 
                id_torneo=Integer.valueOf(result.getString("id")); 
                path=result.getString("name_file"); 
            }
            
            if(id_torneo==-1){
                statement.executeUpdate("INSERT INTO tornei(`nome` , `name_file`) VALUES('" + nometorneo +"','"+path+"');");
                result = statement.executeQuery("SELECT * FROM tornei WHERE nome='" + nometorneo +"';");
                while(result.next()) id_torneo=Integer.valueOf(result.getString("id")); 

            }
            
           
            for(Object temp:singoli){
                Single singolo=(Single) temp;
                if(singolo.getId_database()==-1){

                    statement.executeUpdate("INSERT INTO giocatori(nome) VALUES('"+singolo.getName()+"');");
                    ResultSet result2 = statement.executeQuery("SELECT * FROM giocatori WHERE nome='" + singolo.getName() +"';");
                    int id_giocatore=-1;
                    while(result2.next()) { id_giocatore=Integer.valueOf(result2.getString("id")); }
                    singolo.setId_database(id_giocatore);
                    
                    statement.executeUpdate("REPLACE INTO punteggi(id_torneo,id_giocatore,punti) VALUES('"+id_torneo+"','"+id_giocatore+"','"+singolo.getMaster()+"');");
                }else{
                    statement.executeUpdate("REPLACE INTO punteggi(id_torneo,id_giocatore,punti) VALUES('"+id_torneo+"','"+singolo.getId_database()+"','"+singolo.getMaster()+"');");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Updated");
        return path;

    }
       
    
    public ArrayList<TorneoDatabase> dumpTornei(){
        ArrayList<TorneoDatabase> tornei=new ArrayList();
        try {
            String tempQuery;

            result = statement.executeQuery("select * from tornei");
            while(result.next()){
                String nome=result.getString("nome");
                int id_database=Integer.valueOf(result.getString("id"));
                String name_file=result.getString("name_file");
                tornei.add(new TorneoDatabase(id_database,nome,name_file));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tornei;
    }
    
    
    
    /*public static void main (String Args[]) 
    {
        ConnectDatabase q=new ConnectDatabase("all", "aicon07");
        q.dumpSingoli();
    }*/
}
