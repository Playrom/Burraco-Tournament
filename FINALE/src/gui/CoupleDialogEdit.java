/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.ArrayList;
import defaults.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author playrom
 */
public class CoupleDialogEdit extends DialogEdit  {
    
    ArrayList coppie;
    
    public CoupleDialogEdit(SingleList singles,ArrayList coppie,int id){
        super(singles,id);
        this.coppie=coppie;
        
        this.addWindowListener(this);
        
        
        
        
        idCouple.setText("Modifica Coppia " + (id + 1) );
        
        Coppia temp=(Coppia) coppie.get(id);
        
        oneLabel.setText("Giocatore 1");
        twoLabel.setText("Giocatore 2");
        
        namePlayer1.setText(temp.getName1());
        namePlayer2.setText(temp.getName2());
        
       
        boolean tipo=temp.getType();
        if(tipo==true) { mobile.setSelectedIndex(1); } else { mobile.setSelectedIndex(0); }
        
        
        
        editCouple.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCoupleActionPerformed(evt);
            }
        });
        
        
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        
        this.pack();

    }
    
    
    
    private void editCoupleActionPerformed(java.awt.event.ActionEvent evt){
        boolean tipo=true;
        String temp=mobile.getSelectedItem().toString();
        if(temp.equals("Fissa")) tipo=false;
        
        Coppia toModify=(Coppia) coppie.get(id);
        
        toModify.setName1(namePlayer1.getText());
        toModify.setName2(namePlayer2.getText());
        toModify.setType(tipo);
        
        int t=0;
        firePropertyChange("modificato",false,true);
        this.dispose();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {

    }
}
