/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BurracoServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
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
                Socket socket=serverSocket.accept();
               InputStream is=socket.getInputStream();
            
                byte[] aByte = new byte[1];
                int bytesRead;

                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                fos = new FileOutputStream( "temp.xml" );
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do {
                        baos.write(aByte);
                        bytesRead = is.read(aByte);
                } while (bytesRead != -1);

                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();

                File elaborate=new File("temp.xml");

                transferFile=SearchDatabase.parseSelectionTournament(elaborate);
                    
                String ip_client=socket.getInetAddress().getHostAddress();
                socket.close();
                
                Socket sendSocket=new Socket(ip_client,8779);
                
                                   
                OutputStream os = sendSocket.getOutputStream();

                System.out.println("Server-8778:Sending File Richiesto..."); 

                byte [] bytearray = new byte [(int)transferFile.length()];
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(transferFile));
                bis.read(bytearray, 0, bytearray.length);

                os.write(bytearray,0,bytearray.length);
                os.flush();
                System.out.println("Server-8778:File Sent!"); 
                
                sendSocket.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}