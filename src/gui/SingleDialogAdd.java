/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.Coppia;
import defaults.Single;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author playrom
 */
public class SingleDialogAdd extends DialogAdd{
    
   public SingleDialogAdd(ArrayList singles){
        super(singles);
        
        id.setText("Inserimento Giocatore " + String.valueOf(singles.size() + 1) );
        
        oneLabel.setText("Nome");
        this.remove(twoLabel);
        
        this.remove(mobile);
        this.remove(namePlayer2);
        this.remove(twoButt);
        
        
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCoupleActionPerformed(evt);
            }
        });
        
        
       
        
        this.pack();

    }
    
    
    
    private void addCoupleActionPerformed(java.awt.event.ActionEvent evt){
        boolean tipo=true;
        
        
        Single temp=new Single(singles.size(),data1,namePlayer.getText(),true);
        int idSing=temp.getId();
        singles.add(temp);
        

        
        int t=0;
        this.dispose();
        namePlayer.setText("");
        id.setText("Inserimento Giocatore " + String.valueOf(singles.size() + 1));

        this.setVisible(true);
    }
}
