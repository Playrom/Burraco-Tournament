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
public class CoupleDialogEdit extends JDialog implements WindowListener {
    JTextField namePlayer1,namePlayer2;
    JLabel idCouple,oneLabel,twoLabel;
    JComboBox mobile;
    JButton editCouple,stopEdit;
    ArrayList coppie;
    int id;
    
    public CoupleDialogEdit(ArrayList data,int id){
        coppie=data;
        
        this.addWindowListener(this);
        
        this.id=id;
        
        
        
        this.setLayout(new MigLayout("fillx,debug"));
        idCouple=new JLabel("Inserimento Coppia " + (id + 1) );
        this.add(idCouple,"span 3,align center,wrap");
        idCouple.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        
        Coppia temp=(Coppia) coppie.get(id);
        
        oneLabel=new JLabel("Giocatore 1");
        twoLabel=new JLabel("Giocatore 2");
        
        namePlayer1=new JTextField(temp.getName1());
        namePlayer2=new JTextField(temp.getName2());

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
        
        boolean tipo=temp.getType();
        if(tipo==true) { mobile.setSelectedIndex(1); } else { mobile.setSelectedIndex(0); }
        
        
        
        
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
        boolean tipo=true;
        String temp=mobile.getSelectedItem().toString();
        if(temp.equals("Fissa")) tipo=false;
        
        Coppia toModify=(Coppia) coppie.get(id);
        
        toModify.setName1(namePlayer1.getText());
        toModify.setName2(namePlayer2.getText());
        toModify.setType(tipo);
        
        int t=0;
        this.dispose();
    }
    
    private void stopEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();        // TODO add your handling code here:
    }
    
    public void windowClosed(WindowEvent e) {
        new CoupleDialogList(coppie,false);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
