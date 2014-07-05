/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;
import java.io.*;
/**
 *
 * @author Giorgio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File transferFile;
        SearchDatabase searchSockets=new SearchDatabase();
        SendPath pathSockets=new SendPath();
        SaveData save=new SaveData();
        save.start();
        searchSockets.start();
        pathSockets.start();
        int x=0;
        
    }
    
    
    
    
    
    
    
}
