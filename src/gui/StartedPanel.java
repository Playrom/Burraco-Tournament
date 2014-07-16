/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.*;
import java.util.ArrayList;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author playrom
 */
public class StartedPanel extends JPanel{
    JButton turni,classifica,termina;
    MainClass main;
    ConnectDatabase database;
    String path;
    public StartedPanel(MainClass main2,final ConnectDatabase database){
        this.main=main2;
        this.database=database;
        
        setLayout(new MigLayout("fillx"));
        
        turni=new JButton("Assegna Punteggi");
        classifica=new JButton("Classifica");
        termina=new JButton("Termina Torneo");
        
        this.add(turni);
        this.add(classifica);
        this.add(termina);
       
        
        turni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnoDialog uno=new TurnoDialog(main,0);
                uno.setVisible(true);
                
            }
        });
        
        
        classifica.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(main.getTorneo().isAlone()){
                    CoupleDialogList classi=new CoupleDialogList(main.getSingles(),true);
                }else{
                    CoupleDialogList classi=new CoupleDialogList(main.getCoppie(),main.getSingles(),true);
                }
                
            }
        });
        
        termina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                path=database.updateSingoli((SingleList) main.getSingles(), main.getTorneo().getNome());
                firePropertyChange("savetodatabase",-1,path);

                
            }
        });
        
        
    }
}
