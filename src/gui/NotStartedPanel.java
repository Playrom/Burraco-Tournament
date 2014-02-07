/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.*;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author playrom
 */
public class NotStartedPanel extends JPanel {
    JDialog addCoupleDialog,listCoppie;
    JButton addCoppieButton,showCoppie,editNomeTorneo,editTurni,avviaTorneo,editSmazzate;
    JLabel cop,fisse,mobili,turni,nomeTorneo,smazzate;
    InfoDialogEdit editInfo;
    ArrayList coppie,singles;
    MainClass main;
    boolean editing=false;
    
    public NotStartedPanel(MainClass main){
        this.main=main;
        coppie=main.getCoppie();
        singles=main.getSingles();
        
        
        setLayout(new MigLayout("fillx"));
        
        if(main.getTorneo().isAlone()) addCoppieButton=new JButton("Aggiungi Giocatori");
        else addCoppieButton=new JButton("Aggiungi Coppie");
            
        showCoppie=new JButton("List Coppie");
        editNomeTorneo=new JButton("Modifica Nome");
        avviaTorneo=new JButton("Avvia Torneo");
       
        if(!main.isAlone()) cop=new JLabel("Coppie: " + String.valueOf(coppie.size()));
        else cop=new JLabel("Giocatori: " + String.valueOf(singles.size()));
        
        turni=new JLabel("Turni: " + String.valueOf(main.getNumTurni()));
        smazzate=new JLabel("Smazzate: " + String.valueOf(main.getSmazzate()));
        nomeTorneo=new JLabel(main.getNomeTorneo());
        
        editTurni=new JButton("Modifica");
        editSmazzate=new JButton("Modifica");
        
        
        this.add(nomeTorneo,"align center");
        this.add(editNomeTorneo,"align center,wrap");
        
        this.add(addCoppieButton);
        this.add(showCoppie,"wrap");
        
        this.add(cop,"wrap");
        
        this.add(turni);
        this.add(editTurni,"wrap");
        
        this.add(smazzate);
        this.add(editSmazzate,"wrap");
        
        this.add(avviaTorneo);
        
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
        
       
        
        editNomeTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNomeTorneoActionPerformed(evt);
            }
        });
        
        editTurni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTurniActionPerformed(evt);
            }
        });
        
        editSmazzate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSmazzateActionPerformed(evt);
            }
        });
        
        avviaTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaTorneoActionPerformed(evt);
            }
        });
        
        
        
    }
    
    private void addCoppieButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(main.getTorneo().isAlone()) addCoupleDialog=new SingleDialogAdd(main.getSingles());
        else addCoupleDialog=new CoupleDialogAdd(main.getSingles(),main.getCoppie());
        
        addCoupleDialog.setVisible(true);
        addCoupleDialog.addWindowListener(listener());
    }
    
    private void showCoppieActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(main.getTorneo().isAlone()) listCoppie=new CoupleDialogList(main.getSingles(),false);
        else listCoppie=new CoupleDialogList(main.getCoppie(),main.getSingles(),false);
        
        listCoppie.setVisible(true);
    }  
    
    
    private void editNomeTorneoActionPerformed(java.awt.event.ActionEvent evt) {
        editInfo=new InfoDialogEdit(main,1);
        editInfo.addWindowListener(listener());
    }
    
    private void editTurniActionPerformed(java.awt.event.ActionEvent evt) {
       editInfo=new InfoDialogEdit(main,0);
       editInfo.addWindowListener(listener());
    }
    
    private void editSmazzateActionPerformed(java.awt.event.ActionEvent evt) {
        editInfo=new InfoDialogEdit(main,2);
        editInfo.addWindowListener(listener());
    }
     
    private void avviaTorneoActionPerformed(java.awt.event.ActionEvent evt) {
        main.startTournament();
        this.setVisible(false);
    }
    
    
    
    
    public void reload(){
        nomeTorneo.setText(main.getNomeTorneo());
        turni.setText("Turni: " + String.valueOf(main.getNumTurni()));
        smazzate.setText("Smazzate: " + String.valueOf(main.getSmazzate()));
        if(main.getTorneo().isAlone()) cop.setText("Giocatori: " + String.valueOf(singles.size()));
        else cop.setText("Coppie: " + String.valueOf(coppie.size()));
    }
    
    public WindowListener listener()  {

            WindowListener temp=new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                reload();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                reload();
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
            
            };
            
                    
            return temp;
            
            
    }
    
    
}
