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
public class ClientMode {
    String url;
    int port;
    
    public ClientMode(String url,int port){
        this.url=url;
        this.port=port;
    }
    
    
    public ArrayList<TorneoDatabase> firstConnect(String path){
        ArrayList<TorneoDatabase> temp_tornei=new ArrayList();
         try {
            Socket socket=new Socket(url,port);
            
            InputStream is=socket.getInputStream();
            
            byte[] aByte = new byte[1];
            int bytesRead;

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            fos = new FileOutputStream( path );
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(aByte, 0, aByte.length);

            do {
                    baos.write(aByte);
                    bytesRead = is.read(aByte);
            } while (bytesRead != -1);

            bos.write(baos.toByteArray());
            bos.flush();
            bos.close();

            socket.close();
            temp_tornei=XmlConnectionToServer.parseListTournament(new File(path));

        }catch (IOException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             return temp_tornei;
        }
        
    }
    
    
    public static void main(String[] args){
         ClientMode socket=new ClientMode("127.0.0.1",8777);
         ArrayList<TorneoDatabase> tornei=socket.firstConnect("tornei.xml");
         System.out.println("client created");
    }
}