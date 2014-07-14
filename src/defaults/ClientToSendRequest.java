/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import gui.MainGui;
import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giorgio
 */
public class ClientToSendRequest {
    String url;
    int port;
    
    public ClientToSendRequest(String url,int port){
        this.url=url;
        this.port=port;
    }
    
    
    public File send(File fileToSend){
        ArrayList<TorneoDatabase> temp_tornei=new ArrayList();
         try {
            Socket socket=new Socket(url,port);
            
            OutputStream os = socket.getOutputStream();

            System.out.println("Client:Sending Files...");
           
            DataOutputStream dataOut=new DataOutputStream(os);
            
            FileInputStream fileOut=new FileInputStream(fileToSend);
            
            int leng=fileOut.available();
            
            byte [] bytearray = new byte [leng];
            fileOut.read(bytearray, 0, leng);
            dataOut.write(bytearray, 0, leng);
            
            dataOut.flush();
            os.flush();
            
            
            System.out.println("Client:File Sent!"); 
            
            
            // INPUT
            
            InputStream is=socket.getInputStream();
                System.out.println("Client:ricevo file");
                DataInputStream dataIn=new DataInputStream(is);
                FileOutputStream fileIn=new FileOutputStream("temp.xml");
                
                leng=dataIn.available();
                byte[] aByte = new byte[leng];
                int n;
                while ((n=dataIn.read())!=-1){
                    System.out.println(n);
                    fileIn.write(n);
                }
                System.out.println(dataIn.read());
                
                //dataIn.read(aByte, 0, leng);
                //fileIn.write(aByte, 0, leng);
                fileIn.close();
            
            System.out.println("Client:File Received!"); 
            
            
            socket.close();
            
             
                
        }catch (IOException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             
            return new File("temp.xml");

             
        }
        
    }
    
    
    public static void main(String[] args){
         /*ClientMode socket=new ClientMode("127.0.0.1",8777);
         ArrayList<TorneoDatabase> tornei=socket.firstConnect("tornei.xml");
         System.out.println("client created");*/
    }
}