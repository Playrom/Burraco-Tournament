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
public class SingleDialogEdit extends DialogEdit {
    
    
    public SingleDialogEdit(SingleList singles,int id){
        super(singles,id);
        
        this.addWindowListener(this);
        
        
        
        
        idCouple.setText("Modifica Giocatore " + (id + 1) );
        
        Single temp=(Single) singles.get(id);
        
        oneLabel.setText("Giocatore ");
        
        namePlayer1.setText(temp.getName());
        
       
       this.remove(mobile);
       this.remove(twoLabel);
       this.remove(namePlayer2);
        
        
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
        
        
        Single toModify=(Single) singles.get(id);
        
        toModify.setName(namePlayer1.getText());
        
        int t=0;
        firePropertyChange("modificato",false,true);

        this.dispose();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {

    }
}
