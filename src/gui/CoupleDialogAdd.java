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
public class CoupleDialogAdd extends JDialog {
    JTextField namePlayer1,namePlayer2;
    JLabel idCouple,oneLabel,twoLabel;
    JComboBox mobile;
    JButton addCouple,stopAdd;
    ArrayList coppie;
    
    
    public CoupleDialogAdd(ArrayList data){
        coppie=data;
        this.setLayout(new MigLayout("fillx,debug"));
        idCouple=new JLabel("Inserimento Coppia " + String.valueOf(coppie.size() + 1) );
        this.add(idCouple,"span 3,align center,wrap");
        idCouple.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        
        oneLabel=new JLabel("Giocatore 1");
        twoLabel=new JLabel("Giocatore 2");
        
        namePlayer1=new JTextField();
        namePlayer2=new JTextField();

        namePlayer1.setMinimumSize(new Dimension(14,28));
        namePlayer2.setMinimumSize(new Dimension(14,28));
        
        namePlayer1.setColumns(15);
        namePlayer2.setColumns(15);
        
        
        this.add(oneLabel);
        this.add(namePlayer1,"span 2 ,wrap");
        
        this.add(twoLabel);
        this.add(namePlayer2,"span 2,wrap");
        
        mobile=new JComboBox();
        mobile.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fissa", "Mobile" }));
        mobile.setSelectedIndex(0);
        
        stopAdd=new JButton("Termina");
        addCouple=new JButton("Aggiungi");
        
        this.add(mobile);
        this.add(stopAdd);
        this.add(addCouple);
        
        addCouple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCoupleActionPerformed(evt);
            }
        });
        
        stopAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopAddActionPerformed(evt);
            }
        });
        
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        
        this.pack();

    }
    
    
    
    private void addCoupleActionPerformed(java.awt.event.ActionEvent evt){
        boolean tipo=true;
        String temp=mobile.getSelectedItem().toString();
        if(temp.equals("Fissa")) tipo=false;
        Coppia tempCoppia=new Coppia(namePlayer1.getText(), namePlayer2.getText(), coppie.size(), tipo);
        coppie.add(tempCoppia); // TODO add your handling code here:
        int t=0;
        this.dispose();
        namePlayer1.setText("");
        namePlayer2.setText("");
        idCouple.setText("Inserimento Coppia " + String.valueOf(coppie.size() + 1));
        mobile.setSelectedIndex((mobile.getSelectedIndex() + 1) % 2);
        this.setVisible(true);
    }
    
    private void stopAddActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();        // TODO add your handling code here:
    }  
    
    
    
    
    
}
