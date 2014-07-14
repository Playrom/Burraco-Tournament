/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giorgio
 */
public class SendPath extends Thread{
    public void run(){
        File transferFile;
        System.out.println("Server-8778:avviato");
        try {
            ServerSocket serverSocket=new ServerSocket(8778);
            while(true){
                ConnectDatabase database=new ConnectDatabase("all","aicon07","193.183.99.188",3306);
                System.out.println("Server-8778:in attesa di connessioni");
                
                Socket socket=serverSocket.accept();
                System.out.println("Server-8778:connesso");
                
                InputStream is=socket.getInputStream();
                System.out.println("Server-8778:ricevo file");
                DataInputStream dataIn=new DataInputStream(is);
                FileOutputStream fileIn=new FileOutputStream("temp.xml");
                
                int leng=dataIn.available();
                byte[] aByte = new byte[leng];

                dataIn.read(aByte, 0, leng);
                fileIn.write(aByte, 0, leng);

                System.out.println("Server-8778:file scritto");
                


                File elaborate=new File("temp.xml");

                transferFile=SearchDatabase.parseSelectionTournament(elaborate);
                    
                                   
            OutputStream os = socket.getOutputStream();

           
            DataOutputStream dataOut=new DataOutputStream(os);
            
            FileInputStream fileOut=new FileInputStream(transferFile);
            
            leng=fileOut.available();
            System.out.println("Server-8778:Sending Files..."+leng );
            
            byte [] bytearray = new byte [leng];
            fileOut.read(bytearray, 0, leng);
            dataOut.write(bytearray, 0, leng);
            
            dataOut.flush();
            os.flush();
                socket.close();

                System.out.println("Server-8778:risorse rilasciate..."); 

                

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}