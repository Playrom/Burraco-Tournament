/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author playrom
 */
public class SaveToServerMode {
    
    String url;
    int port;
    
    public SaveToServerMode(String url,int port){
        this.url=url;
        this.port=port;
    }
    
    public void save(File file){
            Socket socket = null;
        try {
            socket=new Socket(url,port);
            OutputStream os = socket.getOutputStream();

            System.out.println("Client:Sending Files..."); 

            byte [] bytearray = new byte [(int)file.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            bis.read(bytearray, 0, bytearray.length);

            os.write(bytearray,0,bytearray.length);
            os.flush();
            System.out.println("Client:File Sent!"); 
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveToServerMode.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
