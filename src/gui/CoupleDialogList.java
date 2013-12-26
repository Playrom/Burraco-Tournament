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
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Giorgio
 */
public class CoupleDialogList extends JDialog {
   
    ArrayList coppie;
    public CoupleDialogList(ArrayList coppie){
        this.coppie=coppie;
        
       setLayout(new MigLayout("fillx"));
       int i=0;
       JLabel names,ids,masters,victorys,types;
       names=new JLabel("Giocatori");
       ids=new JLabel("ID");
       masters=new JLabel("MP");
       victorys=new JLabel("VP");
       types=new JLabel("Tipo");
       
       this.add(ids);
       this.add(types);
       this.add(names);
       this.add(masters);
       this.add(victorys,"wrap");
       
       for (Object temp1 : coppie) {
           
        Coppia temp=(Coppia) temp1;
        JLabel label=new JLabel(temp.toString());
        JLabel id=new JLabel(String.valueOf(temp.getId()));
        JLabel master=new JLabel(String.valueOf(temp.getMastPoints()));
        JLabel victory=new JLabel(String.valueOf(temp.getVictPoints()));
        boolean tipo=temp.getType();
        JLabel type;
        if(tipo==true) { type=new JLabel("Mob"); } else { type=new JLabel("Fis"); }
        JButton test=new JButton("Test");
        /*label.setFont(label.getFont().deriveFont(20f));
        id.setFont(label.getFont().deriveFont(20f));
        master.setFont(label.getFont().deriveFont(20f));
        victory.setFont(label.getFont().deriveFont(20f));
        
        this.setBackground(new Color(000000));*/
        this.add(id);
        this.add(type);
        this.add(label);
        this.add(victory);
        this.add(master);
        this.add(test,"wrap");
        
       }
       
       this.setResizable(false);
       
       this.pack();
       
       this.setVisible(true);
        
    }
    
}
