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
    JButton addCoppieButton,showCoppie,editNomeTorneo,editTurni,avviaTorneo;
    JLabel cop,fisse,mobili,turni,nomeTorneo;
    InfoDialogEdit editInfo;
    ArrayList coppie;
    MainClass main;
    boolean editing=false;
    
    public NotStartedPanel(MainClass main){
        this.main=main;
        coppie=main.getCoppie();
        
        
        setLayout(new MigLayout("fillx"));
        addCoppieButton=new JButton("Aggiungi Coppie");
        showCoppie=new JButton("List Coppie");
        editNomeTorneo=new JButton("Modifica Nome");
        avviaTorneo=new JButton("Avvia Torneo");
       
        cop=new JLabel("Coppie: " + String.valueOf(coppie.size()));
        turni=new JLabel("Turni: " + String.valueOf(main.getNumTurni()));
        nomeTorneo=new JLabel(main.getNomeTorneo());
        
        editTurni=new JButton("Modifica");
        
        
        this.add(nomeTorneo,"align center");
        this.add(editNomeTorneo,"align center,wrap");
        
        this.add(addCoppieButton);
        this.add(showCoppie,"wrap");
        
        this.add(cop,"wrap");
        
        this.add(turni);
        this.add(editTurni,"wrap");
        
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
        
        avviaTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaTorneoActionPerformed(evt);
            }
        });
        
        
        
    }
    
    private void addCoppieButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
        addCoupleDialog=new CoupleDialogAdd(coppie);
        addCoupleDialog.setVisible(true);
        addCoupleDialog.addWindowListener(listener());
    }
    
    private void showCoppieActionPerformed(java.awt.event.ActionEvent evt) {                                        
        listCoppie=new CoupleDialogList(coppie,false);
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
     
    private void avviaTorneoActionPerformed(java.awt.event.ActionEvent evt) {
        main.startTournament();
        this.setVisible(false);
    }
    
    
    
    
    public void reload(){
        nomeTorneo.setText(main.getNomeTorneo());
        turni.setText("Turni: " + String.valueOf(main.getNumTurni()));
        cop.setText("Coppie: " + String.valueOf(coppie.size()));
    }
    
    public WindowListener listener()  {

            WindowListener temp=new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            
            };
            
                    
            return temp;
            
            
    }
    
    
}
