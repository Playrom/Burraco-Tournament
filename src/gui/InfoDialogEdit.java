/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.*;
import java.awt.Component;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Giorgio
 */
public class InfoDialogEdit extends JDialog {
    JLabel info;
    JTextField field;
    JButton conferma,annulla;
    MainClass main;
    int modificatore; //0 - nome torneo , 1 - numero turni
    
    public InfoDialogEdit(MainClass main2,int modificatore2){
        this.main=main2;
        this.modificatore=modificatore2;
        
        conferma=new JButton("Applica");
        annulla=new JButton("Annulla");
        
        setLayout(new MigLayout("fillx"));
        
        if(modificatore==0){
            info=new JLabel("Modifica Numero Turni");
            field=new JTextField(String.valueOf(main.getNumTurni()));
            
            field.setColumns(2);
            
            
            conferma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confermaTurniActionPerformed(evt);
            }
        });
            
            this.add(info);
            this.add(field,"wrap");
            this.add(annulla);
            this.add(conferma);
            
            
        } else if (modificatore==1){
            
            info=new JLabel("Modifica Nome Torneo");
            field=new JTextField(main.getNomeTorneo());
            
            field.setColumns(15);
            
            conferma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confermaNomeActionPerformed(evt);
            }
        });
            
            this.add(info);
            this.add(field,"wrap");
            this.add(annulla);
            this.add(conferma);
            
        }
        
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });
        
        this.pack();
        this.setVisible(true);
        
    }
    
    private void confermaTurniActionPerformed(java.awt.event.ActionEvent evt) {                                        
        main.setNumTurni(Integer.valueOf(field.getText()));
        this.dispose();
    }
    
    private void confermaNomeActionPerformed(java.awt.event.ActionEvent evt) {                                        
        main.setNomeTorneo(field.getText());
        this.dispose();
    }
    
    private void annullaActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();
    }
    
    
    
}
