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
    JButton turni,classifica;
    MainClass main;
    
    public StartedPanel(MainClass main2){
        this.main=main2;
        
        setLayout(new MigLayout("fillx"));
        
        turni=new JButton("Assegna Punteggi");
        classifica=new JButton("Classifica");
        
        this.add(turni);
        this.add(classifica);
       
        
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
        
    }
}
