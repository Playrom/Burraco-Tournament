/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author playrom
 */
public class TurnoDialog extends JDialog {
    
    JTabbedPane tab;
    MainClass main;
    PanelTurno pannelli[];
    ArrayList turni;
    
    
    
    public TurnoDialog(MainClass main2,int idTurno){
        this.main=main2;
        tab=new JTabbedPane();
        turni=main.getTorneo().getTurni();
        pannelli=new PanelTurno[turni.size()];
        
        setLayout(new MigLayout("fillx"));

        
        for(int i=0;i<turni.size();i++){
            
            pannelli[i]=new PanelTurno(main,i);
            tab.addTab("Turno " + (i+1) , pannelli[i]);
            pannelli[i].setEnabled(false);
            this.add(tab);
            tab.setEnabledAt(i, false);
            final int m=i+1;
            
            pannelli[i].addPropertyChangeListener("calcolato", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    tab.setEnabledAt(m, true);
                }
            });
            
            pannelli[i].addPropertyChangeListener("annulla", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    end();
                }
            });
            
        }
        
        tab.setEnabledAt(0, true);


        
        
        this.pack();
        
        
    }
    
    public void end(){
        dispose();
    }
    
    
}
