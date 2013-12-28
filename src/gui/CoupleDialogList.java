/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import defaults.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Giorgio
 */
public class CoupleDialogList extends JDialog {
   
    ArrayList coppie,ordinate;
    JButton arrays[];
    int init;
    CoupleDialogEdit editDialog;
    boolean classifica;
            
            
    public CoupleDialogList(ArrayList coppie,boolean classifica){
        this.coppie=coppie;
        this.classifica=classifica;
        
        
       this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
       setLayout(new MigLayout("fillx"));
       int i=0;
       JLabel names,ids,masters,victorys,types;
       names=new JLabel("Giocatori");
       if(!classifica)ids=new JLabel("ID"); else ids=new JLabel("Pos");
       masters=new JLabel("MP");
       victorys=new JLabel("VP");
       types=new JLabel("Tipo");
       
       this.add(ids);
       this.add(types);
       this.add(names);
       this.add(masters);
       this.add(victorys,"wrap");
       
       if(classifica){
           ordinate=new ArrayList(coppie);
           Collections.sort(ordinate);
           
            for (Object temp1 : ordinate) {
           
                Coppia temp=(Coppia) temp1;
                JLabel label=new JLabel(temp.toString());
                JLabel id=new JLabel(String.valueOf(i+1));
                JLabel master=new JLabel(String.valueOf(temp.getMastPoints()));
                JLabel victory=new JLabel(String.valueOf(temp.getVictPoints()));
                boolean tipo=temp.getType();
                JLabel type;
                if(tipo==true) { type=new JLabel("Mob"); } else { type=new JLabel("Fis"); }



                this.add(id);
                this.add(type);
                this.add(label);
                this.add(victory);
                this.add(master,"wrap");

                i++;
            }
           
           
           
       } else {
       
            for (Object temp1 : coppie) {

                Coppia temp=(Coppia) temp1;
                JLabel label=new JLabel(temp.toString());
                JLabel id=new JLabel(String.valueOf(temp.getId()));
                JLabel master=new JLabel(String.valueOf(temp.getMastPoints()));
                JLabel victory=new JLabel(String.valueOf(temp.getVictPoints()));
                boolean tipo=temp.getType();
                JLabel type;
                if(tipo==true) { type=new JLabel("Mob"); } else { type=new JLabel("Fis"); }
                JButton edit=new JButton("Modifica " + temp.getId());



                this.add(id);
                this.add(type);
                this.add(label);
                this.add(victory);
                this.add(master);
                this.add(edit, "wrap");

                final CoupleDialogList questo=this;
                final int tempF=temp.getId();

                final ArrayList tempC=coppie;

                final int intID=i;

                ActionListener listener=new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                       JButton text=(JButton) e.getSource();
                       String tempString=text.getText();
                       tempString=tempString.replaceAll("Modifica ", "");

                        closeAndReload(Integer.valueOf(tempString));
                    }
                };

                edit.addActionListener(listener);






                i++;
               }

       }
       
       ActionListener terminaListener=new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    end();
                }
            };
       
       JButton termina=new JButton("Termina");
       termina.addActionListener(terminaListener);
       this.add(termina);
       
       this.setResizable(false);
       
       this.pack();
       
       this.setVisible(true);
        
    }
    
    
    public void closeAndReload(int id){
        editDialog=new CoupleDialogEdit(coppie,id);
        editDialog.setVisible(true);
        this.setVisible(false);
    }
    
    public void end(){
        this.dispose();
    }
    
    
    
    
    
    
    
    
}
