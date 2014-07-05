/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import BurracoServer.TorneoDatabase;

/**
 *
 * @author Giorgio
 */
public class ConnectDatabase {
    
    Connection database;
    String user,pass,ip;
    int port;
    private String jdbc;
    Statement statement;
    ResultSet result;
    
    public ConnectDatabase(String username,String password,String ip,int port)  {
        user=username;
        pass=password;
        this.ip=ip;
        this.port=port;
        
        try{
            
            System.out.println("invio richiesta");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            database=DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/burraco?database?connectTimeout=60000&socketTimeout=60000&" + "user="+user+"&password="+pass);

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
    
    public void saveTorneo(String name,String name_file){
        
        String tempQuery="select * from `tornei` where `nome`=\""+name+"\"";
            
        try {
            int id=-1;
            System.out.println(tempQuery);
            result = statement.executeQuery(tempQuery);
            while(result.next()){
                id=Integer.valueOf(result.getString("id"));
            }
            
            if(id==-1) statement.executeUpdate("insert into `tornei` (`nome`,`name_file`) values (\""+name+"\",\""+name_file+".xml\")");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /*public static void main (String Args[]) 
    {
        ConnectDatabase q=new ConnectDatabase("all", "aicon07");
        q.dumpSingoli();
    }*/
}
