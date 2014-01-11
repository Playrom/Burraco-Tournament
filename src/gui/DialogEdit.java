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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author playrom
 */
public class DialogEdit extends JDialog implements WindowListener {
    JTextField namePlayer1,namePlayer2;
    JLabel idCouple,oneLabel,twoLabel;
    JComboBox mobile;
    JButton editCouple,stopEdit;
    ArrayList coppie;
    SingleList singles;
    int id;
    
    public DialogEdit(SingleList singles,int id){
        this.singles=singles;
        
        this.addWindowListener(this);
        
        this.id=id;
        
        this.setLayout(new MigLayout("fillx,debug"));
        
        idCouple=new JLabel();
        
        this.add(idCouple,"span 3,align center,wrap");
        idCouple.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        
        
        oneLabel=new JLabel();
        twoLabel=new JLabel();
        
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
        
       
        
        stopEdit=new JButton("Termina");
        editCouple=new JButton("Modifica");
        
        this.add(mobile);
        this.add(stopEdit);
        this.add(editCouple);
        
        editCouple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCoupleActionPerformed(evt);
            }
        });
        
        stopEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopEditActionPerformed(evt);
            }
        });
        
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        
        this.pack();

    }
    
    
    
    private void editCoupleActionPerformed(java.awt.event.ActionEvent evt){
        
    }
    
    private void stopEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();        // TODO add your handling code here:
    }
    
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    
    
    
    
    
}
