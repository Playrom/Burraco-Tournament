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
public class DialogAdd extends JDialog {
    protected JTextField namePlayer,namePlayer2;
    protected JLabel id,oneLabel,twoLabel;
    JComboBox mobile;
    protected JButton addButton,stopAdd;
    protected ArrayList singles;
    
    
    public DialogAdd(ArrayList singles){
        this.singles=singles;
        
        this.setLayout(new MigLayout("fillx"));
        
        id=new JLabel();
        id.setFont(new java.awt.Font("Tahoma", 0, 18)); 

        this.add(id,"span 3,align center,wrap");
        
        
        oneLabel=new JLabel();
        twoLabel=new JLabel();
        
        namePlayer=new JTextField();
        namePlayer2=new JTextField();

        namePlayer.setMinimumSize(new Dimension(14,28));
        namePlayer2.setMinimumSize(new Dimension(14,28));
        
        namePlayer.setColumns(15);
        namePlayer2.setColumns(15);
        
        
        this.add(oneLabel);
        this.add(namePlayer,"span 2 ,wrap");
        
        this.add(twoLabel);
        this.add(namePlayer2,"span 2,wrap");
        
        mobile=new JComboBox();
        mobile.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fissa", "Mobile" }));
        mobile.setSelectedIndex(0);
        
        stopAdd=new JButton("Termina");
        addButton=new JButton("Aggiungi");
        
        this.add(mobile);
        this.add(stopAdd);
        this.add(addButton);
        
        addButton.addActionListener(new java.awt.event.ActionListener() {
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
        
    }
    
    private void stopAddActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();        // TODO add your handling code here:
    }  
    
    
    
    
    
}
