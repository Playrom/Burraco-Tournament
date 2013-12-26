/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author playrom
 */
public class NotStartedPanel extends JPanel {
    JDialog addCoupleDialog,listCoppie;
    JButton addCoppieButton,showCoppie;
    ArrayList coppie;
    
    public NotStartedPanel(ArrayList data){
        coppie=data;
        addCoppieButton=new JButton("Aggiungi Coppie");
        showCoppie=new JButton("List Coppie");
        
        this.add(addCoppieButton , "span 2");
        this.add(showCoppie,"span 2,wrap");
        
        addCoppieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCoppieButtonActionPerformed(evt);
            }
        });
        
        showCoppie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCoppieActionPerformed(evt);
            }
        });
        
    }
    
    private void addCoppieButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
        addCoupleDialog=new CoupleDialogAdd(coppie);
        addCoupleDialog.setVisible(true);
    }
    
    private void showCoppieActionPerformed(java.awt.event.ActionEvent evt) {                                        
        listCoppie=new CoupleDialogList(coppie);
        listCoppie.setVisible(true);
    }  
    
    
    
}
