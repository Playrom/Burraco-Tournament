/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giorgio
 */
public class ConnectDatabase {
    
    Connection database;
    String user,pass;
    private String jdbc;
    Statement statement;
    ResultSet result;
    SingleList singles;
    
    public ConnectDatabase(String username,String password)  {
        user=username;
        pass=password;
        
        try{
            
            System.out.println("invio richiesta");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            database=DriverManager.getConnection("jdbc:mysql://193.183.99.188:3306/burraco?database?connectTimeout=60000&socketTimeout=60000&" + "user="+user+"&password="+pass);

            System.out.println("loggato");

            statement = database.createStatement();

        } catch(SQLException e){
            
        } catch (InstantiationException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public SingleList dumpSingoli(){
        SingleList singoli=new SingleList();
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
    
    public void updateSingoli(SingleList singoli,String nometorneo){
        try{
            statement.executeUpdate("INSERT INTO tornei(`nome`) VALUES('" + nometorneo +"');");
            result = statement.executeQuery("SELECT * FROM tornei WHERE nome='" + nometorneo +"';");
            int id_torneo=-1;
            while(result.next()) { id_torneo=Integer.valueOf(result.getString("id")); }
            
           
            for(Object temp:singoli){
                Single singolo=(Single) temp;
                if(singolo.getId_database()==-1){
                    statement.executeUpdate("INSERT INTO giocatori(nome) VALUES('"+singolo.getName()+"');");
                    ResultSet result2 = statement.executeQuery("SELECT * FROM giocatori WHERE nome='" + singolo.getName() +"';");
                    int id_giocatore=-1;
                    while(result2.next()) { id_giocatore=Integer.valueOf(result2.getString("id")); }
                    singolo.setId_database(id_giocatore);
                    
                    statement.executeUpdate("INSERT INTO punteggi(id_torneo,id_giocatore,punti) VALUES('"+id_torneo+"','"+id_giocatore+"','"+singolo.getMaster()+"');");
                }else{
                    statement.executeUpdate("INSERT INTO punteggi(id_torneo,id_giocatore,punti) VALUES('"+id_torneo+"','"+singolo.getId_database()+"','"+singolo.getMaster()+"');");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Updated");
    }
       
    
    
    /*public static void main (String Args[]) 
    {
        ConnectDatabase q=new ConnectDatabase("all", "aicon07");
        q.dumpSingoli();
    }*/
}
