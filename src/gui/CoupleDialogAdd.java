/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.ArrayList;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import defaults.*;
import java.awt.Dimension;

/**
 *
 * @author playrom
 */
public class CoupleDialogAdd extends DialogAdd {
    protected ArrayList<Coppia> coppie;
    
    
    public CoupleDialogAdd(ArrayList<Single> singles,ArrayList<Coppia> coppie,ConnectDatabase database){
        super(singles,database);
        this.coppie=coppie;
        
        id.setText("Inserimento Coppia " + String.valueOf(coppie.size() + 1) );
        
        oneLabel.setText("Giocatore 1");
        twoLabel.setText("Giocatore 2"); //Da remove in single
        
        
        
        
       
       
        
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCoupleActionPerformed(evt);
            }
        });
        
        
       
        
        this.pack();

    }
    
    
    
    private void addCoupleActionPerformed(java.awt.event.ActionEvent evt){
        boolean tipo=true;
        String temp=mobile.getSelectedItem().toString();
        if(temp.equals("Fissa")) tipo=false;
        
        int ids=singles.size()-1;
        if(singles.isEmpty()) ids=0;
        
        Single tempUno=new Single(singles.size(),data1,namePlayer.getText(),false);
        singles.add(tempUno);
        
        Single tempDue=new Single(singles.size(),data2,namePlayer2.getText(),false);
        singles.add(tempDue);
        

        Coppia tempCoppia=new Coppia(tempUno,tempDue, coppie.size(), tipo);
        coppie.add(tempCoppia); // TODO add your handling code here:
        
        int t=0;
        this.dispose();
        namePlayer.setText("");
        namePlayer2.setText("");
        id.setText("Inserimento Coppia " + String.valueOf(coppie.size() + 1));
        mobile.setSelectedIndex((mobile.getSelectedIndex() + 1) % 2);
        this.setVisible(true);
    }
    
    
    
    
    
    
    
}
