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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author playrom
 */
public class DialogAdd extends JDialog {
    protected JTextField namePlayer,namePlayer2;
    protected JLabel id,oneLabel,twoLabel;
    JComboBox mobile;
    protected JButton addButton,stopAdd,oneButt,twoButt;
    protected ArrayList singles;
    
    protected int data1,data2;
    
    
    public DialogAdd(ArrayList singles){
        this.singles=singles;
        
        this.setName("Aggiungi Partecipante");
        
        this.setLayout(new MigLayout("fillx"));
        
        id=new JLabel();
        id.setFont(new java.awt.Font("Tahoma", 0, 18)); 

        this.add(id,"span 3,align center,wrap");
        
        
        oneLabel=new JLabel();
        twoLabel=new JLabel();
        
        namePlayer=new JTextField();
        namePlayer2=new JTextField();
        
        oneButt=new JButton("...");
        twoButt=new JButton("...");

        namePlayer.setMinimumSize(new Dimension(14,28));
        namePlayer2.setMinimumSize(new Dimension(14,28));
        
        namePlayer.setColumns(15);
        namePlayer2.setColumns(15);
        
        
        this.add(oneLabel);
        this.add(namePlayer,"span 2");
        this.add(oneButt,"wrap");
        
        this.add(twoLabel);
        this.add(namePlayer2,"span 2");
        this.add(twoButt,"wrap");
        
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
        
        oneButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneButtActionPerformed(evt);
            }
        });
        
        twoButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoButtActionPerformed(evt);
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
    
    private void oneButtActionPerformed(java.awt.event.ActionEvent evt){
        DialogFromDatabase temp=new DialogFromDatabase();
        temp.addPropertyChangeListener("selectTournament", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    int value=(Integer) evt.getNewValue();
                    updateFromLoad(value,1);
                }
            });
    }
     
    private void twoButtActionPerformed(java.awt.event.ActionEvent evt){
        DialogFromDatabase temp=new DialogFromDatabase();
        temp.addPropertyChangeListener("toADD", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    int value=(Integer) evt.getNewValue();
                    updateFromLoad(value,2);
                }
            });
    }
    
    private void updateFromLoad(int value,int position){//reload di tutti i campi con nuovo valore, position=1 se cambio dati primo giocatore,senno per secondo giocatore
        
        ConnectDatabase database=new ConnectDatabase();
        
        SingleList singoli=database.dumpSingoli();
        
        if(position==1){
            
                System.out.println("iterazione");
                Single singolo=singoli.get(value-1);
                System.out.println("trovato");
                namePlayer.setText(singolo.getName());
                data1=singolo.getId_database();
                
            
        }else{
            System.out.println("iterazione");
            Single singolo=singoli.get(value-1);
            System.out.println("trovato");
            namePlayer2.setText(singolo.getName());
            data2=singolo.getId_database();
        }
    }
    
    
    
    
}
